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
	public int gameCount() throws SQLException {

		int gameCount;
		Statement sizeStmt = null;
		String gameCountQuery = "SELECT COUNT(*) from gameplayData";

		sizeStmt = connection.createStatement();
		ResultSet sizeRS = sizeStmt.executeQuery(gameCountQuery);

		sizeRS.next(); 
		gameCount = sizeRS.getInt("count");

		return gameCount; 
	}

	//method to count the number of P1/human wins
	public int humanWinner() throws SQLException {
		int humanWin;
		Statement humanWinnerStmt = null;
		String humanWinQuery = "SELECT COUNT (winner) FROM gameplaydata WHERE winner = 'Player One'";

		humanWinnerStmt = connection.createStatement();
		ResultSet humanRS = humanWinnerStmt.executeQuery(humanWinQuery);

		humanRS.next();
		humanWin = humanRS.getInt("count");

		return humanWin;
	}

	//method to get the average number of draws
	public int avgDraws() throws SQLException {
		int avgDraws;
		Statement drawsStmt = null;
		String avgDrawsQuery = "SELECT AVG (totDraws) AS AvgDraws FROM gameplayData";

		drawsStmt = connection.createStatement();
		ResultSet drawsRS = drawsStmt.executeQuery(avgDrawsQuery);

		drawsRS.next();
		avgDraws = drawsRS.getInt("avgdraws");

		return avgDraws;
	}

	//method to get the number of computer wins
	public int compWin() throws SQLException {
		int computerWins;
		Statement compStmt = null;
		String compWinQuery = "SELECT COUNT (winner) FROM gameplaydata WHERE winner != 'Player One'";

		compStmt = connection.createStatement();
		ResultSet compWinRS = compStmt.executeQuery(compWinQuery);

		compWinRS.next();
		computerWins = compWinRS.getInt("count");

		return computerWins;
	}

	//method to get the largest number of rounds played in a game
	public int largestRounds() throws SQLException {
		int largestRounds;
		Statement roundsStmt = null;
		String largestRoundsQuery = "SELECT MAX (totRounds) FROM gameplaydata";

		roundsStmt = connection.createStatement();
		ResultSet roundsRS = roundsStmt.executeQuery(largestRoundsQuery);

		roundsRS.next();
		largestRounds = roundsRS.getInt("max");

		return largestRounds;
	}

	//method to generate a new gameID
	public void generateGameID() throws SQLException {
		int gameID = gameCount() + 1; 
	}

	//method to write game data to the DB
	public void writeToSQL(int gameID, String input) {

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