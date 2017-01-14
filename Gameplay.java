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

	/**
	 * constructor for GameplayGUI
	 * [parameters can be added as needed]
	 */
	public Gameplay(){

		this.setSize(500, 300);
		this.setLocation(200, 200);
		this.setTitle("Top Trumps!");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);
		this.layoutComponents();

	}

	/**
	 * layout all the components
	 *  
	 */
	public void layoutComponents(){

		// This is all pretty much just placeholders for now
		
		JLabel welcome = new JLabel(" Welcome to Top Trumps!!");
		welcome.setFont(new Font("Comic Sans", Font.PLAIN, 24));


		pan = new JPanel();
		//pan.setBackground(Color.magenta);
		this.add(pan, "North");
		pan.add(welcome);

		play = new JButton("Play!");
		JPanel pan2 = new JPanel();
		this.add(pan2, "South");
		pan2.add(play);

		JPanel pan3 = new JPanel();
		pan3.setLayout(new BorderLayout());
		this.add(pan3, "Center");

		JPanel player1 = new JPanel();
		JPanel player2 = new JPanel();
		JPanel player3 = new JPanel();
		JPanel player4 = new JPanel();
		JPanel player5 = new JPanel();


		pan3.add(player1, BorderLayout.NORTH);
		pan3.add(player2, BorderLayout.CENTER);
		pan3.add(player3, BorderLayout.WEST);
		pan3.add(player4, BorderLayout.EAST);
		pan3.add(player5, BorderLayout.SOUTH);

		JLabel p1 = new JLabel("Player 1");
		player1.add(p1, "East");

		JLabel p2 = new JLabel("Our Card here");
		player2.add(p2);

		JLabel p3 = new JLabel("Player 3");
		player3.add(p3);

		JLabel p4 = new JLabel("Player 4");
		player4.add(p4);

		JLabel p5 = new JLabel("Player 5");
		player5.add(p5);


	}







}