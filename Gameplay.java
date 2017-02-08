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
	private CardClass [] playersTopCard;
	private DeckClass deck;
	private boolean [] playerInOrOut;
	private String [] cats;
	private JLabel welcome, communal, cardCount3, cardCount4, cardCount5;

	private final int PLAYER_ONE = 0, PLAYER_TWO = 1, PLAYER_THREE = 2, PLAYER_FOUR = 3, PLAYER_FIVE = 4;

	// counts
	private int noDraws, overAllWinner, noRounds;
	private Round round;
	private int [] playersWinners;
	private int p1Wins, p2Wins, p3Wins, p4Wins, p5Wins;

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

		this.layoutComponents();
		this.centerLayout();
		this.bottomPanel();
		
		this.getFirstPlayer();
		this.userTopCard();
		this.updateGUI(true);
	
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
		inPlay.setFont(new Font("Courier", Font.PLAIN, 14));
		inPlay.setHorizontalAlignment(JLabel.RIGHT);
		//inPlay.setFont(new Font("Courier", Font.PLAIN, 24));

		// Layout for top of panel
		pan = new JPanel(gridTop);
		this.add(pan, "North");
		pan.add(welcome);
		pan.setBackground(Color.yellow);

		pan.add(inPlay);

		GridLayout grid = new GridLayout(6, 2);
		JPanel pan3 = new JPanel(grid);
		this.add(pan3, "West");

		GridLayout grid2 = new GridLayout(6, 2);
		
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
		cardCount3 = new JLabel("   Cards: ");
		eastPan.add(p3);
		eastPan.add(score3);
		eastPan.add(cardCount3);

		p4 = new JLabel(" Player 4 ");
		p4.setFont(new Font("Courier", Font.BOLD, 16));
		pan3.add(p4);
		pan3.add(score4);
		cardCount4 = new JLabel("   Cards: ");
		pan3.add(cardCount4);

		p5 = new JLabel(" Player 5 ");
		p5.setFont(new Font("Courier", Font.BOLD, 16));
		cardCount5 = new JLabel("   Cards: ");
		eastPan.add(p5);
		eastPan.add(score5);
		eastPan.add(cardCount5);

		for (int i =2; i< noPlayers + 1; i++){
			if (i == 3){
				cardCount3.setText("   Cards: " + usersInGame[i-1].numberOfCards());
			}
			if (i ==4){
				cardCount4.setText("   Cards: " + usersInGame[i-1].numberOfCards());
			}
			if (i ==5){
				cardCount5.setText("   Cards: " + usersInGame[i-1].numberOfCards());
			}
		}

		if (noPlayers < MAX_PLAYERS){
			p5.setEnabled(false);
			score5.setEnabled(false);
			cardCount5.setEnabled(false);
		}
		if (noPlayers < MAX_PLAYERS-1){
			p4.setEnabled(false);
			score4.setEnabled(false);
			cardCount4.setEnabled(false);
		}
		if (noPlayers < MAX_PLAYERS - 2){
			p3.setEnabled(false);
			score3.setEnabled(false);
			cardCount3.setEnabled(false);
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
		cat1.setBackground(Color. cyan);
		cat2 = new JRadioButton(cats[2]+":");
		cat2.addActionListener(this);
		cat2.setBackground(Color. cyan);
		cat3 = new JRadioButton(cats[3]+":");
		cat3.addActionListener(this);
		cat3.setBackground(Color. cyan);
		cat4 = new JRadioButton(cats[4]+":");
		cat4.addActionListener(this);
		cat4.setBackground(Color. cyan);
		cat5 = new JRadioButton(cats[5]+":");
		cat5.addActionListener(this);
		cat5.setBackground(Color. cyan);

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
		// val refers to the user's current category'
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

		communal = new JLabel("Communal pile:");
		communal.setFont(new Font("Courier", Font.PLAIN, 14));
		centerPan.add(communal);	
	}
	/**
	 * Bottom panel alternative layout
	 */
	public void bottomPanel(){

		//Bottom panel used for the player/user
		GridLayout gridBottom = new GridLayout(1,3);
		playButton = new JButton("Play!");
		playButton.addActionListener(this);
		JPanel bottomPan = new JPanel(gridBottom);
		yourScore = new JLabel("Your Score: ");
		yourScore.setFont(new Font("Courier", Font.BOLD, 16));
		bottomPan.add(yourScore);
		
		this.add(bottomPan, "South");
		bottomPan.setBackground(Color.gray);

		// End of round result test
		result = new JLabel("Result");
		result.setFont(new Font("Courier", Font.PLAIN, 20));
		bottomPan.add(result);

		JPanel trial = new JPanel();
		trial.setBackground(Color.gray);
		playButton.setHorizontalAlignment(JLabel.RIGHT);
		trial.add(playButton);
		trial.setLayout(new FlowLayout(FlowLayout.RIGHT));
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

		playerInOrOut = new boolean [noPlayers];
		for (int i =0; i < noPlayers; i++){
			playerInOrOut[i] = true;
		}

	}

	/**
	 * Helper method to selct player to go first
	 */
	public int getFirstPlayer(){

		Random rand = new Random();
		winningPlayerIndex = rand.nextInt(noPlayers);
		return winningPlayerIndex;

	}

	private void updateGUI(boolean start){

		communal.setText("Communal cards:  " + deck.getDeckCount());

		if (winningPlayerIndex == 0){
			welcome.setText("Your Turn");
		}
		else{
			welcome.setText("Player "+ (winningPlayerIndex+1) + "'s turn");
		}
		if (!start){
			inPlay.setText("Category Picked: "+ cats[highestCategory]+ " ");
			// testing
			if (usersInGame[0].numberOfCards() !=0){
				yourScore.setText("Your Score: " + playersTopCard[0].catAtIndex(highestCategory));
			}
			else{
				yourScore.setText("Your Score: ");
			}

			if (winningPlayerIndex ==0){
				result.setText("You Win!");
			}
			else {
			result.setText("Player "+(winningPlayerIndex +1)+ " WINS!");
			}

			if (playerInOrOut[1]){
				score2.setText("  Score: " + playersTopCard[1].catAtIndex(highestCategory));
				cardCount2.setText("   Cards: " + usersInGame[1].numberOfCards());
			}
			else{
				score2.setText("  Score: ");
				p2.setEnabled(false);
				score2.setEnabled(false);
				cardCount2.setEnabled(false);
			}

			if (noPlayers >=3){
				if(playerInOrOut[2]){
					score3.setText("  Score: " + playersTopCard[2].catAtIndex(highestCategory));
					cardCount3.setText("   Cards: " + usersInGame[2].numberOfCards());
				}
				else{
					score3.setText("  Score: ");
					p3.setEnabled(false);
					score3.setEnabled(false);
					cardCount3.setEnabled(false);
				}
			}
			if (noPlayers >=4){
				if(playerInOrOut[3]){
					score4.setText("  Score: " + playersTopCard[3].catAtIndex(highestCategory));
					cardCount4.setText("   Cards: " + usersInGame[3].numberOfCards());
				}
				else{
					score4.setText("  Score: " );
					p4.setEnabled(false);
					score4.setEnabled(false);
					cardCount4.setEnabled(false);
				}
			}
			if (noPlayers ==5){
				if(playerInOrOut[4]){
					score5.setText("  Score: " + playersTopCard[4].catAtIndex(highestCategory));
					cardCount5.setText("   Cards: " + usersInGame[4].numberOfCards());
				}
				else{
					score5.setText("  Score: ");
					p5.setEnabled(false);
					score5.setEnabled(false);
					cardCount5.setEnabled(false);
				}
			}
		}
		
	}

	/**
	 * 
	 * @param e [description]
	 */
	public void actionPerformed(ActionEvent e){

		if (e.getSource()== playButton){

			//Set player one to false if he is out
			for (int i = 0; i < noPlayers; i++){
				if (usersInGame[i].numberOfCards() ==0) {
					playerInOrOut[i] = false;
				}
			}
			//Check if player one is still in the game
			//if not skip to end
			boolean end = false;
			if (!playerInOrOut[0]) {
				end =true;
				playButton.setEnabled(false);
				skipToEnd();
			}
			if (!end){
			this.selectCategory(winningPlayerIndex);
			round(highestCategory);
			getStats();
			this.updateGUI(false);
			this.userTopCard();	
			}		
		}
	}

	/**
	 * Helper method for instatiating the deck
	 */
	public void createDeck() {
		deck = new DeckClass();
		makeUsers();
		deck.dealCards(usersInGame);

		int playerNumber = 1;
		System.out.println("\n\nTEST3: PRINT PLAYERS HAND");
		for (int i = 0; i < noPlayers; i++){
			int n = usersInGame[i].numberOfCards();
			System.out.println("\nPlayer "+ playerNumber +" CARDS:" + n);

			//TEST 3: Print Contents of Users hand
			for(int j=0;j<n;j++){
				String a = usersInGame[i].printCard(j);
				System.out.println(a);
			}

			playerNumber++;

		}
	}
	/**
	 * Users method - makes all the users depending on the Value
	 * selected at the beggining of the game
	 */
	public void makeUsers(){

		usersInGame = new UserClass[noPlayers];
		playersTopCard = new CardClass [noPlayers];
		UserClass winner;

		playerInOrOut = new boolean [noPlayers];
		playersWinners = new int [noPlayers];

		for (int i = 0; i< noPlayers; i++){

			UserClass player = new UserClass();
			usersInGame[i] = player;
			playerInOrOut[i] = true;	
			playersWinners[i] = 0;
			playersTopCard[i] = usersInGame[i].topCard();	
		}
	}

	 /**
	 * Method to add the User's top card to the GUI on launch
	 * Continues to be called to update the user's topcard after each '
	 * 
	 */
	public void userTopCard() {

		if (usersInGame[PLAYER_ONE].numberOfCards()!= 0){

			cat1.repaint();
			cat2.repaint();
			cat3.repaint();
			cat4.repaint();
			cat5.repaint();

			if (usersInGame[PLAYER_ONE].numberOfCards() ==40){
				
				JOptionPane.showMessageDialog(null, "You WIN!", "YOU WIN!", JOptionPane.PLAIN_MESSAGE);
				this.skipToEnd();
			}
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

			playersTopCard[PLAYER_ONE] = usersInGame[0].topCard();
			description.setText(playersTopCard[PLAYER_ONE].getDescription());
			val1.setText("" +playersTopCard[PLAYER_ONE].getCatOne());
			val2.setText(""+playersTopCard[PLAYER_ONE].getCatTwo());
			val3.setText(""+playersTopCard[PLAYER_ONE].getCatThree());
			val4.setText(""+playersTopCard[PLAYER_ONE].getCatFour());
			val5.setText(""+playersTopCard[PLAYER_ONE].getCatFive());
			userCardsLabel.setText("  Cards:    " + usersInGame[PLAYER_ONE].numberOfCards());

		}
		else {
			JOptionPane.showMessageDialog(null, "You Lost! The Game will no skip to the end!", 
			"GAME OVER!", JOptionPane.PLAIN_MESSAGE);
			skipToEnd();
			
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

			playersTopCard = new CardClass[noPlayers];
			for (int i=0; i<noPlayers; i++) {
				playersTopCard[i] = usersInGame[i].topCard();		
			}	

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
			highestCategory=playersTopCard[player].getHighestValue();
			System.err.println(""  +highestCategory);
		}
	}


/**Method for a round
 @param  the index corresponds to the category choosen by the user*/
	public void round(int index) {


		index = highestCategory;
		System.err.println ("I'm here");

		// for (int i = 0; i < noPlayers; i++){
		// 		if (usersInGame[i].numberOfCards() ==0) {
		// 			playerInOrOut[i] = false;
		// 		}
		// 	}


		round = new Round(highestCategory, noPlayers , usersInGame, deck);
		int possibleWinner = round.getWinner();
			if(possibleWinner ==-1) {
				winningPlayerIndex = winningPlayerIndex;
				
			}
			else {
				winningPlayerIndex = possibleWinner;
			}


	}	

	/** 
	 * Plays a round recursively, once player one is 
	 *   out of the game.
	 */
	public void skipToEnd(){

		//Count how many players are left in the game
		int playersLeft = 0;
		for (int i=0; i<usersInGame.length; i++){
			if (usersInGame[i].numberOfCards() !=0) {
				playersLeft++;
			}
		}
		System.out.println("Number of players left in the game " +playersLeft);
		

		//If there are still more than 1 player left
		//than keep playing the game
		if (playersLeft>1) {

			this.selectCategory(winningPlayerIndex);
			round(highestCategory);
			getStats();
			this.skipToEnd();
		}

		//If there is only one player left in the game
		//Stop the game
		else if (playersLeft==1){

			// System.out.println("The number of DRAWS!!!!!"+noDraws);
			// System.out.println("The number of ROUNDS" +noRounds);
			for (int i=0; i<playersWinners.length; i++) {
				//System.out.println("Array of winning tally " + playersWinners[i]);
			}

			findOverAllWinner();
			getReport();
			this.dispose();
		}
	}

	/** 
	*  Helper method to find 
	*  the overall winner of the game
	*
	*/
	private void findOverAllWinner() {
		int largestValue =playersWinners[PLAYER_ONE];
		int increment=1;
		while  (increment<playersWinners.length)	{
			if (playersWinners[increment]>largestValue) {
				largestValue = playersWinners[increment];
				overAllWinner= increment;
			}
			increment++;
		}
		System.out.println("The overall winner is " + overAllWinner);
	}


	/** Helper method to update the stats which are needed
	* 	for the report
	*/
	private void getStats() {
			noDraws = noDraws + round.getNoDraws();
			noRounds++;
			for (int i=0; i<noPlayers; i++) {
				playersWinners[i] = playersWinners[i] + round.getPlayerWinners()[i];
			}
			System.out.println("The number of ROUNDS " +noRounds);
			System.out.println("The number of DRAWS"+noDraws);
			for (int i=0; i<playersWinners.length; i++) {
				System.out.println("Array of winning tally " + playersWinners[i]);
		}
	}

	/**Method to format report parameters and instantiate
	*  the Report class
	*/
	private void getReport() {
		for (int i=0; i<noPlayers; i++) {
			p1Wins = playersWinners[PLAYER_ONE];
			p2Wins = playersWinners[PLAYER_TWO];
			if (noPlayers ==3) {
				p3Wins = playersWinners[PLAYER_THREE];
			}
			if(noPlayers ==4) {
				p3Wins = playersWinners[PLAYER_THREE];
				p4Wins = playersWinners[PLAYER_FOUR];
			} 	
			if(noPlayers ==5) {
				p3Wins = playersWinners[PLAYER_THREE];
				p4Wins = playersWinners[PLAYER_FOUR];
				p5Wins = playersWinners[PLAYER_FIVE];
			}	
		}
		String reportInfo = (noPlayers +"," +noDraws +","+overAllWinner+","+noRounds
			+ "," +p1Wins+","+p2Wins+","+p3Wins+","+p4Wins+","+p5Wins);
		System.out.println(reportInfo);

		Report report = new Report("EndReport", reportInfo);
	}

}
