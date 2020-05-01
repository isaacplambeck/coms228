package edu.iastate.cs228.hw1;

/**
 *  
 * @author Isaac Plambeck
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	/**
	 * Creates a Rabbit object.
	 * @param p: plain  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (Plain p, int r, int c, int a) 
	{
		// DONE
		plain = p;
		row = r;
		column = c;
		age = a;
	}
		
	// Rabbit occupies the square.
	public State who()
	{
		// TODO  
		return State.RABBIT;
	}
	
	/**
	 * A rabbit dies of old age or hunger. It may also be eaten by a badger or a fox.  
	 * @param pNew     plain of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a rabbit. 
		
		//number life forms is 5
		int[] population = new int[NUM_LIFE_FORMS];
						
		//census method on the life forms
		census(population);
						
		//RUNS RULES TO SEE WHAT WILL HAPPEN IN THE SPACE ON THE NEXT CYCLE
		
		//Empty if the Rabbit’s current age is 3;
		if(age >= RABBIT_MAX_AGE) {
			return new Empty(pNew, row, column);
		}
		//otherwise, Empty if there is no Grass in the neighborhood (the rabbit needs food);
		else if(population[GRASS] == 0) {
			return new Empty(pNew, row, column);
		}
		//otherwise, Fox if in the neighborhood there are at least as many Foxes and Badgers combined as Rabbits, 
		//and furthermore, if there are more Foxes than Badgers;
		else if(population[FOX] + population[BADGER] >= population[RABBIT] && population[FOX] > population[BADGER]) {
			return new Fox(pNew, row, column, 0);
		}
		//otherwise, Badger if there are more Badgers than Rabbits in the neighborhood;
		else if(population[BADGER] > population[RABBIT]) {
			return new Badger(pNew, row, column, 0);
		}
		//otherwise, Rabbit (the rabbit will live on).
		else {
			return new Rabbit(pNew, row, column, age + 1);
		}
 
	}
}
