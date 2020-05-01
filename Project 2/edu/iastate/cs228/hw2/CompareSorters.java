package edu.iastate.cs228.hw2;

/**
 *  
 * @author Isaac Plambeck
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		// TODO 
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       RotationalPointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	
		RotationalPointScanner[] scanners = new RotationalPointScanner[4]; 
		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() and draw() 
		//        methods in the RotationalPointScanner class.  You can visualize the result of each scan.  
		//        (Windows have to be closed manually before rerun.)  
		// 
		//     c) After all four scans are done for the input, print out the statistics table (cf. Section 2). 
		//
		// A sample scenario is given in Section 2 of the project description. 
		
		
		int trialCount = 1;
		int randPoints = 0;
		int choice = 0;
		String fileName;
		
		Scanner choices = new Scanner(System.in);
		
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
		
		
		while(choice != 3) {
			
			System.out.print("Trial " + trialCount + ": ");
			
			choice = choices.nextInt();
			//System.out.println(choice);
			
			if(choice == 1) {
				trialCount++;
				
				System.out.print("Enter number of random points: ");
				randPoints = choices.nextInt();
				//System.out.println(randPoints);
				
				System.out.println("algorithm size time (ns)");
				System.out.println("----------------------------------");	
				Random rand = new Random();
				
				scanners[0] = new RotationalPointScanner(generateRandomPoints(randPoints, rand), Algorithm.SelectionSort); 
				
				scanners[1] = new RotationalPointScanner(generateRandomPoints(randPoints, rand), Algorithm.InsertionSort); 
				
				scanners[2] = new RotationalPointScanner(generateRandomPoints(randPoints, rand), Algorithm.MergeSort);
				
				scanners[3] = new RotationalPointScanner(generateRandomPoints(randPoints, rand), Algorithm.QuickSort); 

				for(int i = 0; i < scanners.length; i++) {
					scanners[i].scan();
					scanners[i].draw();
				}
				
				System.out.println(scanners[0].stats());
				System.out.println(scanners[1].stats());
				System.out.println(scanners[2].stats());
				System.out.println(scanners[3].stats());
				
				
				System.out.println("----------------------------------");	


				
			}
			
			else if(choice == 2) {
				trialCount++;
				
				
				System.out.println("Points from a file");
				//System.out.print("Enter number of random points: ");
				//randPoints = choices.nextInt();
				//System.out.println(randPoints);
				
				System.out.print("File name: ");
				fileName = choices.next();
				
				
				System.out.println("algorithm size time (ns)");
				System.out.println("----------------------------------");	
				
				
				scanners[0] = new RotationalPointScanner(fileName, Algorithm.SelectionSort); 
				
				scanners[1] = new RotationalPointScanner(fileName, Algorithm.InsertionSort); 
				
				scanners[2] = new RotationalPointScanner(fileName, Algorithm.MergeSort);
				
				scanners[3] = new RotationalPointScanner(fileName, Algorithm.QuickSort); 
				
				for(int i = 0; i < scanners.length; i++) {
					scanners[i].scan();
					scanners[i].draw();
				}
				
				System.out.println(scanners[0].stats());
				System.out.println(scanners[1].stats());
				System.out.println(scanners[2].stats());
				System.out.println(scanners[3].stats());
				
				System.out.println("----------------------------------");	

		
				
			}
			
			else if(choice == 3) {
				break;
			}
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		choices.close();
		
		
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		//IllegalArgumentException if numPts < 1
		if(numPts < 1) {
			throw new IllegalArgumentException();
		}
		
		
		Point[] points = new Point[numPts];
		
		for(int i = 0; i < numPts; i++) {
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		
		return points;
	
	}
	
}
