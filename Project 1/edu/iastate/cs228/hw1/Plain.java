package edu.iastate.cs228.hw1;

/**
 *  
 * @author Isaac Plambeck
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner; 
import java.util.Random; 

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain 
{
	private int width; // grid size: width X width 
	
	public Living[][] grid; 
	
	/**
	 *  Default constructor reads from a file 
	 */
	public Plain(String inputFileName) throws FileNotFoundException
	{		
        // TODO 
		// 
		// Assumption: The input file is in correct format. 
		// 
		// You may create the grid plain in the following steps: 
		// 
		// 1) Reads the first line to determine the width of the grid.
		// 
		// 2) Creates a grid object. 
		// 
		// 3) Fills in the grid according to the input file. 
		// 
		// Be sure to close the input file when you are done. 
		
		
		
		File f = new File(inputFileName);
		
		Scanner scanWidth = new Scanner(f);
		
		
		
		
		//scanning the width by counting the rows
		//Number 1.
		width = 0;
		
		while(scanWidth.hasNextLine()) {
			
			width++;
			scanWidth.nextLine();

		}
		
		
		scanWidth.close();
		
		
		
		
		//Number 2.
		grid = new Living[width][width];
		
		
		//Number 3.
		Scanner scan = new Scanner(f);
		//grid = new Living[width][width]
		
		
			
			for(int x = 0;x < width; x++) {
				
				for(int y = 0; y < width; y++) {
					
					String h = scan.next();
					
					if(h.length() == 1){
						//char at location is = to the living that starts with that char.
						if(h.charAt(0) == 'G') {
							
							grid[x][y] = new Grass(this, x, y);
							
							//State = State.GRASS;
						}
						else if(h.charAt(0) == 'E') {
							grid[x][y] = new Empty(this, x, y);
						}
						
					}
					
					//else if(h.length() == 2) {
					else {
							//the livings with an age value next to the char
						
						int age = Integer.parseInt(String.valueOf(h.charAt(1)));
						
						if(h.charAt(0) == 'B') {
							grid[x][y] = new Badger(this, x, y, age);
						}
						else if(h.charAt(0) == 'F') {
							grid[x][y] = new Fox(this, x, y, age);
						}
						else if(h.charAt(0) == 'R') {
							grid[x][y] = new Rabbit(this, x, y, age);
						}
						
					}
					
					
				}
				
				
			}
			

			
		
		
		
		
		scan.close();
		

		
	}
	
	
	/**
	 * Constructor that builds a w x w grid without initializing it. 
	 * @param width  the grid 
	 */
	public Plain(int w)
	{
		// DONE FOR NOW
		width = w;
		//grid = new Living[w][w];
		
		
	}
	
	//DONE
	public int getWidth()
	{
		// DONE
		return width;  // to be modified 
	}
	
	/**
	 * Initialize the plain by randomly assigning to every square of the grid  
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit()
	{
		
		
		
		Random generator = new Random(); 
		 
		grid = new Living[width][width];
		
		
		
		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid.length; y++)
			{
				
				
				int rand = generator.nextInt(5);
				
				if(rand == 0) {
					grid[x][y]= new Badger(this, x, y, 0);
				}
				
				else if(rand == 1) {
					grid[x][y] = new Empty(this, x, y);
				}
				
				else if(rand == 2) {
					grid[x][y]= new Fox(this, x, y, 0);
				}
				
				else if(rand == 3) {
					grid[x][y] = new Grass(this, x, y);
				}
				
				else if(rand == 4) {
					grid[x][y] = new Rabbit(this, x, y, 0);
				}
			}
		}
	}
	
	
	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal 
	 * followed by a blank space; otherwise, output two blanks.  
	 */
	public String toString()
	{
		// TODO
		
		
		
	
		
		String output = "";
		int animalAge = 0;
		String nl = " \n";
		
		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid[x].length; y++)
			{
				
				if(grid[x][y].who() == State.BADGER) {
					animalAge = ((Animal)grid[x][y]).myAge();
					
					if(width - 1 == y) {
						output = output + "B" + animalAge + nl;
					}
					else {
						output = output + "B" + animalAge + " ";
					}
					
					
					
				}
				
				else if(grid[x][y].who() == State.EMPTY) {
					if(width - 1 == y) {
						output = output + "E" + nl;
					}
					else {
						output = output + "E" + "  ";
					}
					
					
				}
				
				else if(grid[x][y].who() == State.FOX) {
					animalAge = ((Animal)grid[x][y]).myAge();
					
					if(width - 1 == y) {
						output = output + "F" + animalAge + nl;
					}
					else {
						output = output + "F" + animalAge + " ";
					}
					
				}
				
				else if(grid[x][y].who() == State.GRASS) {
					if(width - 1 == y) {
						output = output + "G" + nl;
					}
					else {
						output = output + "G" + "  ";
					}
					
				}
				
				else if(grid[x][y].who() == State.RABBIT) {
					animalAge = ((Animal)grid[x][y]).myAge();
					if(width - 1 == y) {
						output = output + "R" + animalAge + nl;
					}
					else {
						output = output + "R" + animalAge + " ";
					}
					
				}
				
				
				
				
			}
			
		}
		
		
		
		
		
		return output; 
	}
	

	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly 
	 * generated plain for debugging purpose. 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException
	{
		// DONE
		// 
		// 1. Open the file. 
		// 
		// 2. Write to the file. The five life forms are represented by characters 
		//    B, E, F, G, R. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 
		// 3. Close the file. 
		
		File f = new File(outputFileName);
		
		PrintWriter w = new PrintWriter(f);
		
		w.print(toString());
		
		w.close();
		
	}			
}
