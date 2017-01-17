/** UserClass defines a player object, which is either a computer player
*   or a human player. They share common attributes and conceptual methods,
*   however they differ in how they execute certain methods.
*/
import java.util.*;

public class UserClass {

	/** Class Constants */

	/** Instance Variables */
	private CardClass [] hand;

	/** Default Constructor */
	public UserClass() {

	}

	/** Non-Default Constructor */

	/** Accessor for hand */
	public CardClass [] getHand() {
		return hand;
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
	}
	/** Deletes a Card from the users hand
	*	
	*	hand = Arrays.copyOf(hand,(hand.length-1));
	*	hand[hand.length-1] = crd;
	*
	*	@param card to be deleted
	*
	*/
	public void deleteCard(CardClass crd) {
	
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
}