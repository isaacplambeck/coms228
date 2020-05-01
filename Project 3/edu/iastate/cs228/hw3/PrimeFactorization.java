package edu.iastate.cs228.hw3;

/**
 *  
 * @author Isaac Plambeck
 *
 */

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class PrimeFactorization implements Iterable<PrimeFactor>
{
	private static final long OVERFLOW = -1;
	private long value; 	// the factored integer 
							// it is set to OVERFLOW when the number is greater than 2^63-1, the
						    // largest number representable by the type long. 
	
	/**
	 * Reference to dummy node at the head.
	 */
	private Node head;
	  
	/**
	 * Reference to dummy node at the tail.
	 */
	private Node tail;
	
	private int size;     	// number of distinct prime factors


	// ------------
	// Constructors 
	// ------------
	
    /**
	 *  Default constructor constructs an empty list to represent the number 1.
	 *  
	 *  Combined with the add() method, it can be used to create a prime factorization.  
	 */
	public PrimeFactorization() 
	{	 
		value = 1;
		size = 0;
		
		head = new Node();
		tail = new Node();
		
		head.next = tail;
		tail.previous = head;
		
		
		
	
	}

	
	/** 
	 * Obtains the prime factorization of n and creates a doubly linked list to store the result.   
	 * Follows the direct search factorization algorithm in Section 1.2 of the project description. 
	 * 
	 * @param n
	 * @throws IllegalArgumentException if n < 1
	 */
	public PrimeFactorization(long n) throws IllegalArgumentException 
	{
		
		if(n < 1) {
			//@throws IllegalArgumentException if n < 1
			throw new IllegalArgumentException();
		}
		
		size = 0;
		value = 1;
		
		head = new Node();
		tail = new Node();
		
		head.next = tail;
		tail.previous = head;
		
		
		
		//my count
		int c = 0;
		
		long l = n;
		
		//what ill be dividing the number by/modding by
		int d = 0;
		
		
		
		
		//diving/modding by 2
		d = 2;
		//if divide by 2, keep dividing until it cant
		while(l % d == 0) {
			l = l/d;
			c++;
		}
		
		//after done % 2, check where c is, if c is > 0, then it did % by 2, so add 2
		if(c > 0) {
			add(d, c);
		}
		
		//now im doing it by 3
		d = 3;
		
		//while the product of d^2 pretty much is less than the num
		while(Math.multiplyExact(d, d) <= l){
			
			//start with c at 0
			c = 0;
			
			//do this until the current value of d doesnt mod the num
			while(l % d == 0) {
				l = l/ d;
				c++;
			}
			
			//this means it did mod it
			if(c > 0) {
				add(d, c);
			}
			
			d++;
			d++;
			
			
			
			
			
			
			
			
		}
		
		if(l != 1) {
			//num casted to int because its long
			add((int) l, 1);
		}
		
		
		
		
	}
	
	
	/**
	 * Copy constructor. It is unnecessary to verify the primality of the numbers in the list.
	 * 
	 * @param pf
	 */
	public PrimeFactorization(PrimeFactorization pf)
	{
		
		this.value = pf.value;
		this.size = pf.size;
		this.head = pf.head;
		this.tail = pf.tail;
		
		
		
		
	}
	
	/**
	 * Constructs a factorization from an array of prime factors.  Useful when the number is 
	 * too large to be represented even as a long integer. 
	 * 
	 * @param pflist
	 */
	public PrimeFactorization (PrimeFactor[] pfList)
	{
		size = 0;
		value = 1;
		
		head = new Node();
		tail = new Node();
		
		head.next = tail;
		tail.previous = head;
		
		//PrimeFactorization arr = new PrimeFactorization();
		
		for(int i = 0; i < pfList.length; i++) {
			
			add(pfList[i].prime, pfList[i].multiplicity);
			
			//size++;
		}
		
		updateValue();
		
		
		
	}
	
	

	// --------------
	// Primality Test
	// --------------
	
    /**
	 * Test if a number is a prime or not.  Check iteratively from 2 to the largest 
	 * integer not exceeding the square root of n to see if it divides n. 
	 * 
	 *@param n
	 *@return true if n is a prime 
	 * 		  false otherwise 
	 */
    public static boolean isPrime(long n) 
	{
    	
    	
    	
    	if(n < 2) {
    		return false;
    	}
    	
    	
    	long sqrt = (long) Math.sqrt(n) + 1;
    	
    	for(int i = 2; i < sqrt; i++) {
    		if(n % i == 0) {
    			return false;
    		}
    	}
    	
    	
		return true; 
	}   

   
	// ---------------------------
	// Multiplication and Division 
	// ---------------------------
	
	/**
	 * Multiplies the integer v represented by this object with another number n.  Note that v may 
	 * be too large (in which case this.value == OVERFLOW). You can do this in one loop: Factor n and 
	 * traverse the doubly linked list simultaneously. For details refer to Section 3.1 in the 
	 * project description. Store the prime factorization of the product. Update value and size. 
	 * 
	 * @param n
	 * @throws IllegalArgumentException if n < 1
	 */
	public void multiply(long n) throws IllegalArgumentException 
	{
		
		if(n < 1) {
			throw new IllegalArgumentException();
		}
		
		PrimeFactorization pf = new PrimeFactorization(n);
		
		PrimeFactorizationIterator iter = pf.iterator();
		
		while(iter.hasNext()) {
			
			PrimeFactor p = iter.next();
			
			add(p.prime, p.multiplicity);
			
		}
		
		
		
		
		
		//PrimeFactorization pf = new PrimeFactorization(n);
		
		//multiply(pf);
		
	}
	
	/**
	 * Multiplies the represented integer v with another number in the factorization form.  Traverse both 
	 * linked lists and store the result in this list object.  See Section 3.1 in the project description 
	 * for details of algorithm. 
	 * 
	 * @param pf 
	 */
	public void multiply(PrimeFactorization pf)
	{
		
		PrimeFactorizationIterator iter = pf.iterator();
		
		while(iter.hasNext()) {
			//copy over the nodes
			PrimeFactor temp = iter.next();
			
			add(temp.prime, temp.multiplicity);
		
			
		}
		//updateValue();
		
		
	}
	
	
	/**
	 * Multiplies the integers represented by two PrimeFactorization objects.  
	 * 
	 * @param pf1
	 * @param pf2
	 * @return object of PrimeFactorization to represent the product 
	 */
	public static PrimeFactorization multiply(PrimeFactorization pf1, PrimeFactorization pf2)
	{
		
		
		//mult pf1 pf2
		pf1.multiply(pf2);
		
		//return object of PrimeFactorization to represent the product 
		return pf1; 
	}

	
	/**
	 * Divides the represented integer v by n.  Make updates to the list, value, size if divisible.  
	 * No update otherwise. Refer to Section 3.2 in the project description for details. 
	 *  
	 * @param n
	 * @return  true if divisible 
	 *          false if not divisible 
	 * @throws IllegalArgumentException if n <= 0
	 */
	public boolean dividedBy(long n) throws IllegalArgumentException
	{
		
		if(n <= 0) {
			throw new IllegalArgumentException();
		}
		
		if(this.value != -1 && this.value < n) {
			return false;
		}
		
		PrimeFactorization pf = new PrimeFactorization(n);
		
		dividedBy(pf);
		
		updateValue();
			
		
		
		
		
		return true;
		
		
		//return false; 
	}

	
	/**
	 * Division where the divisor is represented in the factorization form.  Update the linked 
	 * list of this object accordingly by removing those nodes housing prime factors that disappear  
	 * after the division.  No update if this number is not divisible by pf. Algorithm details are 
	 * given in Section 3.2. 
	 * 
	 * @param pf
	 * @return	true if divisible by pf
	 * 			false otherwise
	 */
	public boolean dividedBy(PrimeFactorization pf)
	{
		
		
		if((this.value != -1 && pf.value != -1 && this.value < pf.value )||( this.value != -1 && pf.value == -1 )) {
			return false;
		}
		
		if(this.value == pf.value) {
			clearList();
			updateValue();
			//value = 1;
			return true;
		}
		
		//boolean until = false;
		
		PrimeFactorization copy = new PrimeFactorization(this);;
		PrimeFactorizationIterator iterPf = pf.iterator();
		
		PrimeFactorizationIterator iterCopy = copy.iterator();
		
		boolean first = true;
		
		while(iterPf.cursor != null) {
			
			
			
			if((iterPf.nextIndex() != pf.size) && (first == false)) {
				iterPf.next();
				
				
			}
			
			if((iterCopy.nextIndex() != copy.size) && (first == false)) {
				iterCopy.next();
			}
			
			if(iterPf.cursor.next == null && iterCopy.cursor.next == null) {
				break;
			}
			
			if(iterPf.cursor.next == null) {
				iterPf.previous();
			}
			
			
			//if(iterPf.)
			
			
			
			
			first = false;
			
			if(iterCopy.cursor.pFactor.prime >= iterPf.cursor.pFactor.prime) {
				
				//until = true;
				
				
				
				
				if(iterCopy.cursor.pFactor.prime == iterPf.cursor.pFactor.prime && iterCopy.cursor.pFactor.multiplicity >= iterPf.cursor.pFactor.multiplicity) {
					
					iterCopy.cursor.pFactor.multiplicity = iterCopy.cursor.pFactor.multiplicity - iterPf.cursor.pFactor.multiplicity;
					
					if(iterCopy.cursor.pFactor.multiplicity == 0) {
						iterCopy.cursor.pFactor.prime = 0;
						iterCopy.remove();
						//iterCopy.next();
						//iterPf.next();
					}
					
					
				}
				
				
				
				
			}
			
			
			
			
		}
		
		
		
		
		copy.head = this.head;
		copy.tail = this.tail;
		copy.size = this.size;
		
		
		
		updateValue();
		
		//int var = 1;
		
		
		//value = dividedBy(copy, pf).value;
		
		//value = x.value;
		
		//updateValue();
		
		
		return true;
	}
		

	/**
	 * Divide the integer represented by the object pf1 by that represented by the object pf2. 
	 * Return a new object representing the quotient if divisible. Do not make changes to pf1 and 
	 * pf2. No update if the first number is not divisible by the second one. 
	 *  
	 * @param pf1
	 * @param pf2
	 * @return quotient as a new PrimeFactorization object if divisible
	 *         null otherwise 
	 */
	public static PrimeFactorization dividedBy(PrimeFactorization pf1, PrimeFactorization pf2)
	{
		
		//PrimeFactorizationIterator iter1 = pf1.iterator();
		
		if(pf1.value % pf2.value != 0) {
			return null;
		}
		
			pf1.dividedBy(pf2);
			
			//PrimeFactorization pf = new PrimeFactorization();
			
			return pf1;
		
		
		
		//return null; 
	}

	
	// -----------------------
	// Greatest Common Divisor
	// -----------------------

	/**
	 * Computes the greatest common divisor (gcd) of the represented integer v and an input integer n.
	 * Returns the result as a PrimeFactor object.  Calls the method Euclidean() if 
	 * this.value != OVERFLOW.
	 *     
	 * It is more efficient to factorize the gcd than n, which can be much greater. 
	 *     
	 * @param n
	 * @return prime factorization of gcd
	 * @throws IllegalArgumentException if n < 1
	 */
	public PrimeFactorization gcd(long n) throws IllegalArgumentException
	{
		
		if(n < 1) {
			throw new IllegalArgumentException();
		}
		
		if(this.value != OVERFLOW) {
			
			PrimeFactorization pf = new PrimeFactorization(Euclidean(this.value, n));
			
			return pf;
			
		}
		else {
			PrimeFactorization pf = this.gcd(new PrimeFactorization(n));
		
			return pf;
			
		}
		
		
		//return null; 
	}
	

	/**
	  * Implements the Euclidean algorithm to compute the gcd of two natural numbers m and n. 
	  * The algorithm is described in Section 4.1 of the project description. 
	  * 
	  * @param m
	  * @param n
	  * @return gcd of m and n. 
	  * @throws IllegalArgumentException if m < 1 or n < 1
	  */
 	public static long Euclidean(long m, long n) throws IllegalArgumentException
	{
 		
 		
 		
 		if(m < 1 || n < 1) {
 			throw new IllegalArgumentException();
 		}
 		
 		long bigger, smaller, remainder;
 		
 		bigger = Math.max(m, n);
 		smaller = Math.min(m, n);
 		
 		remainder = bigger % smaller;
 		
 		while(remainder != 0) {
 			
 			bigger = smaller;
 			
 			smaller = remainder;
 			
 			remainder = bigger % smaller;
 			
 			
 		}
 		
 		
 		
 		
 		
 		
 		return smaller; 
	}

 	
	/**
	 * Computes the gcd of the values represented by this object and pf by traversing the two lists.  No 
	 * direct computation involving value and pf.value. Refer to Section 4.2 in the project description 
	 * on how to proceed.  
	 * 
	 * @param  pf
	 * @return prime factorization of the gcd
	 */
	public PrimeFactorization gcd(PrimeFactorization pf)
	{

		if(this.value == -1 || pf.value == -1) {
			updateValue();
			
			if(this.valueOverflow() || pf.valueOverflow()) {
				value = OVERFLOW;
			}
		}
		
		PrimeFactorization done = new PrimeFactorization();
		
		//PrimeFactorization done;
		
		PrimeFactorizationIterator iter = iterator();
		
		
		
		PrimeFactorizationIterator iter2 = pf.iterator();
		
		if(this.value != -1) {
			
			
			
			
			
		
		//while(iter.hasNext()) {
			
			//PrimeFactor iterpf = iter.next();
			
			//while(iter2.hasNext()) {
			
				
			
			
			boolean match = false;
			
			PrimeFactor iter2pf = iter2.next();
			PrimeFactor iterpf = iter.next();
			
			while(iter2.hasNext() || iter.hasNext()) {
			
				if(match == true) {
					iter2pf = iter2.next();
					iterpf = iter.next();
					
				}
				
					
				
			/*	
				if(iter2.hasNext()) {
					iter2pf = iter2.next();
				}
				else {
					iter2pf = iter2.previous();
				}
				
				if(iter.hasNext()) {
					iterpf = iter.next();
				}
				else {
					iterpf = iter.previous();
				}
				*/
		
				
				if(iterpf.prime == iter2pf.prime) {
					
					match = true;
					
					//for finding the min mult
					//int minOfPf;
					
					//done = new PrimeFactorization();
					
					int minOfPf = Math.min(iterpf.multiplicity, iter2pf.multiplicity);
					
					done.add(iterpf.prime, minOfPf);
					
					updateValue();
					
					//pf.unlink(iter2.cursor.previous);
					
					//size down
					//size--;
					
				}
				else {
					
					match = false;
					
				if(iter2.hasNext() || iter.hasNext()) {
					
					if(iter2.hasNext()) {
						iter2pf = iter2.next();
					}
					else {
						iter2pf = iter2.previous();
					}
					
					if(iter.hasNext()) {
						iterpf = iter.next();
					}
					else {
						iterpf = iter.previous();
					}
				}
					
					
				}
				
			//}
			
			
		}
		}
		
		
		
		//System.out.println(done.value);
		return done;
	}
	
	
	/**
	 * 
	 * @param pf1
	 * @param pf2
	 * @return prime factorization of the gcd of two numbers represented by pf1 and pf2
	 */
	public static PrimeFactorization gcd(PrimeFactorization pf1, PrimeFactorization pf2)
	{
		return pf1.gcd(pf2); 
	}

	// ------------
	// List Methods
	// ------------
	
	/**
	 * Traverses the list to determine if p is a prime factor. 
	 * 
	 * Precondition: p is a prime. 
	 * 
	 * @param p  
	 * @return true  if p is a prime factor of the number v represented by this linked list
	 *         false otherwise 
	 * @throws IllegalArgumentException if p is not a prime
	 */
	public boolean containsPrimeFactor(int p) throws IllegalArgumentException
	{
		
		
		
		PrimeFactorizationIterator iter = iterator();
		
		while(iter.hasNext()) {
			
			if(iter.next().prime == p) {
				
				return true;
				
			}
			else {
				throw new IllegalArgumentException();
			}
			
		}
		
		return false; 
	}
	
	// The next two methods ought to be private but are made public for testing purpose. Keep
	// them public 
	
	/**
	 * Adds a prime factor p of multiplicity m.  Search for p in the linked list.  If p is found at 
	 * a node N, add m to N.multiplicity.  Otherwise, create a new node to store p and m. 
	 *  
	 * Precondition: p is a prime. 
	 * 
	 * @param p  prime 
	 * @param m  multiplicity
	 * @return   true  if m >= 1
	 *           false if m < 1   
	 **/
    public boolean add(int p, int m) 
    {
    	if(m < 1) {
    		return false;
    	}
    	
    	
    	
    	PrimeFactorizationIterator iter = iterator();
    	
    	int foundP = 0;
    	
    	while(iter.hasNext()) {
    		
    		if(iter.next().prime == p) {
    			
    			foundP = 1;
    			
    			iter.cursor.previous.pFactor.multiplicity += m;
    			
    			break;
    		}
    		else if(iter.cursor.previous.pFactor.prime > p) {
    			
    			foundP = 1;
    			
    			link(iter.cursor.previous.previous, new Node(p, m));
    			
    			size++;
    			
    			break;
    			
    			
    		}
    		
   
    		
    	}
    	
    	if(foundP == 0) {
			link(iter.cursor.previous, new Node(p, m));
			
			size++;
			
			
		}
    	
    	updateValue();
    	
    	return true; 
    }
	    
    /**
     * Removes m from the multiplicity of a prime p on the linked list.  It starts by searching 
     * for p.  Returns false if p is not found, and true if p is found. In the latter case, let 
     * N be the node that stores p. If N.multiplicity > m, subtracts m from N.multiplicity.  
     * If N.multiplicity <= m, removes the node N.  
     * 
     * Precondition: p is a prime. 
     * 
     * @param p
     * @param m
     * @return true  when p is found. 
     *         false when p is not found. 
     * @throws IllegalArgumentException if m < 1
     */
    public boolean remove(int p, int m) throws IllegalArgumentException
    {
    	
    	if(m < 1) {
    		throw new IllegalArgumentException();
    	}
    	
    	PrimeFactorizationIterator iter = iterator();
    	
    	//until end
    	while(iter.hasNext()) {
    		
    		//true when p is found
    		if(iter.next().prime == p) {
    			
    			int N = iter.cursor.previous.pFactor.multiplicity;
    			
    			//If N.multiplicity > m
    			if(N > m) {
    				
    				//subtracts m from N.multiplicity.
    				N -= m;
  
    			}
    			//If N.multiplicity <= m,
    			else if(N <= m){
    				
    				//removes the node N.  
    				unlink(iter.cursor.previous);
    				
    				//removed so size down
    				size--;
    				
    			}
    			
    			//update the value
    			updateValue();
    			
    			//true  when p is found.
    			return true;
    			
    			
    			
    			
    			
    		}
    		
    		
    		
    	}
    	
    	
    	updateValue();
    	
    	//false when p is not found.
    	return false; 
    }


    /**
     * 
     * @return size of the list
     */
	public int size() 
	{
		return size; 
	}

	
	/**
	 * Writes out the list as a factorization in the form of a product. Represents exponentiation 
	 * by a caret.  For example, if the number is 5814, the returned string would be printed out 
	 * as "2 * 3^2 * 17 * 19". 
	 */
	@Override 
	public String toString()
	{
		
		String s = "";
		
		//PrimeFactorization pf = new PrimeFactorization(value);
		
		PrimeFactorizationIterator iter = iterator();
		
		if(iter.cursor != tail) {
			
			
			s = s + iter.cursor.pFactor.toString();
			
			iter.next();
			
			while(iter.hasNext()) {
				
				if(iter.cursor.pFactor.prime == 0) {
					iter.next();
				}
				
				s = s + " * " + iter.cursor.pFactor.toString();
				
				iter.next();
			}
		}
		else {
			s = "1";
		}
		
			
		
		
		
		
		
		
		return s;
		

	}

	
	// The next three methods are for testing, but you may use them as you like.  

	/**
	 * @return true if this PrimeFactorization is representing a value that is too large to be within 
	 *              long's range. e.g. 999^999. false otherwise.
	 */
	public boolean valueOverflow() {
		return value == OVERFLOW;
	}

	/**
	 * @return value represented by this PrimeFactorization, or -1 if valueOverflow()
	 */
	public long value() {
		return value;
	}

	
	public PrimeFactor[] toArray() {
		PrimeFactor[] arr = new PrimeFactor[size];
		int i = 0;
		for (PrimeFactor pf : this)
			arr[i++] = pf;
		return arr;
	}


	
	@Override
	public PrimeFactorizationIterator iterator()
	{
	    return new PrimeFactorizationIterator();
	}
	
	/**
	 * Doubly-linked node type for this class.
	 */
    private class Node 
    {
		public PrimeFactor pFactor;			// prime factor 
		public Node next;
		public Node previous;
		
		/**
		 * Default constructor for creating a dummy node.
		 */
		public Node()
		{
			
			//set all to null.
			pFactor = null;
			next = null;
			previous = null;
			
		}
	    
		/**
		 * Precondition: p is a prime
		 * 
		 * @param p	 prime number 
		 * @param m  multiplicity 
		 * @throws IllegalArgumentException if m < 1 
		 */
		public Node(int p, int m) throws IllegalArgumentException 
		{	
			
			if(m < 1) {
				throw new IllegalArgumentException();
			}
			
			pFactor = new PrimeFactor(p, m);
			
			next = null;
			
			previous = null;
			
			
			
		}   

		
		/**
		 * Constructs a node over a provided PrimeFactor object. 
		 * 
		 * @param pf
		 * @throws IllegalArgumentException
		 */
		public Node(PrimeFactor pf)  
		{
			
			
			if(pf == null) {
				//@throws IllegalArgumentException
				throw new IllegalArgumentException();
			}
			
			pFactor = pf;
			next = null;
			previous = null;
		}


		/**
		 * Printed out in the form: prime + "^" + multiplicity.  For instance "2^3". 
		 * Also, deal with the case pFactor == null in which a string "dummy" is 
		 * returned instead.  
		 */
		@Override
		public String toString() 
		{
			
			if(pFactor == null) {
				return "dummy";
			}
			else {
				String form = pFactor.toString();
				
			
				return form; 
			}
			
			
			
		}
    }

    
    private class PrimeFactorizationIterator implements ListIterator<PrimeFactor>
    {  	
        // Class invariants: 
        // 1) logical cursor position is always between cursor.previous and cursor
        // 2) after a call to next(), cursor.previous refers to the node just returned 
        // 3) after a call to previous() cursor refers to the node just returned 
        // 4) index is always the logical index of node pointed to by cursor

        private Node cursor = head.next;
        private Node pending = null;    // node pending for removal
        private int index = 0;      
  	  
    	// other instance variables ... 
    	  
      
        /**
    	 * Default constructor positions the cursor before the smallest prime factor.
    	 */
    	public PrimeFactorizationIterator()
    	{
    		
    		//already done but redoing
    		cursor = head.next;
    		pending = null;
    		index = 0;
    	}

    	@Override
    	public boolean hasNext()
    	{
    		//return index < size; 
    		
    		return cursor != tail;
    	}

    	
    	@Override
    	public boolean hasPrevious()
    	{
    		//return index > 0; 
    		
    		return cursor.previous != head;
    	}

 
    	@Override 
    	public PrimeFactor next() 
    	{
    		
    		
    	
    		
    		
    		pending = cursor;
    		
    		
    		cursor = cursor.next;
    		
    		index++;
    		
    		return cursor.previous.pFactor; 
    	}

 
    	@Override 
    	public PrimeFactor previous() 
    	{
    		
    		if(!hasPrevious()) {
    			throw new NoSuchElementException();
    		}
    		
    		pending = cursor.previous;
    		
    		cursor = cursor.previous;
    		
    		--index;
    		
    		
    		
    		return cursor.pFactor; 
    	}

   
    	/**
    	 *  Removes the prime factor returned by next() or previous()
    	 *  
    	 *  @throws IllegalStateException if pending == null 
    	 */
    	@Override
    	public void remove() throws IllegalStateException
    	{
    		
    		//@throws IllegalStateException if pending == null 
    		if(pending == null) {
    			throw new IllegalStateException();
    		}
    		
    		
    		if(pending == cursor) {
    			cursor = pending.next;
    		}
    		
    		//unlink
    		unlink(pending);
    	}
 
 
    	/**
    	 * Adds a prime factor at the cursor position.  The cursor is at a wrong position 
    	 * in either of the two situations below: 
    	 * 
    	 *    a) pf.prime < cursor.previous.pFactor.prime if cursor.previous != head. 
    	 *    b) pf.prime > cursor.pFactor.prime if cursor != tail. 
    	 * 
    	 * Take into account the possibility that pf.prime == cursor.pFactor.prime. 
    	 * 
    	 * Precondition: pf.prime is a prime. 
    	 * 
    	 * @param pf  
    	 * @throws IllegalArgumentException if the cursor is at a wrong position. 
    	 */
    	@Override
        public void add(PrimeFactor pf) throws IllegalArgumentException 
        {
    		
    		
    		
    		//if cursor is in wrong position, following A and B
    		// a)
    		if(pf.prime < cursor.previous.pFactor.prime && cursor.previous != head) {
    			throw new IllegalArgumentException();
    		}
    		// b)
    		else if(pf.prime > cursor.pFactor.prime && cursor != tail) {
    			throw new IllegalArgumentException();
    		}
    		
    		if(pf.prime == cursor.pFactor.prime) {
    			cursor.pFactor.multiplicity += pf.multiplicity;
    		}
    		else {
    			//link @param pf 
    			link(cursor.previous, new Node(pf));
    		}
    		
    		
        }


    	@Override
		public int nextIndex() 
		{
			return index;
		}


    	@Override
		public int previousIndex() 
		{
			return index - 1;
		}

		@Deprecated
		@Override
		public void set(PrimeFactor pf) 
		{
			throw new UnsupportedOperationException(getClass().getSimpleName() + " does not support set method");
		}
        
    	// Other methods you may want to add or override that could possibly facilitate 
    	// other operations, for instance, addition, access to the previous element, etc.
    	// 
    	// ...
    	// 
    }

    
    // --------------
    // Helper methods 
    // -------------- 
    
    /**
     * Inserts toAdd into the list after current without updating size.
     * 
     * Precondition: current != null, toAdd != null
     */
    private void link(Node current, Node toAdd)
    {
    	
    	
    	//assuming none are null
    	
    	current.next.previous = toAdd;
    	
    	toAdd.next = current.next;
    	
    	current.next = toAdd;
    	
    	toAdd.previous = current;
    	
    }

	 
    /**
     * Removes toRemove from the list without updating size.
     */
    private void unlink(Node toRemove)
    {
    	
    	
    	toRemove.next.previous = toRemove.next;
    	
    	
    	toRemove.next.previous = toRemove.previous;
    	
    }


    /**
	  * Remove all the nodes in the linked list except the two dummy nodes. 
	  * 
	  * Made public for testing purpose.  Ought to be private otherwise. 
	  */
	public void clearList()
	{
		
		//head to tail tail to head
		head.next = tail;
		tail.previous = head;
		
		//nothing in the list so size 0
		size = 0;
	}	
	
	/**
	 * Multiply the prime factors (with multiplicities) out to obtain the represented integer.  
	 * Use Math.multiply(). If an exception is throw, assign OVERFLOW to the instance variable value.  
	 * Otherwise, assign the multiplication result to the variable. 
	 * 
	 */
	private void updateValue()
	{
		try {		
			
			long l = 1;
			
			PrimeFactorizationIterator iter = iterator();
			
			while(iter.hasNext()) {
				
				
				
				for(int i = 0; i < iter.cursor.pFactor.multiplicity; i++) {
					l = Math.multiplyExact(l, iter.cursor.pFactor.prime); 
				}
				
				
				
				//l = Math.multiplyExact(iter.cursor.pFactor.prime, iter.cursor.pFactor.multiplicity);
				
				iter.next();
			}
			
			value = l;
			
			
		} 
			
		catch (ArithmeticException e) 
		{
			value = OVERFLOW;
		}
		
	}
}
