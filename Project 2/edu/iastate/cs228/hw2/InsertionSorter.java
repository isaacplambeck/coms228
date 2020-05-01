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
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	// Other private instance variables if you need ... 
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		//super abstract sorter
		super(pts);

		//equal to the type of sorter
		algorithm = "insertion sort";
		
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{
		
		//where the point is being moved to
		int l = 0;
		
		
		for(int i = 1; i < points.length; i++) {
			//new point
			Point k = points[i];
			l = i;
			
			for(int j = i-1; j >= 0; j--) {
				//compare
				if(pointComparator.compare(k, points[j]) < 0) {
					//move
					l = j;
					//swap
					swap(l, i);
					//place
					k = points[l];
				}
				
				
				
			}
			
			
			
			
		}
		
		
		
	}		
}
