 /** Maintains a list of CardClass objects.
 *	 The methods allow CardClass objects to be added and deleted from the list
 *   according to when Cards are added and deleted from the communal deck.
 */
import java.util.*;
import java.io.*;
import javax.swing.*;

public class DeckClass {
//
    /** Class Constants */
    private static final int TOTAL_CARDS = 40;

	/** Instance Variables */
	private CardClass [] deck;

	private int numCards;
	private int count = 0;
	private String categories;

	/** Non-Default Constructor	 */
	public DeckClass() {
		this.readText();
	}



	/** 
	*   Shuffles the CardClass objects in the deck array at random.
	*   The method loops through the deck array and swaps each Card
	*   with a random Card. 
	*/
	public void shuffle() {

		Random rand = new Random();
		for (int i = 0; i<deck.length; i++){
			int random = Math.abs(rand.nextInt()) % deck.length;
			CardClass temp = deck[i]; 
			deck[i]  = deck[random];
			deck[random] = temp; 	
		}

  }
		
	

	/**
	*   Deal the Cards in the deck to the players of the game
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
				// Selects random player to give the extra card??
				if (i == deck.length -1){
					arrayUser[randomPlayer].addCard(deck[i]);
					break;
				}
				for (int j=0; j<arrayUser.length; j++) {
					arrayUser[j].addCard(deck[i++]);
				}
				
			}
	 	}	
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

	/** Return total number of cards on the communal pile 
	*   @return total number of cards on the communal pile
	*/
	public int getDeckCount() {
		return count;
	}


	/** Adds a Card to the communal pile 
	*	@param crd - card to be added
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
	  * Splits the category string into an array of single category names 
	  *	@return array of the category names
	  */
	public String [] getCategories(){
		String [] tokens = categories.split(" ");
		return tokens;
	}

	/** 
	 *  Reads the textFile and converts it into the appropriate format 
	 *  to create Card's and construct the deck
	 */
	private void readText() {

		 deck = new CardClass[TOTAL_CARDS];
	
		String line = "";
		String [] arrayLine = new String[41];
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
			
		

			

			// first line containing all the categorie names
			categories = arrayLine[0];
		

			// Create deck cards array from the string array of cards
			// skip the first
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

		//Close the scanner
		
	}

	public CardClass[] getPile(){

		System.out.println( " this is the pile count " + count);
		CardClass [] pile = new CardClass[count];

		for (int i=0; i< count; i++){
			pile[i] = deck[i];


		}
		return pile;
	}


}