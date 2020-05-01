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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		//super abstract sorter
		super(pts);
		
		//equal to type of sorter
		algorithm = "selection sort";
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		
		//smallest number
		int small;
		
		for(int i = 0; i < points.length - 1; i++) {
			
			//set smallest to i
			small = i;
			
			for(int j = 1; j < points.length; j++) {
				
				//compare
				if(pointComparator.compare(points[j], points[small]) < 0) {
					//set smallest
					small = j;
					
				}
			}
			//if small is not equal to i then swap
			if(small != i) {
				//swap
				swap(i, small);
			}
		}
		
		
		
		
	}	
}
