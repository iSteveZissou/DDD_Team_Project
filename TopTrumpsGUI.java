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
	private int noPlayers;
	
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
	 * A JOptionPane will retrieve the users Choice of 
	 * how many players the game will have
	 * if user closes without making a choice then the game does not start
	 */
	public void startGame(){

		noPlayers =0;
		Object[] players = {"2", "3", "4", "5"};
		String s = (String)JOptionPane.showInputDialog(null, "How many players??",
                    "Player Select", JOptionPane.PLAIN_MESSAGE,null,players,"");

		if ((s != null) && (s.length() > 0)) {
			System.out.println("IT WORKS");
			noPlayers = Integer.parseInt(s);
			Gameplay nGame = new Gameplay(noPlayers);
			System.out.println("New Game Begins");
			this.dispose();
		}
		else{
			System.err.println("Player select Dialog closed");
		} 
		
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