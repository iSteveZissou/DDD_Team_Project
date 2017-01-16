import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

/**
 * GUI to display the gameplay interface
 * 
 */
public class Gameplay extends JFrame implements ActionListener{

	/** Instance variables */
	private JPanel pan;
	private JButton playButton;
	private int noPlayers;
	private final int maxPlayer = 5;
	private JLabel p2, p3, p4, p5, score2, score3, score4, score5;

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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

		// Layout all the components
		this.layoutComponents();
		this.centerLayout();
		this.bottomPanel();

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

		p3 = new JLabel(" Player 3 ");
		p3.setFont(new Font("Courier", Font.BOLD, 16));
		eastPan.add(p3);
		eastPan.add(score3);

		p4 = new JLabel(" Player 4 ");
		p4.setFont(new Font("Courier", Font.BOLD, 16));
		pan3.add(p4);
		pan3.add(score4);

		p5 = new JLabel(" Player 5 ");
		p5.setFont(new Font("Courier", Font.BOLD, 16));
		eastPan.add(p5);
		eastPan.add(score5);


		if (noPlayers < maxPlayer){
			p5.setEnabled(false);
			score5.setEnabled(false);
		}
		if (noPlayers < maxPlayer-1){
			p4.setEnabled(false);
			score4.setEnabled(false);
		}
		if (noPlayers < maxPlayer - 2){
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

		int s = 9;
	
		JRadioButton cat1 = new JRadioButton("Height:");
		JRadioButton cat2 = new JRadioButton("Weight:");
		JRadioButton cat3 = new JRadioButton("Length:");
		JRadioButton cat4 = new JRadioButton("Ferocity:");
		JRadioButton cat5 = new JRadioButton("Intelligence:");

		ButtonGroup group = new ButtonGroup();
		group.add(cat1);
		group.add(cat2);
		group.add(cat3);
		group.add(cat4);
		group.add(cat5);

		centerPan.setBorder(new TitledBorder(new EtchedBorder(), "Top Card"));
		JLabel description = new JLabel(" T -Rex!");
		description.setFont(new Font("Courier", Font.BOLD, 18));

		//test Labels 
		JLabel blank = new JLabel("      ");
		JLabel val1 = new JLabel("" + s);
		val1.setFont(new Font("Courier", Font.BOLD, 16));
		//val1.setHorizontalAlignment(JLabel.CENTER);

		JLabel val2 = new JLabel("9");
		val2.setFont(new Font("Courier", Font.BOLD, 16));
		JLabel val3 = new JLabel("9");
		val3.setFont(new Font("Courier", Font.BOLD, 16));
		JLabel val4 = new JLabel("9");
		val4.setFont(new Font("Courier", Font.BOLD, 16));
		JLabel val5 = new JLabel("9");
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
	
		JLabel cards = new JLabel("  Cards:    ");
		cards.setFont(new Font("Courier", Font.PLAIN, 14));
		centerPan.add(cards);


		// RadioButtons disabled for test

		boolean test = true;
		if (test){
			cat1.setEnabled(false);
			cat2.setEnabled(false);
			cat3.setEnabled(false);
			cat4.setEnabled(false);
			cat5.setEnabled(false);
		 }
	}
	/**
	 * Bottom panel alternative layout
	 */
	public void bottomPanel(){


		//Bottom panel used for the player/user
		
		playButton = new JButton("Play!");
		playButton.addActionListener(this);
		JPanel bottomPan = new JPanel();
		JLabel communal = new JLabel("Communal pile:      ");
		this.add(bottomPan, "South");
		bottomPan.add(communal);
		bottomPan.add(playButton);
		bottomPan.setBackground(Color.cyan);

		// End of round result test
		JLabel result = new JLabel("YOU WIN!!");
		result.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));

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

	/**
	 * 
	 * @param e [description]
	 */
	public void actionPerformed(ActionEvent e){

		if (e.getSource()== playButton){
			//next go
			System.out.println("next go");
		}


	}


}