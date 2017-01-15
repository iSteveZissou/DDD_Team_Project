import java.awt.*;
import javax.swing.*;

/**
 * GUI to display the gameplay interface
 * 
 */

public class Gameplay extends JFrame{

	/** Instance variables */
	private JPanel pan;
	private JButton play;
	private int noPlayers;

	/**
	 * constructor for GameplayGUI
	 * [parameters can be added as needed]
	 */
	public Gameplay(){

		// helper method to get number of players
		this.noPlayer();

		this.setSize(500, 300);
		this.setLocation(200, 200);
		this.setTitle("Top Trumps!");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.layoutComponents();

	}

	/**
	 * layout all the components
	 * Will move into seperate helper methods*****!
	 *  
	 */
	public void layoutComponents(){

		// This is all pretty much just placeholders for now
		
		JLabel welcome = new JLabel("Top Trumps!!");
		welcome.setFont(new Font("Courier", Font.PLAIN, 24));

		// Layout for top of panel
		pan = new JPanel();
		this.add(pan, "North");
		pan.add(welcome);
		pan.setBackground(Color.cyan);

		GridLayout grid = new GridLayout(5, 2);
		JPanel pan3 = new JPanel(grid);
		this.add(pan3, "West");

		GridLayout grid2 = new GridLayout(5, 2);
		GridLayout cGrid = new GridLayout(10, 1);
		
		JPanel eastPan = new JPanel(grid2);
		this.add (eastPan, "East");
		
		
		// Center Panel layout
		JTextArea tArea = new JTextArea(10, 30);
		//tArea.setEditable(false);
		tArea.setBackground(Color.cyan);

		JLabel comPile = new JLabel("Communal pile: ");

		JPanel centerPan = new JPanel();
		this.add(centerPan, "Center");
		centerPan.add(tArea);

		/* Contains the layout for the east and west panels with all the 
		information for each player*/
		JLabel p2 = new JLabel(" Player 2 ");
		pan3.add(p2);
		p2.setFont(new Font("Courier", Font.BOLD, 16));
		JLabel score2 = new JLabel("  Score: ");
		JLabel score3 = new JLabel("  Score: ");
		JLabel score4 = new JLabel("  Score: ");
		JLabel score5 = new JLabel("  Score: ");
		pan3.add(score2);

		JLabel p3 = new JLabel(" Player 3 ");
		p3.setFont(new Font("Courier", Font.BOLD, 16));
		eastPan.add(p3);
		eastPan.add(score3);

		JLabel p4 = new JLabel(" Player 4 ");
		p4.setFont(new Font("Courier", Font.BOLD, 16));
		pan3.add(p4);
		pan3.add(score4);

		JLabel p5 = new JLabel(" Player 5 ");
		p5.setFont(new Font("Courier", Font.BOLD, 16));
		eastPan.add(p5);
		eastPan.add(score5);


		//Bottom panel used for the player/user
		
		String descrip = "T-Rex";
		// for testing ^^^
		play = new JButton("Play!");
		JPanel pan2 = new JPanel();
		JLabel cat = new JLabel("Categories:");
		JLabel topCard = new JLabel("Top Card:");
		JTextField card = new JTextField(descrip.length());
		card.setText(descrip);
		card.setEditable(false);

		this.add(pan2, "South");
		pan2.add(topCard);
		pan2.add(card);
		pan2.add(cat);

		String [] testString = {"height: 9", "weight: 5", "length: 7"};
		JComboBox<String> jcom = new JComboBox<String>(testString);
		pan2.add(jcom);
		pan2.add(play);
		pan2.setBackground(Color.cyan);


		// End of round result test
		JLabel result = new JLabel("YOU WIN!!");
		result.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		centerPan.add(result);


	}

	/**
	 * Helper method to get the number of players for a game
	 * Launches JOptionPane with JCombo
	 * @return noPlayers - the number of players
	 */
	public int noPlayer(){

		String[] players = { "2", "3", "4", "5", };
		JComboBox<String> jBox  = new JComboBox <String>(players);
		
		JOptionPane.showMessageDialog(null, jBox, "Players", JOptionPane.PLAIN_MESSAGE);
		String s = ( String) jBox.getSelectedItem();
		noPlayers = Integer.parseInt(s);

		System.out.println("Number of players: "+ noPlayers);

		return noPlayers;
	}







}