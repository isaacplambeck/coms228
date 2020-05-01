package edu.iastate.cs228.hw1;

/**
 *  
 * @author Isaac Plambeck
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	public Grass (Plain p, int r, int c) 
	{
		// TODO 
		plain = p;
		row = r;
		column = c;
	}
	
	public State who()
	{
		// DONE  
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for grass. 
		
		//number life forms is 5
		int[] population = new int[NUM_LIFE_FORMS];
						
		//census method on the life forms
		census(population);
						
		//RUNS RULES TO SEE WHAT WILL HAPPEN IN THE SPACE ON THE NEXT CYCLE
		
		//Empty if at least three times as many Rabbits as Grasses in the neighborhood;
		if(population[RABBIT] >= population[GRASS] * 3) {
			return new Empty(pNew, row, column);
		}
		//otherwise, Rabbit if there are at least three Rabbits in the neighborhood;
		else if(population[RABBIT] > 2) {
			return new Rabbit(pNew, row, column, 0);
		}
		//otherwise, Grass.
		else {
			return new Grass(pNew, row, column);
		}

	}
}
