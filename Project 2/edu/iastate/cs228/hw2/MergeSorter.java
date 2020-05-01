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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		//super abstract sorter
		super(pts);
				
		//equal to type of sorter
		algorithm = "mergesort"; 
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		
		//cant be less than 1
		if(pts.length <= 1) {
			return;
		}
		
		
		
		
		Point[] left, right;
		
		
		
		if((pts.length) % 2 == 0) {
			
			left = new Point[pts.length/2];
			right = new Point[pts.length/2];
			
			//left
			for(int i = 0; i < pts.length / 2; i++) {
				left[i] = pts[i];
			}
			
			//right
			for(int i = pts.length / 2, j = 0; i < pts.length; i++) {
				right[j++] = pts[i];
			}
			
		}
		else {
			left = new Point[pts.length/2];
			right = new Point[pts.length - left.length];
			
			
			//left
			for(int i = 0; i < pts.length / 2; i++) {
				left[i] = pts[i];
			}
			
			//right
			for(int i = pts.length - left.length - 1, j = 0; i < pts.length; i++) {
				right[j++] = pts[i];
			}
			
			
		}
		
		
		
		mergeSortRec(left);
		mergeSortRec(right);
		
		int i, j, k;
		i = 0;
		j = 0;
		k = 0;
		
		for(; i < pts.length && j <= left.length && k <= right.length; i++) {
			
			if(j == left.length) {
				pts[i] = right[k++];
			}
			else if(k == right.length) {
				pts[i] = left[j++];
			}
			else if(pointComparator.compare(left[j], right[k]) < 0) {
				pts[i] = left[j++];
			}
			else {
				pts[i] = right[k++];
			}
			
			
			
			
			
		}
		
		
		
		
		
	}

	
	// Other private methods in case you need ...
	//private Point[] merge(Point[] pts) {
	
}
