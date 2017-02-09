import java.util.*;
/** 
*   UserClass defines a player in the game.
*   Hand refers to their cards, which is stored as
*   a Card object array.
*/
public class UserClass {

	/** Class Constants*/
	private static final int TOTAL_CARDS = 40;

	/** Instance Variables */
	private CardClass [] hand, playerDeck;
	private int count;

	/** Constructor */
	public UserClass() {
		count = 0;
		hand = new CardClass [TOTAL_CARDS+1];
	}


	/** 
	*  Adds a Card to the user's hand. This card always 
	*  goes to the bottom of the user's hand.
	*  @param card to be added
	*/
	public void addCard(CardClass crd) {
		hand[count] = crd;
		count++;
	}


	/** 
	*  Deletes a Card from the users hand. It is always the
	*  top Card of the user's hand that gets deleted.
	*/
	public void deleteCard() {
		CardClass cd = hand[0];
		hand[0]= new CardClass();
		for (int i=0; i<hand.length-1; i++) {
			hand[i] = hand[i+1];
		}
		count--;
	}

	/**
	 * Gets the number of cards in the user's deck
	 * @return [count] - the number of cards
	 */
	public int numberOfCards(){
		return count;
	}

	
	/**
	 * Method to return the topcard of the player
	 * hand[0] will always be the top card
	 * @return hand[0] 
	 */
	public CardClass topCard(){
		return hand[0];
	}

	/**
	* Displays content of a particular card
	* in the user's hand as a string
	* @param cardNumber - Cards place in the deck 
	* @return [a] String of the card's details
	*/
	public String printCard(int cardNumber){
		String a = hand[cardNumber].toString();
		return a;
	}

	
}