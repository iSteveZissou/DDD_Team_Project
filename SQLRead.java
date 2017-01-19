import java.sql.*;

/**
 * Class to read from the gameplayData
 * and roundsWon DB tables, for use in
 * the Report class.
 *
 */

public class SQLRead {

	private Connection connection = null;

	public SQLRead(){

		String DBname = "m_16_2246923g";
		String username = "m_16_2246923g";
		String pass = "2246923g"; 

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + DBname, username,	pass);
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//method to count the number of rows in the DB table
	public int SQLRowCount() throws SQLException {

		int dbCount;
		Statement sizeStmt = null;
		String dbSizeCountQuery = "SELECT COUNT(*) from gameplayData";

		sizeStmt = connection.createStatement();
		ResultSet sizeRS = sizeStmt.executeQuery(dbSizeCountQuery);

		sizeRS.next(); 
		dbCount = sizeRS.getInt("count");

		return dbCount; 
	}

	//method to read the contents of the DB tables, to be called in the report class.
	public SQLReadReportRow[] ReadDB() throws SQLException {

		Statement readStmt = null;
		String readQuery = "SELECT * FROM gameplayData INNER JOIN roundsWon ON gameplayData.gameID = roundsWon.gameID";

		readStmt = connection.createStatement();
		ResultSet readRS = readStmt.executeQuery(readQuery);

		SQLReadReportRow[] gamestats = new SQLReadReportRow[SQLRowCount()];

		int counter = 0; //counts what row in the database we're on

		while (readRS.next()) {
			SQLReadReportRow row = new SQLReadReportRow();

			//gets the game stats from the table
			row.gameID = readRS.getInt("gameID");
			row.totalDraws = readRS.getInt("totDraws");
			row.winner = readRS.getString("winner");
			row.totalRounds = readRS.getInt("totRounds");
			row.totalHumanRoundWins = readRS.getInt("p1roundswon");
			row.totalCompRoundWins = readRS.getInt("p2roundswon")+readRS.getInt("p3roundswon")+readRS.getInt("p4roundswon")+readRS.getInt("p5roundswon");

			//populates the gamestats array with row data
			gamestats[counter] = row;
			counter++; //increments counter to move to next row
		}
		
		return gamestats;

	}

}
