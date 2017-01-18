import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLWrite {
	
	private Connection connection = null;
	
	public  SQLWrite(){
	
		DBConnect();
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
		}

	}
}
