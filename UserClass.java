/** UserClass defines a player object, which is either a computer player
*   or a human player. They share common attributes and conceptual methods,
*   however they differ in how they execute certain methods.
*/
import java.util.*;

public class UserClass {

	/** Class Constants */

	/** Instance Variables */
	private CardClass [] hand, playerDeck;
	private int count = 0;

	/** Default Constructor */
	public UserClass() {

		count = 0;
		System.out.println("NEW USER CREATED");
		hand = new CardClass [40];

	}

	/** Non-Default Constructor */

	/** Accessor for hand */


	public void getHand() {  // not void

		
		int n = hand[0].getHeight();
		System.out.println("HERE: " + n);
		
	}

	/** Mutator for hand */
	public void setHand(CardClass [] hand) {
		this.hand = hand;
	} 

	/** Adds a Card to a users hand
	*
	*  IDEA : 		deck = Arrays.copyOf(deck,(deck.length+1));
	*	deck[deck.length-1] = crd; 
	*
	*	@param card to be added
	*/
	public void addCard(CardClass crd) {



		
		hand[count] = crd;
		System.out.println(" CARD ADDED: " + crd);

		count++;



	}


	/** Deletes a Card from the users hand:
	*   It will always be the 0th index card of the hand array to be deleted
	*	
	*	hand = Arrays.copyOf(hand,(hand.length-1));
	*	hand[hand.length-1] = crd;
	*
	*/
	public void deleteCard() {
		

	
	}

	/** Counts the number of cards a user has in 
	*	their hand
	*	@return total number of cards in the hand
	*
	*/
	public int totalCards() {
		return 0;

	}

	/** Player selects the appropriate category 
	*   to play
	*/
	public String selectCategory() {
		return "";

	}
	/**
	 * gets the number of cards in the user's deck
	 * @return [count] - the number of cards
	 */
	public int numberOfCards(){


		// int test = hand.length;
		// return test;

		return count;
	}

	
	/**
	 * method to return the topcard of the player
	 * hand[0] will always be the top card
	 * @return hand[0] 
	 */
	public CardClass topCard(){

		return hand[0];
	}

	
}