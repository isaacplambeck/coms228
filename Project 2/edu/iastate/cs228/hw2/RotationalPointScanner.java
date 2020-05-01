package edu.iastate.cs228.hw2;

import java.io.File;

/**
 * 
 * @author Isaac Plambeck
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array by polar angle with respect to a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class RotationalPointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
	protected String outputFileName;   // "select.txt", "insert.txt", "merge.txt", or "quick.txt"
	
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[]. Set outputFileName. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public RotationalPointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		//set param to local
		sortingAlgorithm = algo;
		
		//IllegalArgumentException if pts == null or pts.length == 0.
		if(pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}
		
		points = new Point[pts.length];//local to length of param
		
		for(int i = 0; i < pts.length; i++) {
			points[i] = new Point(pts[i]);
		}
		
		//set all algorithms to right the according file name
		if(algo.toString() == "SelectionSort") {
			outputFileName = "select.txt";
		}
		else if(algo.toString() == "InsertionSort") {
			outputFileName = "insert.txt";
		}
		else if(algo.toString() == "MergeSort") {
			outputFileName = "merge.txt";
		}
		else if(algo.toString() == "QuickSort") {
			outputFileName = "quick.txt";
		}
		
		
	}

	
	/**
	 * This constructor reads points from a file. Set outputFileName. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected RotationalPointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		// TODO
		
		//set param to local
		sortingAlgorithm = algo;
		
		//set all algorithms to right the according file name
		if(algo.toString() == "SelectionSort") {
			outputFileName = "select.txt";
		}
		else if(algo.toString() == "InsertionSort") {
			outputFileName = "insert.txt";
		}
		else if(algo.toString() == "MergeSort") {
			outputFileName = "merge.txt";
		}
		else if(algo.toString() == "QuickSort") {
			outputFileName = "quick.txt";
		}
		
		
		File file = new File(inputFileName);
		Scanner scan = new Scanner(file);
		
		int num = 0;
		
		//count how many numbers are in the file
		while(scan.hasNextInt()) {
			scan.nextInt();
			num++;
		}
		
		scan.close();
		
		//if the numbers are not even,throw exception
		if(num % 2 != 0) {
			throw new InputMismatchException();
		}
		
		//length num / 2 meaning each point is 2 nums
		points = new Point[num/2];
		
		Scanner scan2 = new Scanner(file);
		
		//put 2 nums into a point
		for(int i = 0; i < num/2; i++) {
			points[i] = new Point(scan2.nextInt(), scan2.nextInt());
		}
		
		scan2.close();
			
		
	}

	
	/**
	 * Carry out three rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates. 
	 *     d) Sort points[] again by the polar angle with respect to medianCoordinatePoint.
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting. Copy the sorting result back onto the array points[] by calling 
	 * the method getPoints() in AbstractSorter. 
	 *      
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		// TODO  
		
		AbstractSorter aSorter; 
		
		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the three 
		// rounds of sorting, have aSorter do the following: 
		if(sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
		}
		else if(sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
		}
		
		if(sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
		}
		else {
			aSorter = new QuickSorter(points);
		}
		
		
		//     a) call setComparator() with an argument 0, 1, or 2. in case it is 2, must have made 
		//        the call setReferencePoint(medianCoordinatePoint) already. 
		//
		//     b) call sort(). 		
		// 
		// sum up the times spent on the three sorting rounds and set the instance variable scanTime. 
		
		//start and end times of the sorting rounds
		long startTime, endTime;
		//x and y for point
		int x, y;
				
		
		//0
		aSorter.setComparator(0); //a
		
		startTime = System.nanoTime();
		aSorter.sort(); //b
		endTime = System.nanoTime();
		
		scanTime += endTime - startTime;//add to time
		
		x = aSorter.getMedian().getX(); //get x
		
		//1
		aSorter.setComparator(1); //a
		
		startTime = System.nanoTime();
		aSorter.sort(); //b
		endTime = System.nanoTime();
		
		scanTime += endTime - startTime; //add to time
		
		y = aSorter.getMedian().getY(); //get y
		
		//set point
		medianCoordinatePoint = new Point(x, y);
		
		//sort w.r.t. mCP
		aSorter.setReferencePoint(medianCoordinatePoint);
		
		//2
		aSorter.setComparator(2); //a
		startTime = System.nanoTime();
		aSorter.sort(); //b
		endTime = System.nanoTime();
		
		//TODO //check later
		scanTime += endTime - startTime;//add to time
		
		
		
		//set points (deep copy)
		for(int i = 0; i <= aSorter.points.length - 1; i++) {
			points[i] = new Point(aSorter.points[i]);
		}
		
		
		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		return sortingAlgorithm.toString() + " " + points.length + " " + scanTime;
	}
	
	
	/**
	 * Write points[] after a call to scan().  When printed, the points will appear 
	 * in order of polar angle with respect to medianCoordinatePoint with every point occupying a separate 
	 * line.  The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		
		String result = null;
		
		for(int i = 0; i < points.length; i++) {
			//in order of polar angle
			//x and y coordinates of the point
			result += Integer.toString(points[i].getX()) + " " + Integer.toString(points[i].getY()) + "\n"; // "\n" = new line
		}
		
		
		return result;
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writePointsToFile() throws FileNotFoundException
	{
		File f = new File(outputFileName);
		PrintWriter pw = new PrintWriter(f);
		
		pw.write(toString()); //write toString()
		pw.close();
	}	

	
	/**
	 * This method is called after each scan for visually check whether the result is correct.  You  
	 * just need to generate a list of points and a list of segments, depending on the value of 
	 * sortByAngle, as detailed in Section 4.1. Then create a Plot object to call the method myFrame().  
	 */
	public void draw()
	{		
		int numSegs = 0;  // number of segments to draw 
		
		//count segs
		for(int i = 0; i < points.length - 1; i++) {
			if(!points[i].equals(points[i + 1])) { //points arent same
				numSegs++;
			}
		}
		
		
		//points at 0 not working for test
		
		if(!points[0].equals(points[points.length - 1])) {
			numSegs++;
		}
		
		
		numSegs += points.length;
		
		

		// Based on Section 4.1, generate the line segments to draw for display of the sorting result.
		// Assign their number to numSegs, and store them in segments[] in the order. 
		Segment[] segments = new Segment[numSegs]; 
		
		//for numSegs extra counting
		int s = numSegs;
		
		for(int i = 0; i < points.length - 1; i++) {
			
			if(!points[i].equals(points[i + 1])) {
				segments[s - 1] = new Segment(points[i], points[i+1]); //new segment at point
				s--;
				
			}
			
		}
		
		//points at 0 not working test
		
		if(!points[0].equals(points[points.length - 1])) {
			segments[s - 1] = new Segment(points[0], points[points.length - 1]);
			s--;
		}
		
		/**
		while(s <= 0) {
			segments[0] = new Segment(medianCoordinatePoint, points[0]);

		}
		
		**/
		//at median
		for(int i = 0; i < points.length; i++) {
			segments[i] = new Segment(medianCoordinatePoint, points[i]);
		}
		
		
		
		
		String sort = null; 
		
		switch(sortingAlgorithm)
		{
		case SelectionSort: 
			sort = "Selection Sort"; 
			break; 
		case InsertionSort: 
			sort = "Insertion Sort"; 
			break; 
		case MergeSort: 
			sort = "Mergesort"; 
			break; 
		case QuickSort: 
			sort = "Quicksort"; 
			break; 
		default: 
			break; 		
		}
		
		// The following statement creates a window to display the sorting result.
		Plot.myFrame(points, segments, sort);
		
	}
		
}
