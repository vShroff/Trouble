import java.util.Arrays;
import java.lang.Math;
import java.util.Scanner;
import java.util.Random;


class Player
{
    
	private int num;			 // Player number, 1 or 2
	private int[] arrayOfpegs;	 // This keeps track of the pegs of each player
    private boolean firstroll;	 //A marker to determine if this is the first time the player rolled a 6.
    
	public Player(int num)
	{
		this.num = num;
		this.arrayOfpegs = new int[] { -6, -6, -6, -6 };
		this.firstroll = false;
												// Initialized all the elements of the peg array to -6,
												// so that when the player first rolls a 6, 6 + (-6) = 0, and
												// player starts at position 0
	}

	public int getNum() 
	{ 	// gets player number
		return this.num;
	}

	public String getPegs()
	{										// converting array to string for display purposes 
		return Arrays.toString(this.arrayOfpegs);
	}

	public void movePegforward(int pnum, int spacesTowalk)
	{										//moving peg from start to following spaces
		int[] currentpeg = this.arrayOfpegs;
		currentpeg[pnum - 1] += spacesTowalk;
		this.arrayOfpegs = currentpeg;
	}

	
	public void movebackTohome(int pNum)
	{								//moves peg back to home position i.e. -6, if another player lands on the same position as player 1
		int[] currentpeg = this.arrayOfpegs;
		currentpeg[pNum - 1] = -6;
		this.arrayOfpegs = currentpeg;
	}
	
	public void marker()
	{										//function, which when called marks the marker as true, i.e. if the player has rolled at least once.
		this.firstroll = true;
	}
	
	public boolean getmarker()
	{
		return this.firstroll;
	}
	public int[] getpegArray()
	{												// gets the entire array of pegs for comparison purposes
		int[] myarray = this.arrayOfpegs;		
		return myarray;
	}	
}
class Board {
	private boolean FLAG = false;
	public void game() {
		
		Player person1 = new Player(1); 
		Player person2 = new Player(2); 				//Instantiated two player objects, person1 and person2

		Scanner sc = new Scanner(System.in);
		
		Player currentplayer = person1; 				//Automatically lets person1 start the game
		Player otherplayer;
		int winarray[] = {32,31,30,29}; //this signifies the final position of each player to win the game
		String win= Arrays.toString(winarray);
		
		while (!FLAG) {
			Random rand = new Random(); //COMMENT THIS LINE TO EXECUTE THE TEST CODE BELOW
			int dieRoll = rand.nextInt(6) + 1; 	//COMMENT THIS LINE TO EXECUTE THE TEST CODE BELOW		
			//Randomly generates a number between 1-6 for current player
		
//	//******************************************************************************************************//
//	//*****************************************************************************************************//
//	//DOCUMENTATION - PLEASE USE THIS CODE FOR USER TO ORCHESTRATE THE CODE 
//	//Also, PLease comment  the above two lines of code  before executing to prevent errors
//		System.out.println("Enter what you want to be rolled between 1-6, [This is just to test code] ");	
//		int dieRoll = sc.nextInt();
//
//	//****************************************************************************************************//
//	//*****************************************************************************************************//

			System.out.println("Player " + currentplayer.getNum() + "\n");
			System.out.println("Rolled " + dieRoll + "\n");

			// CASE 1, if player rolls a 6
			if (dieRoll == 6) {
				
				currentplayer.marker(); //marks that this first roll has been done
				
				//In THIS CASE, Player gets the freedom to move any peg
				
				System.out.println("Which peg 1,2,3 or 4 will you move from home? "  
						+ currentplayer.getPegs() + " ");

				int pegNum = sc.nextInt();    //player enters a number
				
				currentplayer.movePegforward(pegNum, dieRoll);   //peg moves to home
				System.out.println("Player " + currentplayer.getNum() + "'s pegs status at home " + currentplayer.getPegs()); 
				
				
				//IN every CASE, after every move I am checking if the player has won
				if(currentplayer.getPegs()== win)  //Everytime a player moves forward, we check to see if he wins
				{
					System.out.println(currentplayer+ " wins!! Game Over");
					System.exit(0);
				}
				
				//----------------------------------------------------------------
				otherplayer= toggle(currentplayer, person1, person2); //now we check if it landed where the other player is by comparing
				//both players array functions
				
				//SUB CASE
				//comparing if both players land at the same place, 
				//by storing both players array into different arrays and traversing through their positions
				int other[] = otherplayer.getpegArray(); 
				int curr[]= currentplayer.getpegArray();
				
				for(int i=0;i<4;i++)
				{
					for(int j=0; j<4; j++)
					if(Math.abs((other[i]) - (curr[j]))==14) //in this case I assume that the two players started 14 spaces apart
						//therefore, I take the difference of abs value, which if =14, would mean one player goes back home
					{
						System.out.println("Whooops goes back home!! ");
						otherplayer.movebackTohome(pegNum); //if they are at the same place, the previous/other player goes back home
						break;
					}
				}
				//-------------------------------------------------------------

				//SUB CASE
				//current player rolls again
				System.out.println("Alrighty, now Player " + currentplayer.getNum()+ " rolls again! ");
				
				Random rand2 = new Random(); //COMMENT THIS LINE TO EXECUTE THE TEST CODE BELOW
				int dieRoll2 = rand2.nextInt(6) + 1; //COMMENT THIS LINE TO EXECUTE THE TEST CODE BELOW
				//Randomly generates a number between 1-6
				
				
//				//***********************************************************************************************//
//				//***********************************************************************************************//
//				//DOCUMENTATION - PLEASE USE THIS CODE FOR USER TO ORCHESTRATE THE CODE
//				//Also, PLease comment  the above two lines of code  before executing to prevent errors		
//					System.out.println("Enter what you want to be rolled between 1-6, [this is just to test code]");	
//					int dieRoll2 = sc.nextInt();
//				//************************************************************************************************//
//				//************************************************************************************************//

				System.out.println("Player " + currentplayer.getNum() + "\n");
				System.out.println("Rolled " + dieRoll2 + "\n");
				
				
				//asking the player which pegs they want to move and giving them possible peg options they can move
				System.out.print("Which peg will you move from home? "+  currentplayer.getPegs() + "\n"+ "You can chose position ");
				
				
				//displaying the possible positions the player can move so that the user does not enter an invalid input
				int mycurrent[]= currentplayer.getpegArray();
				for( int i=0;i<4;i++)
				{
					if(mycurrent[i] != -6)
					{
						System.out.print((i+1) + ",");
					}
				}
				
				
				int pegy = sc.nextInt(); 
				
				
				//move forward, that many spaces
				
				currentplayer.movePegforward(pegy, dieRoll2);
				System.out.println("Player " + currentplayer.getNum() + "'s pegs status at home " + currentplayer.getPegs()); 
				
				if(currentplayer.getPegs()== win) //Everytime a player moves forward, we check to see if he wins
				{
					System.out.println(currentplayer+ " wins!! Game Over");
					System.exit(0);
				}
			
				//Reasoning for the block below is same as above
				otherplayer= toggle(currentplayer, person1, person2);
				
				//we check to see if the current and other player landed on the same positons
				// by measuring the difference of the abs value of their positions
				
				int other1[] = otherplayer.getpegArray();
				int curr1[]= currentplayer.getpegArray();
				
				//comparing the positions of both the players
				for(int i=0;i<4;i++)
				{
					for(int j=0; j<4; j++)
					//if(c[i] == d[j] && c[i] != -6 && d[j]!=-6)
						if(Math.abs((other1[i]) - (curr1[j]))==14)
					{
					   System.out.println("Whoooops! goes back home!! ");
						otherplayer.movebackTohome(pegNum);
						break;
					}
				}
				
				//now calling toggle function for other player to roll
				System.out.println("Now, finally other player's turn! ");
				currentplayer = toggle(currentplayer, person1, person2);
			
	
				System.out.println("[Press Y to continue, N to exit]");
				String key = sc.next();

				if (key.equals("N")) {
					//System.exit(0);
					FLAG = true;
				}
				
				
				
			} 
			
		else {
			
			//CASE 2 player did not roll a six, but has one or more pegs outside home 
			if(currentplayer.getmarker() == true) //indicates player has rolled a 6 before
			{
				
			//Same procedure happens
				System.out.println("Which peg will you move from home? "+ currentplayer.getPegs() + "\n" + "You can chose position ");
				
				//displaying which position the player can move, we check if the peg is not at home
				int mycurrent2[]= currentplayer.getpegArray();
				for( int i=0;i<4;i++)
				{
					if(mycurrent2[i] != -6)
					{
						System.out.print((i+1) + ",");
					}
				}
				
				

				int pegNum = sc.nextInt(); 
				
			    //move that many spaces and diplay the peg status
				
				currentplayer.movePegforward(pegNum, dieRoll);
				System.out.println("Player " + currentplayer.getNum() + "'s pegs status at home " + currentplayer.getPegs()); 
				
				
				//checks to see if the person won
				if(currentplayer.getPegs()== win)
				{
					System.out.println(currentplayer+ " wins!! Game Over");
					System.exit(0);
				}
				
			
				otherplayer= toggle(currentplayer, person1, person2);
				
				//Again a CASE to check if they both landed on the same spot
				int other3[] = otherplayer.getpegArray();
				int curr3[]= currentplayer.getpegArray();
			
				for(int i=0;i<4;i++)
				{
					for(int j=0; j<4; j++)
					//if(e[i] == f[j] && e[i] != -6 && f[j]!= -6)
						if(Math.abs((other3[i]) - (curr3[j]))==14)
					{
						System.out.println("Whooops! goes back home!! ");
						otherplayer.movebackTohome(pegNum);
						break;
					}
				}
				
				
				currentplayer = toggle(currentplayer, person1, person2);
				
				//firstroll = false;
	
				System.out.println("[Press Y to continue, N to exit]");
				String key = sc.next();

				if (key.equals("N")) {
					//System.exit(0);
					FLAG = true;
				}
				
			}
			//CASE 3, neither a 6, nor any peg outside home, other player's turn	
			else 
				
				{
				//Didn't role a 6 nor has any of the pegs outside home
				System.out.println("Whoops CANNOT MOVE, other player's turn! ");
				currentplayer = toggle(currentplayer, person1, person2); 

				System.out.println("[Press Y to continue, N to exit]");
				String key = sc.next();

				if (key.equals("N")) {
					FLAG = true;
				}
				
			}
			}
		}
//if player exits out, it gives out the peg status of the player
		System.out.println("\nP1 pegs: " + person1.getPegs());
		System.out.println("P2 pegs: " + person2.getPegs());
	}

	public Player toggle(Player Person, Player person1, Player person2) { 
		
		// to switch between players
		if (person1.getNum() == Person.getNum()) {
			return person2;
		} else {
			return person1;
		}
	}
}


public class Trouble {
																	// This will call all the functions for each player

	public static void main(String[] args) {

		Board GameBoard = new Board();

		System.out.println("Welcome to Trouble!!");
		System.out.println("INSTRUCTIONS");
		System.out.println("Each time a player rolls a die, the peg array is displayed in the "
				+ "form of [num1, num2, num3, num4] \n"
				+ "This array gives the position of each peg in the board \n" 
				+ "For example, [-6, -6, 0, 4] means that \n" 
				+ "First 2 pegs are at home, third peg is at the start and the fourth peg is at position 4 with respect to the start \n"
				+ "Each time a player land on another players position, the other player goes back home i.e. at -6 \n"
				+ "I have assumed that players start the game 14 spaces apart \n"
				+ "And the game has 32 spaces in total, in order to finish the game, \n"
				+ "The first person to get array [32,31,30,29] wins! \n \n");
		
		//IMPORTANT NOTE:
		//FIRST PLAYER CUTS SECOND PLAYER IF HE REACHES 14 MORE THAN HIS OWN POSITION SINCE,
		//I AM ASSUMING THE PLAYERS START 14 POSITIONS APART

		GameBoard.game();

	}
}
