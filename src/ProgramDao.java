import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ProgramDao {
	public static void preload() {
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();
		    st.execute("USE test");
		    st.execute("DROP TABLE IF EXISTS booking");
		    st.execute("DROP TABLE IF EXISTS review");
		    st.execute("DROP TABLE IF EXISTS program");
		    st.execute("CREATE TABLE program (" +
		            "id BIGINT NOT NULL AUTO_INCREMENT,"
		            + "pname VARCHAR(50),"
		            + "start_time datetime,"
		            + "end_time datetime,"
		            + "availability int,"
		            + "instructor varchar(30),"
		            + "status varchar(15),"
		            + "PRIMARY KEY(id)"
		            + ")");		    
		    st.execute("CREATE TABLE booking (" +
		            "id BIGINT NOT NULL AUTO_INCREMENT,"
		            + "uid BIGINT,"
		            + "pid BIGINT,"
		            + "PRIMARY KEY(id),"
		            + "FOREIGN KEY(uid) references login(id),"
		            + "FOREIGN KEY(pid) references program(id)"
		            + ")");
		    st.execute("INSERT INTO program (pname, start_time, end_time, availability, instructor) VALUES" + 
		            "('Basic Training I', '2015-05-01 10:30:00', '2015-05-01 11:30:00', '8', 'Mary Jane'),('Advanced Training I', '2015-03-27 12:00:00', '2015-03-27 13:00:00', '5', 'Mary Jane')," +
		            "('Basic Training II', '2015-03-24 10:30:00', '2015-03-24 11:30:00', '4', 'Mary Jane'),('Advanced Training II', '2015-03-25 13:30:00', '2015-03-25 14:30:00', '1', 'Mary Jane')");
		    System.out.println("working too");
		    st.close();
		    con.close();  
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	}
	
	public static List<Program> retrieveProgram() {
		List<Program> pList = new ArrayList<Program>();
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = con.prepareStatement("SELECT * FROM program");
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	Program p = new Program();
		    	p.setProgramId(res.getInt("id"));
		    	List<Review> rList = PostingDao.retrieveReviews(res.getInt("id"));
		    	p.setReviews(rList);
		    	p.setProgramName(res.getString("pname"));
		        p.setStartTime(res.getTimestamp("start_time"));
		        p.setEndTime(res.getTimestamp("end_time"));
		        p.setAvailability(res.getInt("availability"));
		        p.setInstructor(res.getString("instructor"));
		        p.setCancelled(res.getString("status")!=null?true:false);
		        List<Members> mList = ProgramDao.retrieveMembers(res.getInt("id"));
		        p.setMembers(mList);
		        pList.add(p);
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
		return pList;
	}
	
	public static List<Members> retrieveMembers(int id) {
		List<Members> mList = new ArrayList<Members>();
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = con.prepareStatement("SELECT l.* FROM booking b, login l where l.id = b.uid and b.pid = ?");
		    ps.setInt(1, id);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	Members m = new Members();
		    	m.setMemberId(res.getInt("id"));
		    	m.setFirstName(res.getString("fname"));
		    	m.setLastName(res.getString("lname"));
		    	m.setMemberType(res.getString("usertype"));
		        mList.add(m);
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
		return mList;
	}

	public static void updateProgram(String id, String action) {
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = null;
		    if (action == "book") {
		    	ps = con.prepareStatement("update program set availability = availability - 1 where id = ?");
		    } else if (action == "cancel") {
		    	ps = con.prepareStatement("update program set availability = availability + 1 where id = ?");
		    }
		    ps.setString(1, id);
		    ps.executeUpdate();
		    System.out.println("updated");
		    ps = con.prepareStatement("select * from program where id = " +id+"");
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
	    	System.out.println(res.getInt("id")+", "+res.getString("pname")+", "+res.getInt("availability"));
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
	}

	public static void book(String uid, String pid) {
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = null;
		    if (!ProgramDao.checkBooking(uid, Integer.parseInt(pid))) {
			    ps = con.prepareStatement("insert into booking(uid, pid) values (?,?)");
			    ps.setString(1, uid);
			    ps.setString(2, pid);
			    ps.executeUpdate();
			    ps = con.prepareStatement("select * from booking");
			    ResultSet res = ps.executeQuery();
			    while (res.next())
			    {
		    	System.out.println(res.getInt("id")+", "+res.getInt("uid")+", "+res.getInt("pid"));
			    }
			    res.close();
		    }
		    st.close();
		    ps.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	}

	public static void unbook(String uid, String pid) {
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = null;
		    ps = con.prepareStatement("delete from booking where uid = ? and pid = ?");
		    ps.setString(1, uid);
		    ps.setString(2, pid);
		    ps.executeUpdate();
		    ps = con.prepareStatement("select * from booking");
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
	    	System.out.println(res.getInt("id")+", "+res.getInt("uid")+", "+res.getInt("pid"));
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
	}

	public static boolean checkBooking(String uid, int pid) {
		boolean status = false;
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = null;
		    ps = con.prepareStatement("select 1 from booking where uid = ? and pid = ?");
		    ps.setString(1, uid);
		    ps.setInt(2, pid);
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

	public static boolean checkAvailability(String pid) {
		boolean status = false;
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = null;
		    ps = con.prepareStatement("select availability from program where id = ?");
		    ps.setString(1, pid);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
	    		if (res.getInt("availability") > 0){
	    			status = true;
	    		}
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

	public static void cancelProgram(String id) {
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = null;
	    	ps = con.prepareStatement("update program set status = 'cancelled' where id = ?");
		    ps.setString(1, id);
		    ps.executeUpdate();
		    ps = con.prepareStatement("select * from program");
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
	    	System.out.println(res.getInt("id")+", "+res.getString("status")+", "+res.getString("pname"));
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
	}

	public static Date retrieveProgramStartDate(String id) {
		Date date = null;
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = null;
	    	ps = con.prepareStatement("select start_time from program where id = ?");
		    ps.setString(1, id);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	date = res.getTimestamp("start_time");
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
		return date;
	}

	public static void rescheduleProgram(String id, Date ns, Date ne) {
		String nst = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ns);
		String net = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ne);
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = null;
	    	ps = con.prepareStatement("update program set start_time = ?, end_time = ? where id = ?");
		    ps.setString(1, nst);
		    ps.setString(2, net);
		    ps.setString(3, id);
		    ps.executeUpdate();
		    ps = con.prepareStatement("select * from program where id = ?");
		    ps.setString(1, id);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	System.out.println(res.getInt("id")+", "+res.getTimestamp("start_time")+", "+res.getTimestamp("end_time"));
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
	}

	public static String retrieveProgramName(int programId) {
		String pname = "";
		try
		{
			Connection con = ConnectionDao.getConnection();
			Statement st = con.createStatement();
			st.execute("USE test");
			PreparedStatement ps = con.prepareStatement("SELECT pname FROM program where id = ?");
			ps.setInt(1, programId);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	pname = res.getString("pname");
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
		return pname;
	}
}
