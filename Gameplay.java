import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;

/**
 * GUI to display the gameplay interface
 * 
 */
public class Gameplay extends JFrame implements ActionListener{

	/** Instance variables */
	private JPanel pan;
	private JButton playButton;
	private JRadioButton cat1, cat2, cat3, cat4, cat5;
	private int noPlayers, winningPlayerIndex = 0, highestCategory;
	private final int MAX_PLAYERS = 5;
	private JLabel p2, p3, p4, p5, score2, score3, score4, score5, description, inPlay;
	private JLabel val1,val2, val3, val4, val5, userCardsLabel, cardCount2, result, yourScore;
	private UserClass []usersInGame;
	private CardClass playerOne, playerTwo;
	private DeckClass deck;
	private String [] cats;

	//test
	private JLabel welcome, communal;

	

	// we could set constants to use for playerArray index ie. int player 1 =0
	// this would make understanding the user easier to follow?

	/**
	 * constructor for GameplayGUI
	 * [parameters can be added as needed]
	 */
	
	public Gameplay(){

		// helper method to get number of players
		this.noPlayer();

		this.setSize(700, 400);
		this.setLocation(200, 200);
		this.setTitle("Top Trumps!");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

		this.createDeck();
		
		//this.selectCategory(1);
		//this.round(selectCategory(1));

		// Layout all the components
		this.layoutComponents();
		this.centerLayout();
		this.bottomPanel();
		


		this.getFirstPlayer();
		this.userTopCard();
		this.updateGUI(true);
		


		//get top card info


	
	}

	/**
	 * layout all the components
	 * Will move into seperate helper methods*****!
	 *  
	 */
	public void layoutComponents(){

		// This is all pretty much just placeholders for now
		
		GridLayout gridTop = new GridLayout(1,2);
		welcome = new JLabel("Top Trumps!!");
		welcome.setFont(new Font("Courier", Font.PLAIN, 24));
		inPlay = new JLabel("");
		//inPlay.setFont(new Font("Courier", Font.PLAIN, 24));

		// Layout for top of panel
		pan = new JPanel(gridTop);
		this.add(pan, "North");
		pan.add(welcome);
		pan.setBackground(Color.cyan);

		pan.add(inPlay);

		GridLayout grid = new GridLayout(6, 2);
		JPanel pan3 = new JPanel(grid);
		this.add(pan3, "West");

		GridLayout grid2 = new GridLayout(5, 2);
		
		JPanel eastPan = new JPanel(grid2);
		this.add (eastPan, "East");
		
		
		// Center Panel layout
		JTextArea tArea = new JTextArea(10, 25);
		//tArea.setEditable(false);
		tArea.setBackground(Color.cyan);

		/* Contains the layout for the east and west panels with all the 
		information for each player*/
		p2 = new JLabel(" Player 2 ");
		pan3.add(p2);
		p2.setFont(new Font("Courier", Font.BOLD, 16));
		score2 = new JLabel("  Score: ");
		score3 = new JLabel("  Score: ");
		score4 = new JLabel("  Score: ");
		score5 = new JLabel("  Score: ");
		pan3.add(score2);

		

		cardCount2 = new JLabel("   Cards: " + usersInGame[1].numberOfCards());
		pan3.add(cardCount2);

		p3 = new JLabel(" Player 3 ");
		p3.setFont(new Font("Courier", Font.BOLD, 16));
		eastPan.add(p3);
		eastPan.add(score3);

		p4 = new JLabel(" Player 4 ");
		p4.setFont(new Font("Courier", Font.BOLD, 16));
		pan3.add(p4);
		pan3.add(score4);
		JLabel cardCount4 = new JLabel("   Cards: ");
		pan3.add(cardCount4);

		p5 = new JLabel(" Player 5 ");
		p5.setFont(new Font("Courier", Font.BOLD, 16));
		eastPan.add(p5);
		eastPan.add(score5);


		if (noPlayers < MAX_PLAYERS){
			p5.setEnabled(false);
			score5.setEnabled(false);
		}
		if (noPlayers < MAX_PLAYERS-1){
			p4.setEnabled(false);
			score4.setEnabled(false);
			cardCount4.setEnabled(false);
		}
		if (noPlayers < MAX_PLAYERS - 2){
			p3.setEnabled(false);
			score3.setEnabled(false);
		}
	}
	
	/**
	 * Lay out all the components of the centre panel
	 */
	public void centerLayout(){


		JLabel comPile = new JLabel("Communal pile: ");
		GridLayout cGrid = new GridLayout(7, 2); // 1 col originally
		JPanel centerPan = new JPanel(cGrid);
		this.add(centerPan, "Center");
		centerPan.setBackground(Color.cyan);

		cats = deck.getCategories();

		
	
		cat1 = new JRadioButton(cats[1]+":");
		cat1.addActionListener(this);
		cat2 = new JRadioButton(cats[2]+":");
		cat2.addActionListener(this);
		cat3 = new JRadioButton(cats[3]+":");
		cat3.addActionListener(this);
		cat4 = new JRadioButton(cats[4]+":");
		cat4.addActionListener(this);
		cat5 = new JRadioButton(cats[5]+":");
		cat5.addActionListener(this);

		ButtonGroup group = new ButtonGroup();
		group.add(cat1);
		group.add(cat2);
		group.add(cat3);
		group.add(cat4);
		group.add(cat5);

		cat1.setSelected(true);



		centerPan.setBorder(new TitledBorder(new EtchedBorder(), "Top Card"));
		description = new JLabel("");
		description.setFont(new Font("Courier", Font.BOLD, 18));

		//test Labels 
		JLabel blank = new JLabel("      ");
		val1 = new JLabel("");
		val1.setFont(new Font("Courier", Font.BOLD, 16));
		//val1.setHorizontalAlignment(JLabel.CENTER);
		blank.setHorizontalAlignment(JLabel.CENTER);

		val2 = new JLabel("");
		val2.setFont(new Font("Courier", Font.BOLD, 16));
		val3 = new JLabel("");
		val3.setFont(new Font("Courier", Font.BOLD, 16));
		val4 = new JLabel("");
		val4.setFont(new Font("Courier", Font.BOLD, 16));
		val5 = new JLabel("");
		val5.setFont(new Font("Courier", Font.BOLD, 16));

		centerPan.add(description);
		centerPan.add(blank);
		centerPan.add(cat1);
		centerPan.add(val1);
		centerPan.add(cat2);
		centerPan.add(val2);
		centerPan.add(cat3);
		centerPan.add(val3);
		centerPan.add(cat4);
		centerPan.add(val4);
		centerPan.add(cat5);
		centerPan.add(val5);
	
		userCardsLabel = new JLabel("  Cards:    " );
		userCardsLabel.setFont(new Font("Courier", Font.PLAIN, 14));
		centerPan.add(userCardsLabel);


		//RadioButtons disabled for test

		// boolean test = true;
		// if (test){
		// 	cat1.setEnabled(false);
		// 	cat2.setEnabled(false);
		// 	cat3.setEnabled(false);
		// 	cat4.setEnabled(false);
		// 	cat5.setEnabled(false);
		//  }
	}
	/**
	 * Bottom panel alternative layout
	 */
	public void bottomPanel(){


		//Bottom panel used for the player/user
		GridLayout gridBottom = new GridLayout(2,3);
		playButton = new JButton("Play!");
		playButton.addActionListener(this);
		//playButton.setPreferredSize(new Dimension(10, 10));
		JPanel bottomPan = new JPanel(gridBottom);
		yourScore = new JLabel("Your Score: ");
		yourScore.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		bottomPan.add(yourScore);
		communal = new JLabel("Communal pile:");
		this.add(bottomPan, "South");
		bottomPan.add(communal);
		//bottomPan.add(playButton);
		bottomPan.setBackground(Color.cyan);

	

		// End of round result test
		result = new JLabel("Result");
		result.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		bottomPan.add(result);

		JPanel trial = new JPanel();
		trial.setBackground(Color.cyan);
		trial.add(playButton);
		trial.setLayout(new FlowLayout(FlowLayout.LEFT));
		bottomPan.add(trial);




	}

	/**
	 * Helper method to get the number of players for a game
	 * Launches JOptionPane with JCombo
	 * @return noPlayers - the number of players
	 */
	private void noPlayer(){

		String[] players = { "2", "3", "4", "5", };
		JComboBox<String> jBox  = new JComboBox <String>(players);
		
		JOptionPane.showMessageDialog(null, jBox, 
			"Players", JOptionPane.PLAIN_MESSAGE);
		String s = ( String) jBox.getSelectedItem();
		noPlayers = Integer.parseInt(s);
		System.out.println("Number of players: "+ noPlayers);

	}

	private void updateGUI(boolean start){

		communal.setText("Communal cards:  " + deck.getDeckCount());

		if (winningPlayerIndex == 0){
			welcome.setText("Your Turn");
		}
		else{
			welcome.setText("Player "+ (winningPlayerIndex+1) + "'s turn");
		}
		cardCount2.setText("   Cards: " + usersInGame[1].numberOfCards());


		if (!start){
			inPlay.setText("Category chosen: "+ cats[highestCategory]);
			// testing
			yourScore.setText("Your Score: " + playerOne.catAtIndex(highestCategory));
			
			score2.setText("  Score: " + playerTwo.catAtIndex(highestCategory));

			if (winningPlayerIndex ==0){
				result.setText("You Win!");
			}
			else {
			result.setText("Player "+(winningPlayerIndex +1)+ " WINS!");
			}
		}
		



	}

	/**
	 * 
	 * @param e [description]
	 */
	public void actionPerformed(ActionEvent e){

		if (e.getSource()== playButton){
			//next go
			this.selectCategory(winningPlayerIndex);
			round(highestCategory);
		
			this.updateGUI(false);
			this.userTopCard();
			System.out.println("next go");
			
		}
		// else if (e.getSource() ==cat1)
		// {
		// 	round(1);
		// 	this.userTopCard();
		// }
		// else if (e.getSource() ==cat2)
		// {
		// 	round(2);
		// 	this.userTopCard();
		// }
		// else if (e.getSource() ==cat3)
		// {
		// 	round(3);
		// 	this.userTopCard();
		// }
		// else if (e.getSource() ==cat4)
		// {
		// 	round(4);
		// 	this.userTopCard();
		// }
		// else if (e.getSource() ==cat5)
		// {
		// 	round(5);
		// 	this.userTopCard();
		// }
	}

	/**
	 * Helper method for instatiating the deck
	 */
	public void createDeck() {

		//DeckClass deck = new DeckClass();
		deck = new DeckClass();
		makeUsers();

		
		deck.dealCards(noPlayers, usersInGame);

		// usersInGame[0].getHand();
		// 
		int playerNumber = 1;
		for (int i = 0; i < noPlayers; i++){
			int n = usersInGame[i].numberOfCards();
			System.out.println("Player "+ playerNumber +" CARDS:" + n);
			playerNumber++;

		//SUGGESTION: randomly select a player to decide on the initial category 
		//this method should return this category index value so it can be used as param in round method
		}
	}
	/**
	 * Users method
	 */
	public void makeUsers(){

		usersInGame = new UserClass[noPlayers];
		UserClass winner;

		for (int i = 0; i< noPlayers; i++){

			UserClass player = new UserClass();
			usersInGame[i] = player;		
		}
	}


	 /**
	 * Method to add the User's top card to the GUI on launch
	 * 
	 */

	public void userTopCard(){

		if (usersInGame[0].numberOfCards()!= 0){

			if (winningPlayerIndex == 0){
				cat1.setEnabled(true);
				cat2.setEnabled(true);
				cat3.setEnabled(true);
				cat4.setEnabled(true);
				cat5.setEnabled(true);

			}
			else{
				cat1.setEnabled(false);
				cat2.setEnabled(false);
				cat3.setEnabled(false);
				cat4.setEnabled(false);
				cat5.setEnabled(false);
			}

			playerOne = usersInGame[0].topCard();

			


			description.setText(playerOne.getDescription());
			val1.setText("" +playerOne.getCatOne());
			val2.setText(""+playerOne.getCatTwo());
			val3.setText(""+playerOne.getCatThree());
			val4.setText(""+playerOne.getCatFour());
			val5.setText(""+playerOne.getCatFive());
			userCardsLabel.setText("  Cards:    " + usersInGame[0].numberOfCards());

		}



		
}
	


/** Winner chooses a category to play.
	If the user is a human player, they will manually
	via the GUI select the category.
	If the user is a computer player, they will select the highest value category 
	of their topcard.
	@param player whose turn it is to select a category
	@return category that has been choosen 
*/

	private void selectCategory(int player) {
		highestCategory=0;
		int [] topCardValues = new int[5];



		if (player==0) {

			

			if (cat1.isSelected()){
				highestCategory = 1;
			}
			else if (cat2.isSelected()){
				highestCategory = 2;
			}
			else if (cat3.isSelected()){
				highestCategory = 3;
			}
			else if(cat4.isSelected()){
				highestCategory =4;
			}
			else if (cat5.isSelected()){
				highestCategory = 5;
			}
		 

			

		}
			
			
		
		else 
		{
			topCardValues[0]=usersInGame[player].topCard().getCatOne();
			
			topCardValues[1]=usersInGame[player].topCard().getCatTwo();
			
			topCardValues[2]=usersInGame[player].topCard().getCatThree();
			
			topCardValues[3]=usersInGame[player].topCard().getCatFour();
			
			topCardValues[4]=usersInGame[player].topCard().getCatFive();
			
			int largest =topCardValues[0];
			int i=1;
			int largestIndex =0;
			while ( i<topCardValues.length) {
				if (topCardValues[i]>largest) {
					largest = topCardValues[i];
					largestIndex=i;
				}
				i++;
			 }


			highestCategory=largestIndex+1;
		
			System.out.println(usersInGame[player].topCard());
			System.out.println(largest + " " + (highestCategory));
			
		}

		// testing
		playerTwo = usersInGame[1].topCard();
		
	
		
	}


/**Method for a round
 @param  the index corresponds to the category choosen by the user*/
	public void round(int index) {

	
		//INSTANCE VARIABLE?? USED REPEATEDLY
		int [] round = new int [noPlayers];

		boolean winner= true;

		winningPlayerIndex =0;



		
		// for (int i = 0; i < noPlayers; i++){
		// 	if (usersInGame[i])
		// }





		//INDEX =0 IS NOT CONSIDERED, AS IT IS NEVER CHOOSEN AS A CATEGORY (DESCRIPTION)
		
		//Get the value of all players top card Height category	
		if (index==1) {
			for (int i=0; i<noPlayers; i++)
			{
				if( usersInGame[i].numberOfCards() !=0){
					System.out.println("CRASHED HERE!!!!!!!!");
					round[i] = usersInGame[i].topCard().getCatOne();
				}
				else if (usersInGame[i].numberOfCards()  == 0){
					round[i] = -1; // set to kinus one if player eliminated
				}


			}

				
		}

		//Get the value of all players top card Weight category	
		if (index==2) {
			for (int i=0; i<noPlayers; i++)
			{
				if( usersInGame[i].numberOfCards() !=0){
					System.out.println("CRASHED HERE!!!!!!!!");
					round[i] = usersInGame[i].topCard().getCatTwo();
				}

				else if (usersInGame[i].numberOfCards() == 0){
					round[i] = -1; // set to kinus one if player eliminated
				}
			}
		}	

		//Get the value of all players top card Length category
		if (index==3) {
			for (int i=0; i<noPlayers; i++)
			{
				if( usersInGame[i].numberOfCards() != 0){
					System.out.println("CRASHED HERE!!!!!!!!");
					round[i] = usersInGame[i].topCard().getCatThree();
				}
				else if (usersInGame[i].numberOfCards() == 0){
					round[i] = -1; // set to kinus one if player eliminated
				}
			}

		}	

		//Get the value of all players top card ferocity category
		if (index==4) {
			for (int i=0; i<noPlayers; i++)
			{
				if( usersInGame[i].numberOfCards() !=0){
					System.out.println("CRASHED HERE!!!!!!!!");
					round[i] = usersInGame[i].topCard().getCatFour();
				}
				else if (usersInGame[i].numberOfCards() == 0){
					round[i] = -1; // set to kinus one if player eliminated
				}

				}
		}


		//Get the value of all players top card intelligence category
		if (index==5) {
			for (int i=0; i<noPlayers; i++)
			{
				if( usersInGame[i].numberOfCards() != 0){
					System.out.println("CRASHED HERE!!!!!!!!");
					round[i] = usersInGame[i].topCard().getCatFive();

				}
				else if (usersInGame[i].numberOfCards() == 0){
					round[i] = -1; // set to kinus one if player eliminated
				}
			}
		}	

		//JUST FOR TESTING
		for (int i=0; i<round.length; i++) {
			System.out.println("Value of category played by player " +i +" is " +round[i]);
		}

		//Maxinum value in round array algorithm
		//COULD BE MOVED INTO ITS OWN METHOD
		int largestValue =round[0];
		int increment=1;
		int possibleWinner =0;
			 //index value for the round array
		while  (increment<round.length)	{
			if (round[increment]>largestValue) {
				largestValue = round[increment];
				possibleWinner= increment;


			}
			increment++;

		}

		System.out.println("WE FOUND OUR WINNING VALUE " +largestValue + " HELD BY PLAYER "+possibleWinner);
	
		//Tests for whether there are duplicate largestValue elements in the round array
		//  	if there are duplicate largestValue && do not compare it to the value it is produced from
		//			draw; no winner
		//		if there are no duplicate largestValue elements in round array
		//			winner
		// for (int j=0; j<round.length; j++)
		// {
		// 	if ((round[j]==largestValue) )
		// 	{
		// 		if (largestValue == round[possibleWinner]){
		// 			winner=false;

		// 			System.out.println("We have a tie");
		// 		}
				
		// 	}
		// 	else if (round[j] ==largestValue) {
		// 		winner = true;
		// 		winningPlayerIndex = possibleWinner;

		// 		System.out.println("aLL GOOD");
		// 	}
		// }
		boolean test = false;
		for (int i = 0; i < noPlayers; i++){


			if (largestValue==round[i]){
				test = false;



				if (possibleWinner == i){
					
					test = true;
				}
				if (!test){
					winner = false;
					System.out.println("We have a tie");
				}
				


			}


		}


		
		/*There is a winner:
		* 	add all cards for the other players deck to the winning players deck
		* 	remove cards from losing players deck
		*	add all communal cards to the winning players deck
		*	remove all cards from communal deck
		*/
		if (winner==true) {
			winningPlayerIndex = possibleWinner;
			

			for (int i=0; i<noPlayers; i++)
			{
				//Adding cards from the losing players hands to the winning players hands
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


			
			System.out.println("Adding was successful" + usersInGame[winningPlayerIndex].numberOfCards());
			System.out.println("Deleting was successful" + usersInGame[1].numberOfCards());
			System.out.println("      INDEX of wining player: " +winningPlayerIndex);
			System.out.println("     Category in play: "+ index);

			
				
		}

		/** There is a draw:
		*	add all players cards to communal deck
		*   delete players cards
		*
		*/
		if (winner==false) {
			for (int i=0; i<usersInGame.length; i++)
			{
				if (usersInGame[i].numberOfCards() != 0){

					deck.addCard(usersInGame[i].topCard());
					usersInGame[i].deleteCard();
				}
					
			}

			
		}
		
	

		/**Checks to move onto next round
				if no  losers/winners
					winner selects next category
					call round() again
				 if winner
				 	quit game play
				 if loser 
				 	remove from game
				 	call round() again */
	for (int i=0; i<usersInGame.length; i++) {

	
		//No winners/losers
		if (usersInGame[i].numberOfCards() != 0 && usersInGame[i].numberOfCards() !=40)
		{
			//round(3);
		}
		//A winner
		else if (usersInGame[i].numberOfCards() ==40)
		{
			System.err.println("Stop Game");
			//quit()
		}
		else if (usersInGame[i].numberOfCards() ==0)
		{
			//remove player method
			//round
		}
	}
	

	}


	/**
	 * Helper method to selct player to go first
	 */
	public int getFirstPlayer(){




		Random rand = new Random();
		winningPlayerIndex = rand.nextInt(noPlayers);


		//TEST will change later
		


		//welcome.setText("player "+ winningPlayerIndex + " to go");

		return winningPlayerIndex;


	}





	

}
