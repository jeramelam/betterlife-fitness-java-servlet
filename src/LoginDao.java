import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {
	public static void preload() {
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("CREATE DATABASE IF NOT EXISTS test");
		    st.execute("USE test");
		    st.execute("DROP TABLE IF EXISTS booking");
		    st.execute("DROP TABLE IF EXISTS review");
		    st.execute("DROP TABLE IF EXISTS login");
		    st.execute("CREATE TABLE login (" +
		            "id BIGINT NOT NULL AUTO_INCREMENT,"
		            + "fname VARCHAR(25),"
		            + "lname VARCHAR(25),"
		            + "username VARCHAR(25),"
		            + "password VARCHAR(25),"
		            + "usertype VARCHAR(25),"
		            + "PRIMARY KEY(id)"
		            + ")");
		    st.execute("INSERT INTO login (fname, lname, username, password, usertype) VALUES" + 
		            "('Joe', 'Cole', 'jcole', '', 'member'), ('James', 'Jones', 'jjones', '', 'member'), ('Mary', 'Jane', 'mjane', '', 'instructor'), ('Jay', 'Chow', 'jchow', '', 'staff'),('My', 'Admin', 'admin', '', 'admin')");
		    System.out.println("working");
		    st.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	}
	public static boolean validate(String name,String pwd){
		boolean status = false;
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = con.prepareStatement("SELECT * FROM login WHERE username = ? and password = ?");
		    ps.setString(1, name);
		    ps.setString(2, pwd);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	status = true;
		    }
		    res.close();
		    st.close();
		    ps.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return status;
	}
	
	public static User retrieveUser(String uname) {
		User u = new User();
		try
		{
			Connection con = ConnectionDao.getConnection();
			Statement st = con.createStatement();
			st.execute("USE test");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM login WHERE username = ?");
		    ps.setString(1, uname);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	u.setUserId(res.getInt("id"));
		    	u.setFirstName(res.getString("fname"));
		        u.setLastName(res.getString("lname"));
		        u.setUserName(res.getString("username"));
		        u.setUserType(res.getString("usertype"));
		    	System.out.println(res.getInt("id") + " " + res.getString("username") + " " + res.getString("usertype"));
		    }
		    res.close();
		    ps.close();
		    st.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return u;
	}
	public static List<User> retrieveAllUser() {
		List<User> uList = new ArrayList<User>();
		try
		{
			Connection con = ConnectionDao.getConnection();
			Statement st = con.createStatement();
			st.execute("USE test");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM login");
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	User u = new User();
		    	u.setUserId(res.getInt("id"));
		    	u.setFirstName(res.getString("fname"));
		        u.setLastName(res.getString("lname"));
		        u.setUserName(res.getString("username"));
		        u.setUserType(res.getString("usertype"));
		    	uList.add(u);
		    }
		    res.close();
		    ps.close();
		    st.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return uList;
	}
	public static String retrieveFirstName(int userId) {
		String fname = "";
		try
		{
			Connection con = ConnectionDao.getConnection();
			Statement st = con.createStatement();
			st.execute("USE test");
			PreparedStatement ps = con.prepareStatement("SELECT fname FROM login where id = ?");
			ps.setInt(1, userId);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	fname = res.getString("fname");
		    }
		    res.close();
		    ps.close();
		    st.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return fname;
	}

	public static String retrieveLastName(int userId) {
		String lname = "";
		try
		{
			Connection con = ConnectionDao.getConnection();
			Statement st = con.createStatement();
			st.execute("USE test");
			PreparedStatement ps = con.prepareStatement("SELECT lname FROM login where id = ?");
			ps.setInt(1, userId);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	lname = res.getString("lname");
		    }
		    res.close();
		    ps.close();
		    st.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return lname;
	}
	public static boolean checkUserExists(String u) {
		boolean hasUser = false;
		try
		{
			Connection con = ConnectionDao.getConnection();
			Statement st = con.createStatement();
			st.execute("USE test");
			PreparedStatement ps = con.prepareStatement("SELECT 1 FROM login where username = ?");
			ps.setString(1, u);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	hasUser = true;
		    }
		    res.close();
		    ps.close();
		    st.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		return hasUser;
	}
	public static void registerUser(String fn, String ln, String u, String p) {
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = null;
			ps = con.prepareStatement("insert into login(fname, lname, username, password, usertype) values (?,?,?,?,'member')");
			ps.setString(1, fn);
			ps.setString(2, ln);
			ps.setString(3, u);
			ps.setString(4, p);
			ps.executeUpdate();
		    st.close();
		    ps.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}		
	}
}
