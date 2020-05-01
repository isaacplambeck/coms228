package edu.iastate.cs228.hw2;

/**
 *  
 * @author Isaac Plambeck
 *
 */

import java.util.Comparator;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later the sorted) sequence. 
 *
 */
public abstract class AbstractSorter
{
	
	protected Point[] points;    // array of points operated on by a sorting algorithm. 
	                             // stores ordered points after a call to sort(). 
	
	protected String algorithm = null; // "selection sort", "insertion sort", "mergesort", or
	                                   // "quicksort". Initialized by a subclass constructor.
		 
	protected Comparator<Point> pointComparator = null;  
	
	private Point referencePoint = null; 	      // common reference point for computing the polar angle 

	
	// Add other protected or private instance variables you may need. 
	

	protected AbstractSorter()
	{
		// No implementation needed. Provides a default super constructor to subclasses. 
		// Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	}
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		
		//throw exception if null or 0
		if(pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}
		
		//new point of pts length and then points to at pts
		points = new Point[pts.length];
		
		for(int i = 0; i < pts.length; i++) {
			points[i] = new Point(pts[i]);
		}
		
		
	}

		
	
	/**
	 * 
	 * @param p
	 * @throws IllegalArgumentException  if p == null
	 */
	public void setReferencePoint(Point p) throws IllegalArgumentException 
	{
		
		//throw if null
		if(p == null) {
			throw new IllegalArgumentException();
		}
		
		//local var to param
		referencePoint = p;
		
		
	}
	

	/**
	 * Generates a comparator on the fly that compares by x-coordinate if order == 0, by y-coordinate
	 * if order == 1, and by polar angle with respect to referencePoint if order == 2. Assign the 
     * comparator to the variable pointComparator. 
     * 
     * If order == 2, the method cannot be called when referencePoint == null.  Call setRereferencePoint()
     * first to set referencePoint. 
	 * 
	 * Need to create an object of the PolarAngleComparator class and call the compareTo() method in the 
	 * Point class.  
	 * 
	 * @param order  0   by x-coordinate 
	 * 				 1   by y-coordinate
	 * 			     2   by polar angle w.r.t referencePoint 
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 2
	 *         IllegalStateException if order == 2 and referencePoint == null; 
	 */
	public void setComparator(int order) throws IllegalArgumentException, IllegalStateException
	{
		// TODO 
		
		Point p = new Point(0,0);
		
		//IllegalArgumentException if order is less than 0 or greater than 2
		if(order < 0 || order > 2) {
			throw new IllegalArgumentException();
		}
		
		//IllegalStateException if order == 2 and referencePoint == null
		if(order == 2 && referencePoint == null) {
			throw new IllegalStateException();
		}
		
		if(order == 2) {
			pointComparator = new PolarAngleComparator(referencePoint);
		}
		else if(order == 1) {
			pointComparator = new PolarAngleComparator(p) {
				
				@Override
				public int compare(Point p1, Point p2) {
					Point.xORy = false;
					return p1.compareTo(p2);
				}
				
			};
		}
		else if(order == 0) {
			pointComparator = new PolarAngleComparator(p) {
				
				@Override
				public int compare(Point p1, Point p2) {
					Point.xORy = true;
					return p1.compareTo(p2);
				}
				
			};
		}
		
		
		
		
		
		
	}

	

	/**
	 * Use the created pointComparator to conduct sorting.  
	 * 
	 * Ought to be protected. Made public for testing. 
	 */
	public abstract void sort(); 
	
	
	/**
	 * Obtain the point in the array points[] that has median index 
	 * 
	 * @return	median point 
	 */
	public Point getMedian()
	{
		return points[points.length/2]; 
	}
	
	
	/**
	 * Copys the array points[] onto the array pts[]. 
	 * 
	 * @param pts
	 */
	public void getPoints(Point[] pts)
	{
		
		//copy array to pts[]
		for(int i = 0; i < points.length; i++) {
			pts[i] = new Point(points[i]);
		}
		
	}
	

	/**
	 * Swaps the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		//2 new points to swap
		Point k = new Point(points[i]);
		Point l = new Point(points[j]);
		
		//swap
		points[i] = l;
		points[j] = k;
		
		
		
	}	
}
