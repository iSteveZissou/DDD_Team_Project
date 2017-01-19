import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class created to bundle row 
 * data from the database, to be 
 * used in the SQLRead class.    
 * @author amy
 */

public class SQLReadReportRow {
	
	public int gameID;
	public int totalDraws;
	public String winner;
	public int totalRounds;
	public int totalHumanRoundWins;
	public int totalCompRoundWins;

}
