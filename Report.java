import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.*;

/**
 * GUI to display the Game report
 * 
 */

public class Report extends JFrame implements ActionListener{

	private int noPlayers; 
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
		if (type.equals("Report")){  //report with overall data
			getResultsSQL(); 
			makeReportMiddle();
		}
		else{			
			splitInput(data);		//report with game data
			makeEndReportMiddle();		
		}	
		
		makeBottom();
		setVisible(true); 
	}
	
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
	
	public String getWinnerString(int winnerNumber){
		String winner = ""; 
		
		if (winnerNumber == 1){
			winner = "Player One"; 
		}
		if (winnerNumber == 2){
			winner = "Player Two"; 
		}
		if (winnerNumber == 3){
			winner = "Player Three"; 
		}
		if (winnerNumber == 4){
			winner = "Player Four"; 
		}
		if (winnerNumber == 5){
			winner = "Player Fine"; 
		}
		
		
		return winner; 
	}
	
	public void makeTop(){
		JPanel top = new JPanel();
		top.setBackground(Color.cyan);
		JLabel title = new JLabel("TopTrumps Triumphs"); 
		title.setFont(new Font("Andale Mono 14", Font.PLAIN, 40));	
		top.add(title);
		add(top, BorderLayout.NORTH);	
		
	}
	
	public void makeReportMiddle(){
			
		JLabel noGamesLbl, pcWinLbl, humWinLbl, drawsLbl, roundsLbl,
		noGamesLblRes, pcWinLblRes, humWinLblRes, drawsLblRes, roundsLblRes; 
	
		GridLayout gridMiddle = new GridLayout(5, 2); //rows, columsn
		JPanel panMiddle = new JPanel(gridMiddle);
		
		panMiddle.setBackground(Color.cyan);
				
		noGamesLbl = new JLabel(noGamesStr); 
		noGamesLbl.setHorizontalAlignment(JLabel.CENTER);
		noGamesLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		noGamesLblRes = new JLabel(noGamesVal);
		noGamesLblRes.setHorizontalAlignment(JLabel.CENTER);
		noGamesLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		pcWinLbl = new JLabel(pcWinsStr); 
		pcWinLbl.setHorizontalAlignment(JLabel.CENTER);
		pcWinLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		pcWinLblRes= new JLabel(pcWinsVal);
		pcWinLblRes.setHorizontalAlignment(JLabel.CENTER);
		pcWinLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		humWinLbl = new JLabel(humanWinStr);
		humWinLbl.setHorizontalAlignment(JLabel.CENTER);
		humWinLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		humWinLblRes = new JLabel(humanWinVal);
		humWinLblRes.setHorizontalAlignment(JLabel.CENTER);
		humWinLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		drawsLbl = new JLabel(drawsStr); 
		drawsLbl.setHorizontalAlignment(JLabel.CENTER);
		drawsLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		drawsLblRes= new JLabel(drawsVal); 
		drawsLblRes.setHorizontalAlignment(JLabel.CENTER);
		drawsLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		roundsLbl = new JLabel(roundsStr); 
		roundsLbl.setHorizontalAlignment(JLabel.CENTER);
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
	
	public void makeEndReportMiddle(){
		JLabel noDrawsLbl, winnerLbl, noRoundsLbl, p1winLbl, p2winLbl, p3winLbl, p4winLbl, p5winLbl,
		noDrawsRls, winnerRls, noRoundsRsl, p1winRsl, p1winRls, p2winRls, p3winRls, p4winRls, p5winRls; 
				
		GridLayout gridMiddle = new GridLayout(8, 2); //rows, columns
		JPanel panMiddle = new JPanel(gridMiddle);
		
		panMiddle.setBackground(Color.cyan);
		
		noDrawsLbl = new JLabel("Number of Draws: "); 
		noDrawsLbl.setHorizontalAlignment(JLabel.CENTER);
		noDrawsLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (noDrawsLbl);
		
		noDrawsRls = new JLabel(noDraws); 
		noDrawsRls.setHorizontalAlignment(JLabel.CENTER);
		noDrawsRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (noDrawsRls);
		
		winnerLbl = new JLabel("Winner: "); 
		winnerLbl.setHorizontalAlignment(JLabel.CENTER);
		winnerLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (winnerLbl);

		winnerRls = new JLabel(winner); 
		winnerRls.setHorizontalAlignment(JLabel.CENTER);
		winnerRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (winnerRls);
		
		noRoundsLbl = new JLabel("Rounds Played: "); 
		noRoundsLbl.setHorizontalAlignment(JLabel.CENTER);
		noRoundsLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		panMiddle.add (noRoundsLbl);
		
		noRoundsRsl = new JLabel(noRounds); 
		noRoundsRsl.setHorizontalAlignment(JLabel.CENTER);
		noRoundsRsl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (noRoundsRsl);
		
		p1winLbl = new JLabel("Player 1 round wins: " ); 
		p1winLbl.setHorizontalAlignment(JLabel.CENTER);
		p1winLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (p1winLbl);
		
		p1winRls = new JLabel(p1win); 
		p1winRls.setHorizontalAlignment(JLabel.CENTER);
		p1winRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (p1winRls);
		
		p2winLbl = new JLabel("Player 2 round wins: " ); 
		p2winLbl.setHorizontalAlignment(JLabel.CENTER);
		p2winLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (p2winLbl);
			
		p2winRls = new JLabel(p2win); 
		p2winRls.setHorizontalAlignment(JLabel.CENTER);
		p2winRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (p2winRls);
		
		if (noPlayers < 6 && noPlayers > 2 ){ 
			
			p3winLbl = new JLabel("Player 3 round wins: " ); 
			p3winLbl.setHorizontalAlignment(JLabel.CENTER);
			p3winLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
			panMiddle.add (p3winLbl);
		
			
			p3winRls = new JLabel(p3win); 
			p3winRls.setHorizontalAlignment(JLabel.CENTER);
			p3winRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
			panMiddle.add (p3winRls);
			
			
			if (noPlayers < 6 && noPlayers > 3 ){
				p4winLbl = new JLabel("Player 4 round wins: " ); 
				p4winLbl.setHorizontalAlignment(JLabel.CENTER);
				p4winLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
				panMiddle.add (p4winLbl);
				
				p4winRls = new JLabel(p4win); 
				p4winRls.setHorizontalAlignment(JLabel.CENTER);
				p4winRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
				panMiddle.add (p4winRls);
	
		
				if (noPlayers <  6 && noPlayers > 4 ){	
					p5winLbl = new JLabel("Player 5 round wins: " ); 
					p5winLbl.setHorizontalAlignment(JLabel.CENTER);
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
	
	public void makeBottom(){
		JPanel bottom = new JPanel();
		
		bottom.setBackground(Color.cyan);
		saveButton = new JButton("Save Results"); 
		exitButton = new JButton("Exit"); 
		ngButton = new JButton("New Game"); 
		
		saveButton.addActionListener(this);
		//exitButton.addActionListener(e -> this.dispose()); //not sure if we can use
		exitButton.addActionListener(this); 
		ngButton.addActionListener(this);
		
		if (type.equals("EndReport")){
			bottom.add(ngButton); 
			}
		
		bottom.add(saveButton); 
		bottom.add(exitButton); 
		
		add(bottom, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {

		
		if (e.getSource() == ngButton){
		 //new game
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
			
		}
	}	
	
	
	public void saveResultsFile(){
		PrintWriter saveData = null; 
		String Report = String.format("%s %s\n%s %s\n%s %s\n%s %s\n%s %s\n", 
				noGamesStr,noGamesVal,pcWinsStr,pcWinsVal,humanWinStr,humanWinVal,drawsStr,drawsVal,roundsStr,roundsVal);								
		try{
			
		saveData = new PrintWriter("gameStats.txt");
		saveData.write(Report);
		
		}catch(FileNotFoundException e){ JOptionPane.showMessageDialog(null, "File not Found",
				"Error: No File", JOptionPane.ERROR_MESSAGE);
			
		}
		
		saveData.close();
	}
	
	public void getResultsSQL() {
		
		SQLMethods sql = new SQLMethods(); 
		
		noGamesVal = Integer.toString(sql.gameCount()); 
		pcWinsVal = Integer.toString(sql.compWin()); 
		humanWinVal = Integer.toString(sql.humanWinner()); 		
		drawsVal = Integer.toString(sql.avgDraws()); 
		roundsVal = Integer.toString(sql.largestRounds()); 
		
		sql.closeConnection();
		
	}
	
	public void saveResultsSQL(){
		
		String gameRecord = String.format("%s,'%s',%s", noDraws, winner, noRounds);
		String roundRecord= String.format("%s,%s,%s,%s,%s", p1win, p2win, p3win, p4win,p5win); 
		
		SQLMethods save = new SQLMethods(); 
		save.generateGameID();
		save.writeGameplay(gameRecord);
		save.writeRoundPlay(roundRecord);
		save.closeConnection();

	}
		
}