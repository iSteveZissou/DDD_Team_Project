import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.*;

/**
 * Class to display the game reports. Class can handle 2 different 
 * types of reports based on the input provided in the constructor.
 * 
 * Report refers to the report accessible from the main menu and outputs
 * information from the database. This data can be saved to a text file
 * 
 * EndReport refers to the report accessible after the game ends. Game 
 * data is output in the GUI and the user has the option to save it into 
 * the database.
 * 
 */

public class Report extends JFrame implements ActionListener{

	private int noPlayers, count = 0; 
	private JButton saveButton, exitButton, ngButton;  
	private String type, noDraws, winner, noRounds, p1win, p2win, p3win, p4win, p5win, noGamesVal, pcWinsVal, humanWinVal, drawsVal, roundsVal; 
	private String noGamesStr = "Games Played: "; 
	private String pcWinsStr = "Number of PC wins: "; 
	private String humanWinStr = "Number of human wins: "; 
	private String drawsStr = "Average number of Draws: "; 
	private String roundsStr = "Most rounds played: " ; 
	
	public Report(String style, String data){
		//make frame
		setTitle("TopTrumps Results!!!!!!!");
		setSize(500, 400);
		setLocation(300, 100);
		setDefaultCloseOperation((DISPOSE_ON_CLOSE));
		makeTop(); 
		type = style; 
		
		//Displays different report based on input		
		if (type.equals("Report")){  //report with overall data (pulled from SQL database) 
			getResultsSQL();  //gets results from the database about the past games 
			makeReportMiddle();
		}
		else{			
			splitInput(data);		//report with game data ( pulled from the game play)
			makeEndReportMiddle();		
		}	
		
		makeBottom();
		setVisible(true); 
	}
	/**
	 * Method which splits the post-game data 
	 * and assigns it to appropriate variables. 
	 * 
	 */
	
	public void splitInput(String data){
		
		String[] result = data.split("\\,"); 
		
		noPlayers = Integer.parseInt(result[0]); 
		noDraws = result[1]; 
		winner = getWinnerString(Integer.parseInt(result[2])); 
		noRounds = result[3]; 
		p1win = result[4];
		p2win= result[5]; 
						
		if (noPlayers < 6 && noPlayers > 2) {
			p3win = result[6];
		
			if (noPlayers < 6 && noPlayers > 3){
				p4win = result[7];
	
				if (noPlayers <  6 && noPlayers > 4 ){
					p5win= result[8];
				}
			}
		}
	}
	
	/**
	 * Method which takes in the player number and converts it
	 *  into string with regards to the player name
	 * 
	 *@return String of the player name
	 */
	
	public String getWinnerString(int winnerNumber){
		String winner = ""; 
		
		if (winnerNumber == 0){
			winner = "Player One"; 
		}
		if (winnerNumber == 1){
			winner = "Player Two"; 
		}
		if (winnerNumber == 2){
			winner = "Player Three"; 
		}
		if (winnerNumber == 3){
			winner = "Player Four"; 
		}
		if (winnerNumber == 4){
			winner = "Player Five"; 
		}
		
		return winner; 
	}
	
	/**
	 * Builds the top panel of the GUI
	 */
	
	public void makeTop(){
		JPanel top = new JPanel();
		top.setBackground(Color.yellow);
		JLabel title = new JLabel("TopTrumps Triumphs"); 
		title.setFont(new Font("Courier", Font.BOLD, 40));	
		top.add(title);
		add(top, BorderLayout.NORTH);	
		
	}
	/**
	 * Builds the middle panel of the Report 
	 */
	
	public void makeReportMiddle(){
			
		JLabel noGamesLbl, pcWinLbl, humWinLbl, drawsLbl, roundsLbl,
		noGamesLblRes, pcWinLblRes, humWinLblRes, drawsLblRes, roundsLblRes; 
	
		GridLayout gridMiddle = new GridLayout(5, 2); //rows, columns
		JPanel panMiddle = new JPanel(gridMiddle);
		
		panMiddle.setBackground(Color.cyan);
				
		noGamesLbl = new JLabel( " " + noGamesStr); 
		noGamesLbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		noGamesLblRes = new JLabel(noGamesVal);
		noGamesLblRes.setHorizontalAlignment(JLabel.CENTER);
		noGamesLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		pcWinLbl = new JLabel( " " + pcWinsStr); 
		pcWinLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		pcWinLblRes= new JLabel(pcWinsVal);
		pcWinLblRes.setHorizontalAlignment(JLabel.CENTER);
		pcWinLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		humWinLbl = new JLabel( " " + humanWinStr);
		humWinLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		humWinLblRes = new JLabel(humanWinVal);
		humWinLblRes.setHorizontalAlignment(JLabel.CENTER);
		humWinLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		drawsLbl = new JLabel( " " + drawsStr); 
		drawsLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		drawsLblRes= new JLabel(drawsVal); 
		drawsLblRes.setHorizontalAlignment(JLabel.CENTER);
		drawsLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		roundsLbl = new JLabel( " " + roundsStr); 
		roundsLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		roundsLblRes= new JLabel(roundsVal); 
		roundsLblRes.setHorizontalAlignment(JLabel.CENTER);
		roundsLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
				 	
		panMiddle.add (noGamesLbl);
		panMiddle.add(noGamesLblRes);
		panMiddle.add(pcWinLbl); 
		panMiddle.add(pcWinLblRes); 
		panMiddle.add(humWinLbl); 
		panMiddle.add(humWinLblRes); 
		panMiddle.add(drawsLbl); 
		panMiddle.add(drawsLblRes); 
		panMiddle.add(roundsLbl); 
		panMiddle.add(roundsLblRes); 

		add(panMiddle, BorderLayout.CENTER);
		setVisible(true);
			
	}
	
	/**
	 * Builds the middle panel of the EndReport 
	 */
	
	public void makeEndReportMiddle(){
		JLabel noDrawsLbl, winnerLbl, noRoundsLbl, p1winLbl, p2winLbl, p3winLbl, p4winLbl, p5winLbl,
		noDrawsRls, winnerRls, noRoundsRsl, p1winRsl, p1winRls, p2winRls, p3winRls, p4winRls, p5winRls; 
				
		GridLayout gridMiddle = new GridLayout(8, 2); //rows, columns
		JPanel panMiddle = new JPanel(gridMiddle);
		
		panMiddle.setBackground(Color.cyan);
		
		noDrawsLbl = new JLabel(" Number of Draws: "); 
		noDrawsLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (noDrawsLbl);
		
		noDrawsRls = new JLabel(noDraws); 
		noDrawsRls.setHorizontalAlignment(JLabel.CENTER);
		noDrawsRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (noDrawsRls);
		
		winnerLbl = new JLabel(" Winner: "); 
		winnerLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (winnerLbl);

		winnerRls = new JLabel(winner); 
		winnerRls.setHorizontalAlignment(JLabel.CENTER);
		winnerRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (winnerRls);
		
		noRoundsLbl = new JLabel(" Rounds Played: "); 
		noRoundsLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		panMiddle.add (noRoundsLbl);
		
		noRoundsRsl = new JLabel(noRounds); 
		noRoundsRsl.setHorizontalAlignment(JLabel.CENTER);
		noRoundsRsl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (noRoundsRsl);
		
		p1winLbl = new JLabel(" Player 1 round wins: " ); 
		p1winLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (p1winLbl);
		
		p1winRls = new JLabel(p1win); 
		p1winRls.setHorizontalAlignment(JLabel.CENTER);
		p1winRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (p1winRls);
		
		p2winLbl = new JLabel(" Player 2 round wins: " ); 
		p2winLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (p2winLbl);
			
		p2winRls = new JLabel(p2win); 
		p2winRls.setHorizontalAlignment(JLabel.CENTER);
		p2winRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (p2winRls);
		
		
		//depending on the number of players, player labels are displayed accordingly.
		if (noPlayers < 6 && noPlayers > 2 ){ 
			
			p3winLbl = new JLabel(" Player 3 round wins: " ); 
			p3winLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
			panMiddle.add (p3winLbl);
		
			
			p3winRls = new JLabel(p3win); 
			p3winRls.setHorizontalAlignment(JLabel.CENTER);
			p3winRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
			panMiddle.add (p3winRls);
			
			
			if (noPlayers < 6 && noPlayers > 3 ){
				p4winLbl = new JLabel(" Player 4 round wins: " ); 
				p4winLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
				panMiddle.add (p4winLbl);
				
				p4winRls = new JLabel(p4win); 
				p4winRls.setHorizontalAlignment(JLabel.CENTER);
				p4winRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
				panMiddle.add (p4winRls);
	
		
				if (noPlayers <  6 && noPlayers > 4 ){	
					p5winLbl = new JLabel(" Player 5 round wins: " ); 
					p5winLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
					panMiddle.add (p5winLbl);
					
					p5winRls = new JLabel(p5win); 
					p5winRls.setHorizontalAlignment(JLabel.CENTER);
					p5winRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
					panMiddle.add (p5winRls);
					
				}
			}
			
		}
		
		add(panMiddle, BorderLayout.CENTER);
		setVisible(true);
	}
	
	/**
	 * Builds the bottom panel of the GUI, 
	 * Depending on the type of report different 
	 * buttons will be displayed
	 *  
	 */
	public void makeBottom(){
		JPanel bottom = new JPanel();
		
		bottom.setBackground(Color.gray);
		saveButton = new JButton("Save Results"); 
		exitButton = new JButton("Main Menu"); 
		ngButton = new JButton("New Game"); 
		
		saveButton.addActionListener(this);
		exitButton.addActionListener(this); 
		ngButton.addActionListener(this);
		
		
		if (type.equals("EndReport")){
			bottom.add(ngButton); 
			}
		
		bottom.add(saveButton); 
		bottom.add(exitButton); 
		
		add(bottom, BorderLayout.SOUTH);
	}
	
	/**
	 * Action listeners for buttons
	 *  
	 */
	public void actionPerformed(ActionEvent e) {

		
		if (e.getSource() == ngButton){
			//launches a new game
			Gameplay nGame = new Gameplay();
			this.dispose();
		}
		
		
		if (e.getSource() == saveButton) {
				if (type.equals("Report")){
					saveResultsFile(); 
				}
				else {
					saveResultsSQL(); 
				}
		}
		
		if(e.getSource() == exitButton){
			//return to game menu
			TopTrumpsGUI gui = new TopTrumpsGUI();
			this.dispose();
		}
	}	
	
	/**
	 * Method to save results from the database 
	 *  
	 */
	public void saveResultsFile(){
		PrintWriter saveData = null; 
		String Report = String.format("%s %s \n %s %s \n %s %s \n %s %s \n %s %s \n", 
				noGamesStr,noGamesVal,pcWinsStr,pcWinsVal,humanWinStr,humanWinVal,drawsStr,drawsVal,roundsStr,roundsVal);								
		try{
			
		saveData = new PrintWriter("gameStats.txt");
		saveData.write(Report);
		
		JOptionPane.showMessageDialog(null, "Game results saved to file",
				"Game Saved", JOptionPane.INFORMATION_MESSAGE);
		
		}catch(FileNotFoundException e){ JOptionPane.showMessageDialog(null, "File not Found",
				"Error: No File", JOptionPane.ERROR_MESSAGE);
			
		}
		
		saveData.close();
	}
	
	/**
	 * Method to get the results from the database 
	 *  
	 */
	public void getResultsSQL() {
		
		SQLMethods sql = new SQLMethods(); 
		
		noGamesVal = Integer.toString(sql.gameCount()); 
		pcWinsVal = Integer.toString(sql.compWin()); 
		humanWinVal = Integer.toString(sql.humanWinner()); 		
		drawsVal = Integer.toString(sql.avgDraws()); 
		roundsVal = Integer.toString(sql.largestRounds()); 
		
		sql.closeConnection();
		
	}
	/**
	 * Method to save results into the database 
	 * the counter allows the file to be saved only 
	 * once. Method also checks that the database saved successfully
	 *   
	 */
	public void saveResultsSQL(){
		
		
		if (count == 0){
			//create queries 
			String gameRecord = String.format("%s,'%s',%s", noDraws, winner, noRounds);
			String roundRecord= String.format("%s,%s,%s,%s,%s", p1win, p2win, p3win, p4win,p5win); 
		
			//write to database  
			SQLMethods save = new SQLMethods(); 
			boolean generateGameIDCheck  = save.generateGameID();
			boolean saveGameplayCheck = save.writeGameplay(gameRecord);
			boolean writeRoundPlayCheck  = save.writeRoundPlay(roundRecord);	
			boolean closeConnectionCheck = save.closeConnection();
			
			
			//checks if the game saved successfully
			if(generateGameIDCheck && saveGameplayCheck && writeRoundPlayCheck &&closeConnectionCheck){
				//increases the counter so the results cannot be saved more than once
				count++; 
				//Confirmation to the user.
				JOptionPane.showMessageDialog(null, "Game results saved to database",
						"Game Saved", JOptionPane.INFORMATION_MESSAGE);		
			}
			else {	
				JOptionPane.showMessageDialog(null, "Could not save data to database, please contact admin",
					"Error: Save", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		else{
			JOptionPane.showMessageDialog(null, "Game has already been saved",
					"Error: Save", JOptionPane.ERROR_MESSAGE);
			
		}

	}
		
}