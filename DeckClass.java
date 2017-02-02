 /** Maintains a list of CardClass objects
 * The methods allow objects to be added and deleted from the list
 *
 */
import java.util.*;
import java.io.*;
import javax.swing.*;

public class DeckClass {

    /** Class Constants */
    private static final int TOTAL_CARDS = 40;

	/** Instance Variables */
	private CardClass [] deck;

	private int numCards;
	private int count = 0;
	private String categories;

	/** Default Constructor */
	public DeckClass() {

		this.readText();
		


		// //Fill the cardClass array with null values
		// for (int i =0; i<deck.length; i++)
		// {
		// 	deck[i] =new CardClass();
		// }


	}

	/** Non-Default Constructor 
	**	Use loop to make deep copy
	*/
	public DeckClass(CardClass [] deck) {
		for (int i=0; i<deck.length; i++)
		{
			this.deck[i] = deck[i];
		}
	}
	/** Accessor for deck */
	public CardClass [] getDeck() {
		return deck;
	}

	/** Mutator for deck */
	public void setDeck(CardClass [] deck) {
		this.deck = deck;
	}

	/** Shuffles the CardClass objects on the Deck at random
	*	IDEAS: Use Fisher Yates Algorithm
	*	@return shuffled CardClass array
	*/
	public void shuffle() {

		// Simple way of shuffling though I'm not sure if we can use
		// the Java Collections Framework??
		// if not then we can change but this will work for testing purposes!

		Collections.shuffle(Arrays.asList(deck));
	
  }
		
	

	/** Deal the Cards in the deck to the players 
	*   of the game
	*	@param number of players
	*/
	public void dealCards(int numPlayers, UserClass[] arrayUser) {

		int count = 0;

		Random rand = new Random();
		int randomPlayer = rand.nextInt(3);
		if (numPlayers == 5){

			int i = 0;
			while ( i < deck.length){

				arrayUser[0].addCard(deck[i++]);
				arrayUser[1].addCard(deck[i++]);
 				arrayUser[2].addCard(deck[i++]);
				arrayUser[3].addCard(deck[i++]);
 				arrayUser[4].addCard(deck[i++]);
			}
		}
		else if (numPlayers ==4){
			int i = 0;
			while ( i < deck.length){
				arrayUser[0].addCard(deck[i++]);
				arrayUser[1].addCard(deck[i++]);
 				arrayUser[2].addCard(deck[i++]);
				arrayUser[3].addCard(deck[i++]);
			}
		}
		else if (numPlayers == 3){
			int i = 0;
			while ( i < deck.length){
				// Selects random player to give the extra card??
				if (i == deck.length -1){
					arrayUser[randomPlayer].addCard(deck[i]);
					break;
				}
				arrayUser[0].addCard(deck[i++]);
				arrayUser[1].addCard(deck[i++]);
 				arrayUser[2].addCard(deck[i++]);	
			}
	 	}	
	 	else if (numPlayers ==2){
	 		
	 		int i = 0;
			while ( i < deck.length){

				arrayUser[0].addCard(deck[i++]);
				arrayUser[1].addCard(deck[i++]);
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

	/** Clears the communal pile */
	public CardClass [] clearDeck() {
		return null;
	}

	/** Adds a Card to the communal pile 
	*
	*	@param card to be added
	*/
	public void addCard(CardClass crd) {
		deck[count] = crd;
		System.out.println(" CARD ADDEDTO COMMUNAL DECK: " + crd);

		count++;
	}

	/** Clear the deck
	*
	*
	*/
	public void clear() {


		deck = new CardClass[TOTAL_CARDS];
	 	count = 0;
	 }
	


	/** Deletes a Card from the communal pile.
	*   
	*   This is only used when we have an remaining card when dealing
	*
	*	@param index of position of the card to be deleted 
	*          in the deck
	*/
	public void deleteCard(int position) {

	}

	/** Reset deck once the game is completed
	*
	*/
	public void reset() {
		
	}

	public String [] getCategories(){

		String [] tokens = categories.split(" ");
		return tokens;


	}

	public void readText() {

		 deck = new CardClass[TOTAL_CARDS];
	
		String line = "";
		String [] arrayLine = new String[41];
		String s ="";

		int t = 0;
		try 
		{
			FileReader fr = new FileReader("deck2.txt");
			Scanner in = new Scanner(fr);
			
			//Reads each line of the text file into 
			// a string array
			int numCards=0;
			while (in.hasNextLine())
			{	
				s = in.nextLine();
				arrayLine[numCards] = s;
				numCards++;

				
				System.out.println("" + t);
				t++;

			}
			
			// for (int i =0 ;i < 41; i++){


			// 	System.out.println(arrayLine[i]);
			// }

			

			// first line containing all the categorie names
			categories = arrayLine[0];
			System.out.println(categories);

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