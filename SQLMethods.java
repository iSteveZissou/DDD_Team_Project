import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * SQL class containing all methods to
 * read and write to the database.
 * Methods can then be called in the report
 * class.
 * @author amy
 *
 */

public class SQLMethods {
	private int gameID;
	private Connection connection = null;

	public SQLMethods() {
		
		String DBname = "m_16_2246923g";
		String username = "m_16_2246923g";
		String pass = "2246923g"; 

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + DBname, username,
					pass);
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//method to count the number of games stored
	public int gameCount() {

		int gameCount = 0;
		Statement sizeStmt = null;
		String gameCountQuery = "SELECT COUNT(*) from gameplayData";

		try{	
		sizeStmt = connection.createStatement();
		ResultSet sizeRS = sizeStmt.executeQuery(gameCountQuery);

		sizeRS.next(); 
		gameCount = sizeRS.getInt("count");
		} catch (SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error executing query.",
					"Error: Query failed.", JOptionPane.ERROR_MESSAGE);
		}
		
		return gameCount; 
	}

	//method to count the number of P1/human wins
	public int humanWinner() {
		int humanWin = 0;
		Statement humanWinnerStmt = null;
		String humanWinQuery = "SELECT COUNT (winner) FROM gameplaydata WHERE winner = 'Player One'";
		try {
		humanWinnerStmt = connection.createStatement();
		ResultSet humanRS = humanWinnerStmt.executeQuery(humanWinQuery);

		humanRS.next();
		humanWin = humanRS.getInt("count");
		} catch (SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error executing query.",
					"Error: Query failed.", JOptionPane.ERROR_MESSAGE);
		}
		return humanWin;
	}

	//method to get the average number of draws
	public int avgDraws() {
		int avgDraws = 0;
		Statement drawsStmt = null;
		String avgDrawsQuery = "SELECT AVG (totDraws) AS AvgDraws FROM gameplayData";
		try{
		drawsStmt = connection.createStatement();
		ResultSet drawsRS = drawsStmt.executeQuery(avgDrawsQuery);

		drawsRS.next();
		avgDraws = drawsRS.getInt("avgdraws");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error executing query.",
					"Error: Query failed.", JOptionPane.ERROR_MESSAGE);
		}
		return avgDraws;
	}

	//method to get the number of computer wins
	public int compWin(){
		int computerWins = 0;
		Statement compStmt = null;
		String compWinQuery = "SELECT COUNT (winner) FROM gameplaydata WHERE winner != 'Player One'";

		try{
		compStmt = connection.createStatement();
		ResultSet compWinRS = compStmt.executeQuery(compWinQuery);

		compWinRS.next();
		computerWins = compWinRS.getInt("count");
		} catch (SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error executing query.",
					"Error: Query failed.", JOptionPane.ERROR_MESSAGE);
		}
		return computerWins;
	}

	//method to get the largest number of rounds played in a game
	public int largestRounds()  {
		int largestRounds = 0;
		Statement roundsStmt = null;
		String largestRoundsQuery = "SELECT MAX (totRounds) FROM gameplaydata";
		
		try{
		roundsStmt = connection.createStatement();
		ResultSet roundsRS = roundsStmt.executeQuery(largestRoundsQuery);

		roundsRS.next();
		largestRounds = roundsRS.getInt("max");
		} catch (SQLException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error executing query.",
					"Error: Query failed.", JOptionPane.ERROR_MESSAGE);
		}
		return largestRounds;
	}

	//method to generate a new gameID
	public void generateGameID() {
		
		gameID = gameCount() + 1; 
	}

	//method to write game data to the DB
	public void writeGameplay(String input) {
		generateGameID(); 
		String queryAddGameplay = "INSERT INTO gameplayData VALUES(" + gameID + "," + input + ");"; 
		Statement stmt = null;
		try {
			stmt = connection.createStatement(); 
			int rs = stmt.executeUpdate(queryAddGameplay);

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to save results. Please contact support",
					"Error: Saving Failed", JOptionPane.ERROR_MESSAGE);
		}

	}	
	
	public void writeRoundPlay(String input) {
	
		String queryAddGameplay = "INSERT INTO roundswon VALUES(" + gameID + "," + input + ");"; 
		Statement stmt = null;
		try {
			stmt = connection.createStatement(); 
			int rs = stmt.executeUpdate(queryAddGameplay);

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to save results. Please contact support",
					"Error: Saving Failed", JOptionPane.ERROR_MESSAGE);
		}

	}

	//method to close the connection
	public void closeConnection() {
		try {
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}