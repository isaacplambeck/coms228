package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Isaac Plambeck
 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class Wildlife 
{
	/**
	 * Update the new plain from the old plain in one cycle. 
	 * @param pOld  old plain
	 * @param pNew  new plain 
	 */
	public static void updatePlain(Plain pOld, Plain pNew)
	{
		// TODO 
		// 
		// For every life form (i.e., a Living object) in the grid pOld, generate  
		// a Living object in the grid pNew at the corresponding location such that 
		// the former life form changes into the latter life form. 
		// 
		// Employ the method next() of the Living class. 
		
		
		//x < the old grids width
		for(int x = 0; x < pOld.grid.length; x++) {
			for(int y = 0; y < pOld.grid.length; y++) {
				//using next() method. to update the plain at x y into the new plain
				//pOld.grid[x][y].next(pNew);
				pNew.grid[x][y] = pOld.grid[x][y].next(pNew);
				
				
			}
		}
		
		
		
		
	}
	
	/**
	 * Repeatedly generates plains either randomly or from reading files. 
	 * Over each plain, carries out an input number of cycles of evolution. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{	
		// TODO 
		// 
		// Generate wildlife simulations repeatedly like shown in the 
		// sample run in the project description. 
		// 
		// 1. Enter 1 to generate a random plain, 2 to read a plain from an input
		//    file, and 3 to end the simulation. (An input file always ends with 
		//    the suffix .txt.)
		// 
		// 2. Print out standard messages as given in the project description. 
		// 
		// 3. For convenience, you may define two plains even and odd as below. 
		//    In an even numbered cycle (starting at zero), generate the plain 
		//    odd from the plain even; in an odd numbered cycle, generate even 
		//    from odd. 
		
		Plain even;				 // the plain after an even number of cycles 
		Plain odd;               // the plain after an odd number of cycles
		
		// 4. Print out initial and final plains only.  No intermediate plains should
		//    appear in the standard output.  (When debugging your program, you can 
		//    print intermediate plains.)
		// 
		// 5. You may save some randomly generated plains as your own test cases. 
		// 
		// 6. It is not necessary to handle file input & output exceptions for this 
		//    project. Assume data in an input file to be correctly formated. 
		
		
		//NUMBER 1
		
		//Scanner scan = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
		//scan.close();
		
		//there answer
		int answer = 0;
		int trialCount = 1;
		
		//delete later
		//int gridPlaceHolder = 0;
		int cycles = 0;
		int width = 0;
		String fileName = "No file Name";
		
		
		System.out.println("Simulation of Wildlife of the Plain");
		System.out.println("keys: 1 (random plain) 2 (file input) 3 (exit) ");
		
		//scan.next();
		
		while(answer != 3) {
			
		
		
		System.out.print("Trial " + trialCount + ": ");
		answer = scan.nextInt();
		
		
		if(answer == 1) {
			trialCount++;
			width = 0;
			cycles = 0;
			
			System.out.println("Random plain");
			
			System.out.print("Enter grid width: ");
			width = scan.nextInt();
			//System.out.println(width);
			
			System.out.print("Enter the number of cycles: ");
			cycles = scan.nextInt();
			//System.out.println(cycles);
			
			even = new Plain(width);
			odd = new Plain(width);
			even.randomInit();
			odd.randomInit();
			
			
			System.out.print("\n");
			
			System.out.println("Initial plain:");
			
			System.out.print("\n");
			
			
			//even.randomInit(); //uncomment
			System.out.println(even.toString());
			
			System.out.print("\n");
			
			System.out.println("Final plain:");
			
			System.out.print("\n");
			
			for(int i = 0; i < cycles; i++) {
				if(i % 2 == 0) {
					//System.out.println(width);
					updatePlain(even, odd); //goes to census
				}else {
					updatePlain(odd, even);
				}
			}
			
			if(cycles % 2 == 0) {
				System.out.println(even.toString());
			}
			else {
				System.out.println(odd.toString());
			}
			
			
			
		}
		else if(answer == 2) {
			trialCount++;
			cycles = 0;
			
			
			System.out.println("Plain input from a file");
			
			System.out.print("File name: ");
			//fileName = scan.next() + ".txt";
			fileName = scan.next();
			//System.out.println(fileName);
			
			System.out.print("Enter the number of cycles: ");
			cycles = scan.nextInt();
			//System.out.println(cycles);
			
			System.out.print("\n");
			
			System.out.println("Initial plain:");
			
			System.out.print("\n");
			
			even = new Plain(fileName);
			//odd = new Plain(fileName);
			odd = new Plain(fileName);
			System.out.println(even.toString());
			
			System.out.print("\n");
			
			System.out.println("Final plain:");
			
			System.out.print("\n");
			
			for(int i = 0; i < cycles; i ++) {
				if(i % 2 == 0) {
					updatePlain(even, odd);
					
				}else {
					updatePlain(odd, even);
				}
			}
			
			if(cycles % 2 == 0) {
				System.out.println(even.toString());
				
			}
			else {
				System.out.println(odd.toString());
			}
			
			
			
		}
		else if(answer == 3) {
			break;
		}
		
		
		
		
		}
		
		scan.close();
		
		//scan.close();
		
		
		
		
		
		
		
	}
}
