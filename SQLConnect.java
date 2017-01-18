import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnect {
	private Connection connection = null;
	

	public void DBConnect() {
				
		String DBname = "m_16_2246923g";
		String username = "m_16_2246923g";
		String pass = "2246923g"; 

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + DBname, username,	pass);
		}

		catch (SQLException e) {
			e.printStackTrace();
			return;
		}

	}
}
