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
		
		String DBname = "m_16_0813273j";
		String username = "m_16_0813273j";
		String pass = "0813273j"; 

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
		String humanWinQuery = "SELECT COUNT (winner) FROM gameplayData WHERE winner = 'Player One'";
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
		String compWinQuery = "SELECT COUNT (winner) FROM gameplayData WHERE winner != 'Player One'";

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
		String largestRoundsQuery = "SELECT MAX (totRounds) FROM gameplayData";
		
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
	public boolean generateGameID() {
		
		gameID = gameCount() + 1;
		return true; 
	}

	//method to write game data to the DB
	public boolean writeGameplay(String input) {
		generateGameID(); 
		String queryAddGameplay = "INSERT INTO gameplayData VALUES(" + gameID + "," + input + ");"; 
		Statement stmt = null;
		try {
			stmt = connection.createStatement(); 
			int rs = stmt.executeUpdate(queryAddGameplay);
			return true; 
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to save results. Please contact support",
					"Error: Saving Failed", JOptionPane.ERROR_MESSAGE);
		}
		
		return false; 
	}	
	
	public boolean writeRoundPlay(String input) {
	
		String queryAddGameplay = "INSERT INTO roundswon VALUES(" + gameID + "," + input + ");"; 
		Statement stmt = null;
		try {
			stmt = connection.createStatement(); 
			int rs = stmt.executeUpdate(queryAddGameplay);
			return true; 

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to save results. Please contact support",
					"Error: Saving Failed", JOptionPane.ERROR_MESSAGE);
		}
		return false; 
	}

	//method to close the connection
	public boolean closeConnection() {
		try {
			connection.close();
			return true; 
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false; 
	}

}