import java.awt.*;
import javax.swing.*;

/**
 * GUI to display the gameplay interface
 * 
 */

public class Gameplay extends JFrame{

	/** Instance variables */
	private JPanel pan;

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

		JLabel welcome = new JLabel(" Welcome to Top Trumps!!");
		welcome.setFont(new Font("Comic Sans", Font.PLAIN, 24));


		pan = new JPanel();
		//pan.setBackground(Color.magenta);
		this.add(pan, "North");
		pan.add(welcome);

	}







}