import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * GUI to display the Game report
 * 
 */

public class Report extends JFrame implements ActionListener{

	/**
	 * Constructor for the Report
	 * parameters can be added when ready
	 */
	private JButton saveButton, exitButton; 
	private JLabel title, noGamesLbl, pcWinLbl, humWinLbl, drawsLbl, roundsLbl,
	noGamesLblRes, pcWinLblRes, humWinLblRes, drawsLblRes, roundsLblRes; 
	private JPanel top, middle, bottom; 
	private GridLayout infoGl; 
	


	public Report(){
		//make frame
		setTitle("TopTrumps Game");
		setSize(230, 240);
		setLocation(300, 100);
		setDefaultCloseOperation((DISPOSE_ON_CLOSE));
		makeTop(); 
		//make middle	
		makeMiddle(); 
		//make bottom
		makeBottom(); 
		
		
		
		
		setVisible(true); 

		
	
		connectionCheck (); 
	}
	
	public void makeTop(){
		JPanel top = new JPanel();
		title = new JLabel("TopTrumps Triumphs"); 
		title.setFont(new Font("Andale Mono 14", Font.PLAIN, 22));	
		top.add(title);
		add(top, BorderLayout.NORTH);
			
	}
	
	public void makeMiddle(){
		JPanel middle = new JPanel();
		
			
		noGamesLbl = new JLabel("Games Played: "); 
		noGamesLblRes = new JLabel("Bush");
		pcWinLbl = new JLabel(" Number of PC wins:"); 
		pcWinLblRes= new JLabel("did ");
		humWinLbl = new JLabel("Number of human wins:");
		humWinLblRes = new JLabel("9");
		drawsLbl = new JLabel("Number of Draws:"); 
		drawsLblRes= new JLabel("/"); 
		roundsLbl = new JLabel("Number of rounds:"); 
		roundsLblRes= new JLabel("11"); 
		
	
			 
		middle.add (noGamesLbl);
		middle.add(noGamesLblRes);
		middle.add(pcWinLbl); 
		middle.add(pcWinLblRes); 
		middle.add(humWinLbl); 
		middle.add(humWinLblRes); 
		middle.add(drawsLbl); 
		middle.add(drawsLblRes); 
		middle.add(roundsLbl); 
		middle.add(roundsLblRes); 
		
		
	
		add(middle, BorderLayout.CENTER);
		
		setVisible(true);
			
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
		
		//actions performed upon pressing a button 
		
		if (e.getSource() == saveButton) {
			 saveResults(); 
		}
		else { 
			//close frame
		}
	}
	
	public boolean connectionCheck (){
	/**
	 * This method checks if the system is online.
	 * If not it connects and returns true
	 * If yes it just returns true
	 */
		
			return true; 
	}
	
	public void saveResults(){
		/**
		 * This method saves the data
		 * from the database into the txt
		 * file
		 */
	}
		
}



