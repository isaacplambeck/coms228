package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Isaac Plambeck
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
	
	// Other private instance variables if you need ... 
		
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		//super abstract sorter
		super(pts);
				
		//equal to type of sorter
		algorithm = "quicksort";
	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		//quicksort starting at 0 to length of point(-1)
		quickSortRec(0, points.length - 1);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		
		
		if(first < last) {
			
			//parition at param
			int p = partition(first, last);
			
			//p-1 at last
			quickSortRec(first, p - 1);
			
			//p+1 at first
			quickSortRec(p + 1, last);
			
			
		}
		
		
		
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last)
	{
		//pivot on last element
		Point pivot = points[last];
		
		//first element - 1 variable (to be incremented in loop)
		int f = first - 1;
		
		for(int i = first; i <= last - 1; i++) {
			//compare
			if(pointComparator.compare(points[i], pivot) < 0) {
				//increment f
				f++;
				//swap
				swap(f, i);
				
				
			}
			
			
			
		}
		//final swap
		swap(f + 1, last);
		
		
		//return f + 1, (which isn't "first")
		return f + 1;
		
		
	}	
		


	
	// Other private methods in case you need ...
}
