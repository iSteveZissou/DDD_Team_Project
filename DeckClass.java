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
	private CardClass [] deck = new CardClass[TOTAL_CARDS];

	private int numCards;

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
	public CardClass [] shuffle() {
		return null;
	}

	/** Deal the Cards in the deck to the players 
	*   of the game
	*	@param number of players
	*/
	public void dealCards(int numPlayers, UserClass[] arrayUser) {

		if (numPlayers == 2){
			for (int i = 0; i < deck.length; i++){
				arrayUser[0].addCard(deck[i]);
	
			}
		}



	}

	/** Return total number of cards on the communal pile 
	*   @return total number of cards on the communal pile
	*/
	public int getDeckCount() {
		return 0;
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
		deck = Arrays.copyOf(deck,(deck.length+1));
		deck[deck.length] = crd;
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

	private void readText() {

	
		String line = "";
		String [] arrayLine = new String[41];
		String s ="";
		try 
		{
			FileReader fr = new FileReader("deck.txt");
			Scanner in = new Scanner(fr);
			
			//Reads each line of the text file into 
			// a string array
			int numCards=0;
			while (in.hasNextLine())
			{
				
				s = in.nextLine();
				// s = s + in.nextLine() + "\n";
				arrayLine[numCards] = s;
				numCards++;

			}
			System.out.println("" + numCards);
			for (int i =0 ;i < 41; i++){


				System.out.println(arrayLine[i]);
			}

			for (int index = 1; index < 41; index++){

				deck[index-1] = new CardClass(arrayLine[index]);

			}


			System.out.println("Get Intel " +deck[0].getIntelligence());
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


}