import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * GUI to display the Game report
 * 
 */

public class Report extends JFrame implements ActionListener{

	private String type; 
	private JButton saveButton, exitButton;  
	private JPanel top, middle, bottom; 
	private GridLayout infoGl; 
	
	private String noDraws, winner, noRounds, p1win, p2win, p3wi, p4win, p5win; 
	
	public Report(String style, String data){
		//make frame
		setTitle("TopTrumps Results!!!!!!!");
		setSize(230, 240);
		setLocation(300, 100);
		setDefaultCloseOperation((DISPOSE_ON_CLOSE));
		makeTop(); 
		makeBottom(); 
		type = style; 
		
		if (type.equals("read")){
			makeReadMiddle();
			//makeReadBottom();
		}
		else{
			
			splitInput(data);			
			makeWriteMiddle();
		//	makeWriteBottom();
			
		}		
		setVisible(true); 
	}
	
	public void splitInput(String data){
		String[] result = data.split("\\,"); 
		
		noDraws = result[0]; 
		winner = result[1]; 
		noRounds = result[2]; 
		p1win = result[3];
		p2win= result[4]; 
		p3wi = result[5];
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
		JLabel noGamesLbl, pcWinLbl, humWinLbl, drawsLbl, roundsLbl,
		noGamesLblRes, pcWinLblRes, humWinLblRes, drawsLblRes, roundsLblRes; 
	
		GridLayout gridMiddle = new GridLayout(5, 2); //rows, columsn
		JPanel panMiddle = new JPanel(gridMiddle);
	
		noGamesLbl = new JLabel("Games Played: "); 
		noGamesLbl.setHorizontalAlignment(JLabel.CENTER);
		noGamesLbl.setFont(new Font("Andale Mono 14", Font.PLAIN, 18));
		noGamesLblRes = new JLabel("Bush");
		noGamesLblRes.setHorizontalAlignment(JLabel.CENTER);
		pcWinLbl = new JLabel(" Number of PC wins:"); 
		pcWinLblRes= new JLabel("did ");
		humWinLbl = new JLabel("Number of human wins:");
		humWinLblRes = new JLabel("9");
		drawsLbl = new JLabel("Number of Draws:"); 
		drawsLblRes= new JLabel("/"); 
		roundsLbl = new JLabel("Number of rounds:"); 
		roundsLblRes= new JLabel("11"); 
				 
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
		JLabel noDrawsLbl, winnerLbl, noRoundsLbl, p1winLbl, p2winLbl, p3winLbl, p4winLbl, p5winLbl; 
		
		GridLayout gridMiddle = new GridLayout(4, 2); //rows, columsn
		JPanel panMiddle = new JPanel(gridMiddle);
		
		noDrawsLbl = new JLabel("Number of Draws: "); 
		noDrawsLbl.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		/////////COMPLETE THIS FIRSTTTTTTTT
		
	}
	
	public void makeBottom(){
		JPanel bottom = new JPanel();
		JButton saveButton = new JButton("Save Results"); 
		JButton exitButton = new JButton("Exit"); 
		
		exitButton.addActionListener(e -> this.dispose()); //not sure if we can use
		saveButton.addActionListener(this);
		bottom.add(saveButton); 
		bottom.add(exitButton); 
		
		add(bottom, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//actions performed upon pressing a button, big statement here 
		
		if (e.getSource() == saveButton) {
			 saveResults(); 
		}
		else { 
			//close frame
		}
	}
		
	public void saveResults(){
		/**
		 * This method saves the data
		 * from the database into the txt
		 * file
		 */
	}
		
}