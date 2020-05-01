package edu.iastate.cs228.hw1;

/**
 *  
 * @author Isaac Plambeck
 *
 */

/**
 * A fox eats rabbits and competes against a badger. 
 */
public class Fox extends Animal 
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Fox (Plain p, int r, int c, int a) 
	{
		// DONE
		
		plain = p;
		row = r;
		column = c;
		age = a;
		
		
	}
		
	/**
	 * A fox occupies the square. 	 
	 */
	public State who()
	{
		// DONE
		return State.FOX; 
	}
	
	/**
	 * A fox dies of old age or hunger, or from attack by numerically superior badgers. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a fox. 
		
		//number life forms is 5
		int[] population = new int[NUM_LIFE_FORMS];
						
		//census method on the life forms
		census(population);
						
		//RUNS RULES TO SEE WHAT WILL HAPPEN IN THE SPACE ON THE NEXT CYCLE
		
		
		//Empty if the Fox is currently at age 6;
		if(age >= FOX_MAX_AGE) {
			return new Empty(pNew, row, column);
		}
		//otherwise, Badger, if there are more Badgers than Foxes in the neighborhood;
		else if(population[BADGER] > population[FOX]) {
			return new Badger(pNew, row, column, 0);
		}
		//otherwise, Empty, if Badgers and Foxes together out number Rabbits in the neighborhood;
		else if(population[BADGER] + population[FOX] > population[RABBIT]) {
			return new Empty(pNew, row, column);
		}
		//otherwise, Fox (the fox will live on).
		else {
			return new Fox(pNew, row, column, age + 1);
		}
		
	}
}
