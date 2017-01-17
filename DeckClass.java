 /** Maintains a list of CardClass objects
 * The methods allow objects to be added and deleted from the list
 *
 */
import java.util.*;

public class DeckClass {

    /** Class Constants */
    private static final int TOTAL_CARDS = 40;

	/** Instance Variables */
	private CardClass [] deck = new CardClass[TOTAL_CARDS];

	private int numCards;

	/** Default Constructor */
	public DeckClass() {

		//Fill the cardClass array with null values
		for (int i =0; i<deck.length; i++)
		{
			deck[i] =new CardClass();
		}
	}

	/** Non-Default Constructor */

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
	public void dealCards(int numPlayers) {

	}

	/** Return total number of cards on the communal pile 
	*   @return total number of cards on the communal pile
	*/
	public int getDeckCount() {
		return 0;
	}

	/** Clears the communal pile */
	public void clearDeck() {

	}

	/** Adds a Card to the communal pile 
	*
	*	@param card to be added
	*/
	public void addCard(CardClass crd) {
		deck = Arrays.copyOf(deck,(deck.length+1));
		deck[deck.length] = crd;
	}

	/** Deletes a Card from the communal pile
	*	@param card to be deleted
	*
	*	NOTE: not really necessary as the communal pile is 
	*	either added to or cleared
	*/
	public void deleteCard(CardClass crd) {

	}

	/** Reset deck once the game is completed
	*
	*/
	public void reset() {
		
	}


}