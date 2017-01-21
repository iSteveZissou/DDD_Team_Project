// to create the Report the string will need to pass "read".Prob will
//need DB connection

//to createEndReport
//Report write = new Report("write","6,Player One, 50, 66,2,3,4,5");




//this assumes we have all 5 players.
//this needs to be updated to reflect
//the actual number of players 

//buttons dont work - pls help. 

//

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.*;

/**
 * GUI to display the Game report
 * 
 */

public class Report extends JFrame implements ActionListener{

	private static final ActionListener ActionPerformed = null;
	private String type; 
	private JButton saveButton, exitButton, ngButton;  
	private JPanel top, middle, bottom; 
	private GridLayout infoGl; 
	
	private String noDraws, winner, noRounds, p1win, p2win, p3win, p4win, p5win; 
	
	public Report(String style, String data){
		//make frame
		setTitle("TopTrumps Results!!!!!!!");
		setSize(500, 400);
		setLocation(300, 100);
		setDefaultCloseOperation((DISPOSE_ON_CLOSE));
		makeTop(); 
		type = style; 
		
		if (type.equals("read")){
			makeReadMiddle();
		}
		else{			
			splitInput(data);			
			makeWriteMiddle();		
		}	
		
		makeBottom();
		setVisible(true); 
	}
	
	public void splitInput(String data){
		
		String[] result = data.split("\\,"); 
		
		noDraws = result[0]; 
		winner = result[1]; 
		noRounds = result[2]; 
		p1win = result[3];
		p2win= result[4]; 
		p3win = result[5];
		p4win = result[6];
		p5win= result[7];
	}
	
	public void makeTop(){
		JPanel top = new JPanel();
		JLabel title = new JLabel("TopTrumps Triumphs"); 
		title.setFont(new Font("Andale Mono 14", Font.PLAIN, 40));	
		top.add(title);
		add(top, BorderLayout.NORTH);		
	}
	
	public void makeReadMiddle(){
		SQLMethods sql = new SQLMethods(); 
		
		
		JLabel noGamesLbl, pcWinLbl, humWinLbl, drawsLbl, roundsLbl,
		noGamesLblRes, pcWinLblRes, humWinLblRes, drawsLblRes, roundsLblRes; 
	
		GridLayout gridMiddle = new GridLayout(5, 2); //rows, columsn
		JPanel panMiddle = new JPanel(gridMiddle);
	
		noGamesLbl = new JLabel("Games Played: "); 
		noGamesLbl.setHorizontalAlignment(JLabel.CENTER);
		noGamesLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		noGamesLblRes = new JLabel(Integer.toString(sql.gameCount()));
		noGamesLblRes.setHorizontalAlignment(JLabel.CENTER);
		noGamesLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		pcWinLbl = new JLabel(" Number of PC wins:"); 
		pcWinLbl.setHorizontalAlignment(JLabel.CENTER);
		pcWinLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		pcWinLblRes= new JLabel(Integer.toString(sql.compWin()));
		pcWinLblRes.setHorizontalAlignment(JLabel.CENTER);
		pcWinLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		humWinLbl = new JLabel("Number of human wins:");
		humWinLbl.setHorizontalAlignment(JLabel.CENTER);
		humWinLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		humWinLblRes = new JLabel(Integer.toString(sql.humanWinner()));
		humWinLblRes.setHorizontalAlignment(JLabel.CENTER);
		humWinLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		drawsLbl = new JLabel("Average number of Draws:"); 
		drawsLbl.setHorizontalAlignment(JLabel.CENTER);
		drawsLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		drawsLblRes= new JLabel(Integer.toString(sql.avgDraws())); 
		drawsLblRes.setHorizontalAlignment(JLabel.CENTER);
		drawsLblRes.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		roundsLbl = new JLabel("Most rounds played:"); 
		roundsLbl.setHorizontalAlignment(JLabel.CENTER);
		roundsLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		
		roundsLblRes= new JLabel(Integer.toString(sql.largestRounds())); 
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
	
	public void makeWriteMiddle(){
		JLabel noDrawsLbl, winnerLbl, noRoundsLbl, p1winLbl, p2winLbl, p3winLbl, p4winLbl, p5winLbl,
		noDrawsRls, winnerRls, noRoundsRsl, p1winRsl, p1winRls, p2winRls, p3winRls, p4winRls, p5winRls; 
				
		GridLayout gridMiddle = new GridLayout(8, 2); //rows, columsn
		JPanel panMiddle = new JPanel(gridMiddle);
		
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
		
		p3winLbl = new JLabel("Player 3 round wins: " ); 
		p3winLbl.setHorizontalAlignment(JLabel.CENTER);
		p3winLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (p3winLbl);
				
		p3winRls = new JLabel(p3win); 
		p3winRls.setHorizontalAlignment(JLabel.CENTER);
		p3winRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (p3winRls);
	
		
		p4winLbl = new JLabel("Player 4 round wins: " ); 
		p4winLbl.setHorizontalAlignment(JLabel.CENTER);
		p4winLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (p4winLbl);
		
		p4winRls = new JLabel(p4win); 
		p4winRls.setHorizontalAlignment(JLabel.CENTER);
		p4winRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));	
		panMiddle.add (p4winRls);
		
		p5winLbl = new JLabel("Player 5 round wins: " ); 
		p5winLbl.setHorizontalAlignment(JLabel.CENTER);
		p5winLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		panMiddle.add (p5winLbl);
		
		p5winRls = new JLabel(p5win); 
		p5winRls.setHorizontalAlignment(JLabel.CENTER);
		p5winRls.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		panMiddle.add (p5winRls);
		
		add(panMiddle, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void makeBottom(){
		JPanel bottom = new JPanel();
		JButton saveButton = new JButton("Save Results"); 
		JButton exitButton = new JButton("Exit"); 
		JButton ngButton = new JButton("New Game"); 
		
		saveButton.addActionListener(this);
		exitButton.addActionListener(e -> this.dispose()); //not sure if we can use
		ngButton.addActionListener(this);
		
		bottom.add(saveButton); 
		bottom.add(exitButton); 
		
		if (type.equals("write")){
		//	bottom.add(ngButton); 
		}
		
		add(bottom, BorderLayout.SOUTH);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		//actions performed upon pressing a button, big if statement here 
		
		if (e.getSource() == saveButton) {
			
		}
		else {
			System.out.println("whaddup");
			saveResults(); 
		}
		
	}
			
	public void saveResults(){
		String gameRecord = String.format("%s,'%s',%s", noDraws, winner, noRounds);
		String roundRecord= String.format("%s,%s,%s,%s,%s", p1win, p2win, p3win, p4win,p5win); 
		
		SQLMethods save = new SQLMethods(); 
		save.writeGameplay(gameRecord);
		save.writeRoundPlay(roundRecord);
		
		
		
		
	}
		
}