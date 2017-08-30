import java.sql.*;

public class ConnectionDao {
	public static Connection getConnection(){
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String password = "";
		Connection con = null;
		try
		{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    con = DriverManager.getConnection(url, user, password);
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return con;
	}
}
