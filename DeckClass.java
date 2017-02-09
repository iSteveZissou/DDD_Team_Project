import java.util.*;
import java.io.*;
import javax.swing.*;

/**  Class that maintains a list of CardClass objects which
 *   corresponds to the deck in the game.
 */
public class DeckClass {
//
    /** Class Constants */
    private static final int TOTAL_CARDS = 40;

	/** Instance Variables */
	private CardClass [] deck;

	private int numCards;
	private int count = 0;
	private String categories;

	/** Non-Default Constructor */
	public DeckClass() {
		this.readText();
	}

	/** 
	*   Shuffles the CardClass objects in the deck array at random.
	*   The method loops through the deck array and swaps each Card
	*   with a random Card in the array. 
	*/
	public void shuffle() {

		Random rand = new Random();

		//Shuffle algorithm
		for (int i = 0; i<deck.length; i++){
			int random = Math.abs(rand.nextInt()) % deck.length;
			CardClass temp = deck[i]; 
			deck[i]  = deck[random];
			deck[random] = temp; 	
		}

 	 }
		
	

	/**
	*   Deal the cards in the deck to the players of the game.
	*   If the cards do not divide up equally, the program
	*   chooses a player at random to give the extra cards to.
	*   @param arrayUser - array of the users in the game
	*/
		public void dealCards(UserClass[] arrayUser) {

		int count = 0;

		Random rand = new Random();
		int randomPlayer = rand.nextInt(3);
		
		//How cards are dealt out when there are 5 players
		if (arrayUser.length == 5){
			int i = 0;
			while ( i < deck.length){
				for (int j =0; j<arrayUser.length; j++) {
					arrayUser[j].addCard(deck[i++]);
				}
			}
		}

		//How cards are dealt out when there are 4 players
		else if (arrayUser.length == 4){
			int i = 0;
			while ( i < deck.length){
				for (int j=0; j<arrayUser.length; j++) {
					arrayUser[j].addCard(deck[i++]);
				}
			}
		}

		//How cards are dealt out when there are 3 players
		else if (arrayUser.length == 3){
			int i = 0;
			while ( i < deck.length){
				//Selects random player to give the extra card
				if (i == deck.length -1){
					arrayUser[randomPlayer].addCard(deck[i]);
					break;
				}
				for (int j=0; j<arrayUser.length; j++) {
					arrayUser[j].addCard(deck[i++]);
				}
				
			}
	 	}

	 	//How cards are dealt out when there are 3 players	
	 	else if (arrayUser.length ==2){
	 		
	 		int i = 0;
			while ( i < deck.length){
				for (int j=0; j<arrayUser.length; i++) {					
					arrayUser[j].addCard(deck[i++]);

				}
 			}
		}
	 	this.clear();
	}

	/**
	* Returns the total number of cards on the deck 
	* @return total number of cards on deck
	*/
	public int getDeckCount() {
		return count;
	}


	/** 
	*  Adds a Card to the deck
	*  @param crd - card object to be added
	*/
	public void addCard(CardClass crd) {
		deck[count] = crd;
		count++;
	}

	/**
	*  Clears the deck of all cards
	*/
	public void clear() {
		deck = new CardClass[TOTAL_CARDS];
	 	count = 0;
	 }
	
	 /** 
	  * Splits the String category, produced by the read 
	  * in textfile, into an array of single String category names 
	  *	@return String array of the category names
	  */
	public String [] getCategories(){
		String [] tokens = categories.split(" ");
		return tokens;
	}

	/** 
	 *  Reads the textFile in and converts the information format appriopriately
	 *  so it may be used to instantiate CardClass objects
	 *  and construct the deck.
	 */
	private void readText() {

		deck = new CardClass[TOTAL_CARDS];
	
		String line = "";
		//Text file contains extra line with descriptions 
		//hence array size of TOTAL_CARDS+1
		String [] arrayLine = new String[TOTAL_CARDS+1];
		String s ="";

		int t = 0;
		try 
		{
			FileReader fr = new FileReader("deck2.txt");
			Scanner in = new Scanner(fr);
			
			System.out.println("\nTEST 1: READ IN & PRINT CARDS:\n");

			int numCards=0;
			while (in.hasNextLine())
			{	
				s = in.nextLine();

				//TEST 1
				System.out.println(s);

				arrayLine[numCards] = s;
				numCards++;
				t++;
			}
			
		

			

			// first line contains all the category names
			categories = arrayLine[0];
		

			// Create the deck with Card object array
			// skip the first line of arrayLine as it
			// holds descriptions and is not a card
			for (int index = 1; index < 41; index++){

				deck[index-1] = new CardClass(arrayLine[index]);

			}

			this.shuffle();

			System.out.println("Get Intel " +deck[0].getDescription());
			in.close();
		
			for (int j = 0; j< 40; j++){
				System.out.println(deck[j]);
			}

			System.out.println("The array size IS::" + deck.length);

		
		}
		//Catch statement if the file is not found
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "File not found", 
				"INPUT ERROR", JOptionPane.ERROR_MESSAGE);
		}		
	}

	/**
	*  Returns all Card objects in the deck
	*  @param array of all card objects left in the deck
	*/
	public CardClass[] getPile(){

		//System.out.println( " this is the pile count " + count);
		CardClass [] pile = new CardClass[count];

		for (int i=0; i< count; i++){
			pile[i] = deck[i];
		}
		return pile;
	}
}