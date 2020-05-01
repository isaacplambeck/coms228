package edu.iastate.cs228.hw5;


import java.io.FileNotFoundException;
import java.util.Scanner; 

/**
 *  
 * @author Isaac Plambeck
 *
 */

/**
 * 
 * The Transactions class simulates video transactions at a video store. 
 *
 */
public class Transactions 
{
	
	/**
	 * The main method generates a simulation of rental and return activities.  
	 *  
	 * @param args
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws FileNotFoundException
	{	
		// 
		// 1. Construct a VideoStore object.
		
		
		//CONSTRUCTION OF VIDEOSTORE OBJECT:
		VideoStore vs = new VideoStore("videoList1.txt");
		
		
		// 2. Simulate transactions as in the example given in Section 4 of the 
		//    the project description. 
		
		int answer = 0;
		boolean end = false;
		//int count = 1;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Transactions at a Video Store");
		System.out.println("keys: 1 (rent)     2 (bulk rent)\n      3 (return)   4 (bulk return)\n      5 (summary)  6 (exit)");
		
		
		
		
		
		
		while(end != true) {
			
			System.out.print("Transaction: ");
			
			answer = scan.nextInt();
			
			
			if(answer == 1) {
				
				scan.nextLine();
				
				System.out.print("Film to rent: ");
				String line = scan.nextLine();
				String name = vs.parseFilmName(line);
				int copies = vs.parseNumCopies(line);
				
				
				try {
					vs.videoRent(name, copies);
				} catch (IllegalArgumentException | FilmNotInInventoryException | AllCopiesRentedOutException e) {
					System.out.println(e.getMessage());
				}
				
				
				System.out.print("\n");
				
				
			}
			else if(answer == 2) {
				
				System.out.print("Video file (rent): ");
				
				try {
					vs.bulkRent(scan.next());
				} catch (IllegalArgumentException | FilmNotInInventoryException | AllCopiesRentedOutException e) {
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
				
				
				//System.out.print("\n");
				
			}
			else if(answer == 3) {
								
				scan.nextLine();
				
				System.out.print("Film to return: ");
				String line = scan.nextLine();
				String name = vs.parseFilmName(line);
				int copies = vs.parseNumCopies(line);
				
				//System.out.println(line + name + copies);
				
				try {
					vs.videoReturn(name, copies);
				} catch (IllegalArgumentException | FilmNotInInventoryException e) {
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
				
				System.out.print("\n");

				
			}
			else if(answer == 4) {
				
				System.out.print("Video file (return): ");
				
				try {
					vs.bulkReturn(scan.next());
				} catch (IllegalArgumentException | FilmNotInInventoryException e) {
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
				
				
				//System.out.print("\n");
				
			}
			else if(answer == 5) {
				
				System.out.println(vs.transactionsSummary());
				
			}
			else if(answer == 6) {
				end = true;
				break;
			}
			
			
			
			
			
		}
		
		scan.close();
		
		
	}
}
