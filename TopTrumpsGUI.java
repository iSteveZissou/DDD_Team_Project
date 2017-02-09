import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Main GUI class to display initial start scree
 * and listen for events
 */

public class TopTrumpsGUI extends JFrame implements ActionListener{

	/** Instance variable  */
	private JButton newGame, reportButton, exitButton;
	
	/**
	 * Contructor for TopTrumpsGUI
	 */
	public TopTrumpsGUI(){

		this.setSize(300, 150);
		this.setLocation(100,100);
		this.setTitle ("Top Trumps!");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// call layout method
		this.layOut();
	}
	/**
	 * Adds the layout components
	 */
	public void layOut(){
	
		JPanel pan = new JPanel();
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();

		newGame = new JButton("NEW GAME");
		newGame.addActionListener(this);
		
		reportButton = new JButton("REPORT");
		reportButton.addActionListener(this);
		exitButton = new JButton("EXIT");
		exitButton.addActionListener(this);

		this.add(pan, "North");
		pan.setBackground(Color.yellow);
		this.add(pan1, "Center");
		pan1.setBackground(Color.cyan);
		this.add(pan2, "South");
		pan2.setBackground(Color.gray);

		pan.add(newGame);
		pan1.add(reportButton);
		pan2.add(exitButton);

		this.setVisible(true);

	}
	/**
	 * Method to start the main game
	 */
	public void startGame(){

		Gameplay nGame = new Gameplay();

	}

	/**
	 *  Generate new report
	 */
	public void report(){

		Report report = new Report ("Report",""); 
	}

	/**
	 * Listen and act on button press events
	 * @param e the event
	 */
	public void actionPerformed(ActionEvent e){

		if (e.getSource()==newGame){
			//start new Game
			this.startGame();
			System.out.println("New Game Begins");
		}

		else if (e.getSource()==reportButton){
			this.report();
			System.out.println("Generate report");		
		}

		else if (e.getSource()==exitButton){
			System.exit(0);
		}

	}

}