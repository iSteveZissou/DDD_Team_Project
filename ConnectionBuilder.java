import java.io.*;
import java.sql.*;

/**
 * Connects to the database to be
 * used in EndReport and Report classes.
 */

/*
 * Method to connect to the database
 */

public class ConnectionBuilder {

	public static Connection createConnection() throws SQLException {

		//Database name
		String dbName = "m_16_0813273j";
		//Database user id and password
		String username = "m_16_0813273j";
		String password = "0813273j";

		Connection c = DriverManager.getConnection("jdbc:postegresql://yacata.dcs.gla.ac.uk:5432/"+ dbName, username, password);
		
		return c;
	}
}