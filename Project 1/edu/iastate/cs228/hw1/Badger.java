package edu.iastate.cs228.hw1;

/**
 *  
 * @author Isaac Plambeck
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	
	
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (Plain p, int r, int c, int a) 
	{
		// DONE
		plain = p;
		row = r;
		column = c;
		age = a;
		
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		// DONE
		return State.BADGER; 
	}
	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// To be implemented in the classes Badger, Empty, Fox, Grass, and Rabbit. 
		// 
		// For each class (life form), carry out the following: 
		// 
		// 1. Obtains counts of life forms in the 3x3 neighborhood of the class object. 

		// 2. Applies the survival rules for the life form to determine the life form  
		//    (on the same square) in the next cycle.  These rules are given in the  
		//    project description. 
		// 
		// 3. Generate this new life form at the same location in the plain pNew.  

		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a badger. 
		
		// TODO 
		
		
		//number life forms is 5
		int[] population = new int[NUM_LIFE_FORMS];
		
		//census method on the life forms
		census(population);
		
		//RUNS RULES TO SEE WHAT WILL HAPPEN IN THE SPACE ON THE NEXT CYCLE
		
		
		//Empty if the Badger is currently at age 4;
		if(age >= BADGER_MAX_AGE) {
			return new Empty(pNew, row, column);
		}
		//Fox, if there is only one Badger but more than one Fox in the neighborhood;
		else if(population[FOX] > 1 && population[BADGER] == 1) {
			return new Fox(pNew, row, column, 0);
		}
		//Empty, if Badgers and Foxes together out number Rabbits in the neighborhood;
		else if(population[BADGER] + population[FOX] > population[RABBIT]) {
			return new Empty(pNew, row, column);
		}
		//otherwise, Badger (the badger will live on).
		else{
			return new Badger(pNew, row, column, age + 1);
		}
		
		
		
	
	}
}
