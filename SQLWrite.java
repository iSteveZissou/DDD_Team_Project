import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class SQLWrite {
	
	private Connection connection = null;
	private int gameID; 
	private boolean connectionState; 
	
		
	public  SQLWrite(String gameData) throws SQLException{
		
		DBConnect(); //connect to the database 
		generateGameID(); 
		writeToSQL(gameData); 
		closeConnection(); 
		
	}
			
	public void DBConnect() {
		String DBname = "m_16_2246923g";
		String username = "m_16_2246923g";
		String pass = "2246923g"; 

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + DBname, username,	pass);
		}

		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to connect to database. Please contact support",
					"Error: Connection Failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void generateGameID (){
		 	gameID = getGameNumber() + 1; 
	}
	
	
	public int getGameNumber(){
		int count = 0; 
		Statement stmt = null;
		String query = "SELECT count(*) FROM gameplayData" ; 
		
		try {
			stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery(query);
			
			//get a number of courses present in the database 
			while (rs.next()) {
				count = rs.getInt("count"); 
			}					 						
		}	
		 catch (SQLException e) {
			 e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to retrieve results. Please contact support",
						"Error: Data Retrieval", JOptionPane.ERROR_MESSAGE);
	
		}
			
		return count; 
	}
	
	public void writeToSQL(String input){
		
		String queryAddGameplay = "INSERT INTO gameplayData VALUES(" + gameID + "," + input + ");"; 
		Statement stmt = null;	
		//execute booking query

		try {
			stmt = connection.createStatement(); 
			int rs = stmt.executeUpdate(queryAddGameplay);
			
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to save results. Please contact support",
						"Error: Saving Failed", JOptionPane.ERROR_MESSAGE);
		}		
		
	}
	
	public void closeConnection() {
		try {
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
