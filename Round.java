/** Class which contains all method which handle a single
	round in a game */
public class Round{

	/** Instance Variables */
	private int index = 0;
	private int noPlayers, noDraws;
	private DeckClass deck;
	private UserClass [] usersInGame = new UserClass [noPlayers];
	private int [] round ;
	private boolean winner;
	private int winningPlayerIndex;
	private String [] categoryNames;
	private int [] playerWinners;
	private boolean playerOneStillIn = false;

	/** Constructor */
	public Round(int index, int noPlayers, UserClass []  usersInGame, DeckClass communalDeck, String [] cats){

		this.index = index;
		this.noPlayers = noPlayers;
		this.usersInGame = usersInGame;
		this.deck = communalDeck;
		this.categoryNames = cats;
		
		this.setUpRoundArray();
		this.findWinnerDraw();	

	}

	/**
	 * Sets up the round array.
	 * The round array is an integer array of all the players
	 * card values for a selected category.
	 */
	public void setUpRoundArray() {

		playerWinners = new int [noPlayers];
		for (int i=0; i< playerWinners.length; i++){
				playerWinners[i] = 0;
		}

		round = new int [noPlayers];
	
		for (int i=0; i<noPlayers; i++)
		{
			if( usersInGame[i].numberOfCards() !=0){
				if (index ==1){
					round[i] = usersInGame[i].topCard().getCatOne();
				}
				else if (index == 2){
					round[i] = usersInGame[i].topCard().getCatTwo();
				}
				else if (index ==3){
					round[i] = usersInGame[i].topCard().getCatThree();
				}
				else if (index ==4){
					round[i] = usersInGame[i].topCard().getCatFour();
				}
				else if (index ==5){
					round[i] = usersInGame[i].topCard().getCatFive();
				}
								
			}

			//Card value for a selected category is set to
			//-1 if a player is eliminated
			else if (usersInGame[i].numberOfCards()  == 0){
				round[i] = -1; 
			}
		}
		
		//TESTS 5
		System.out.println("\nTEST 5: PRINT CURRENT TOP CARD"); 
		if (usersInGame[0].numberOfCards()!=0){
			System.out.println("User Player cards are " +usersInGame[0].printCard(0));
		}

		for (int i=1; i<usersInGame.length; i++) {
			if (usersInGame[i].numberOfCards()!=0) {
			System.out.println("Computer Player " + (i+1) +" cards are " +usersInGame[i].printCard(0));
			}
		}System.out.println(""); //used for test formating

		//Tests 6
		System.out.println("TEST 6: Category in play is " + categoryNames[index]);
		for (int i=0; i<round.length; i++) {
			System.out.println("Value of category played by player " +(i+1) +" is " +round[i]);
		}
	}

	/**
	 * Method to find a winner or draw result.
	 * Uses maxinum value algorithm to find the largest
	 * value in the round array. 
	 * Checks that there is no duplicate of this value.
	 * If duplicate: draw
	 * If no duplicate: win 
	 */
	public void findWinnerDraw() {

		winner= true;
		
		//Maxinum value algorithm
		int largestValue =round[0];
		int increment=1;
		int possibleWinner =0;
		while  (increment<round.length)	{
			if (round[increment]>largestValue) {
				largestValue = round[increment];
				possibleWinner= increment;
			}
			increment++;
		}

		//System.out.println("WE FOUND OUR WINNING VALUE " +largestValue + " HELD BY PLAYER "+possibleWinner);
		
		//Tests for duplicate of largest value in round array
		boolean test = false;
		for (int i = 0; i < noPlayers; i++){
			if (largestValue==round[i]){
				test = false;
				if (possibleWinner == i){
					test = true;
				}
				if (!test){
					winner = false;
				//	System.out.println("We have a tie");
					noDraws++;
				}
			}
		}
		//Method which hands out cards 
		//depending on the result of the round
		handOutCards(possibleWinner);
	}
		

		
	
	
	/**
	 * Method which distributes cards depending on the 
	 * result of the round
	 * @param possibleWinner number of the winning player if there was a win
	 */
	public void handOutCards(int possibleWinner) {
		/*	There is a winner:
		* 	-add all cards for the other players deck to the winning players deck
		* 	-remove cards from losing players deck
		*	-add all communal cards to the winning players deck
		*	-remove all cards from communal deck.	
		*/
		if (winner==true) {

			winningPlayerIndex = possibleWinner;
			playerWinners[winningPlayerIndex] = 1;

			for (int i=0; i<noPlayers; i++)
			{
				 
				if (usersInGame[i].numberOfCards() != 0){
					usersInGame[winningPlayerIndex].addCard(usersInGame[i].topCard());
					usersInGame[i].deleteCard();
				}
			}
 
			int n = deck.getDeckCount();
			CardClass [] pile = deck.getPile(); 
			if ( n>0){
				for (int j =0; j <n; j++){
					usersInGame[winningPlayerIndex].addCard(pile[j]);
					}
				}

			int communalDeckTotal = deck.getDeckCount();
			deck.clear();

			//System.out.println("Adding was successful" + usersInGame[winningPlayerIndex].numberOfCards());
			//System.out.println("Deleting was successful" + usersInGame[1].numberOfCards());
			//System.out.println("      INDEX of wining player: " +winningPlayerIndex);

	
		}

		/** There is a draw:
		*	-add all players cards to communal deck
		*   -delete players cards
		*/
		if (winner==false) {

			winningPlayerIndex = -1;

			//TEST PRINT 4: PRINT CONTENTS OF COMMUNAL PILE
			System.out.println("\nTEST 4: PRINT COMMUNAL PILE:");

			for (int i=0; i<usersInGame.length; i++) {
				if (usersInGame[i].numberOfCards() != 0){
					deck.addCard(usersInGame[i].topCard());
					System.out.println(usersInGame[i].topCard());
					usersInGame[i].deleteCard();
				}			
			}
			System.out.println("");
	
		}
		
		if (usersInGame[0].numberOfCards() == 0) {
			playerOneStillIn = true;
		}
	}
	
	/**
	 * Accessor for winningPlayerIndex
	 * @return the index of the winning player
	 */
 	public int getWinner() {
 		return winningPlayerIndex;
 	}

 	/**
	 * Accessor for noDraws
	 * @return the number of draws
	 */
 	public int getNoDraws() {
 		return noDraws;
 	}

 	/**
	 * Accessor for playerWinners
	 * @return number of time each player has won a round
	 */
 	public int [] getPlayerWinners() {
 		return playerWinners;
 	}

 	/**
	 * Accessor for playerOneStillIn
	 * @return flag to show if player one is still in the game
	 */
 	public boolean getPlayerOneStillIn() {
 		return playerOneStillIn;
 	}

}
