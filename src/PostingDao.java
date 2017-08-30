import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostingDao {
	public static void preload() {
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("CREATE DATABASE IF NOT EXISTS test");
		    st.execute("USE test");
		    st.execute("DROP TABLE IF EXISTS review");
		    st.execute("CREATE TABLE review (" 
		    		+ "id BIGINT NOT NULL AUTO_INCREMENT,"
		            + "review varchar(1000),"
		    		+ "description varchar(100),"
		    		+ "uid BIGINT,"
		            + "pid BIGINT,"
		    		+ "name varchar(100),"
		            + "PRIMARY KEY(id),"
		            + "FOREIGN KEY(uid) references login(id),"
		            + "FOREIGN KEY(pid) references program(id)"
		            + ")");
		    st.execute("INSERT INTO review (review,description,pid,name) VALUES "
		            + "('It will re-define your body and empower you to achieve your goals.<br> "
		    		+ "Burns an average of 420 calories in a normal 50-minute class. Enhance your workout with the use of optional hand weights.<br> "
		    		+ "This fitness class was created by our very own Maureen Hagan (2006 IDEA Fitness Instructor of the Year) and is available exclusively at GoodLife Fitness.<br>',"
		    		+ "'a','3',''),"
		    		+ "('I have been taking this class for a 3 months, thumbs up!','b','3','Jeramy'),"
		    		+ "('Cannot agree more.','c','3','James'),"
		    		+ "('It will re-define your body and empower you to achieve your goals.<br> "
		    		+ "Burns an average of 420 calories in a normal 50-minute class. Enhance your workout with the use of optional hand weights.<br> "
		    		+ "This fitness class was created by our very own Maureen Hagan (2006 IDEA Fitness Instructor of the Year) and is available exclusively at GoodLife Fitness.<br>',"
		    		+ "'a','1',''),"
		    		+ "('This fiercely energetic program is inspired by martial arts and draws from a wide array of disciplines such as karate, boxing, taekwondo, tai chi and muay thai.<br> "
		    		+ "Supported by driving music and powerful role model instructors, you strike, punch, kick and kata your way through calories to superior cardio fitness. Like all the LES MILLSª programs, a new BODYCOMBATª class is produced every three months with new music and choreography.<br>', "
		    		+ "'a','2',''),"
		    		+ "('This fiercely energetic program is inspired by martial arts and draws from a wide array of disciplines such as karate, boxing, taekwondo, tai chi and muay thai.<br> "
		    		+ "Supported by driving music and powerful role model instructors, you strike, punch, kick and kata your way through calories to superior cardio fitness. Like all the LES MILLSª programs, a new BODYCOMBATª class is produced every three months with new music and choreography.<br>', "
		    		+ "'a','4',''),"
		    		+ "('This class is mad fun WOOT~','b','2','Taige')"
		    		);
		    System.out.println("working as well");
		    st.close();
		    con.close();            
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
	}

	public static List<Review> retrieveReviews(int pid) {
		List<Review> rList = new ArrayList<Review>();
		try
		{
			Connection con = ConnectionDao.getConnection();
			Statement st = con.createStatement();
			st.execute("USE test");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM review where pid = ? order by description asc");
			ps.setInt(1, pid);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	Review r = new Review();
		    	r.setReviewId(res.getInt("id"));
		    	r.setReview(res.getString("review"));
		    	r.setDesc(res.getString("description"));
		    	if (r.getDesc().equals("a")) {
		    		r.setDetail(true);
		    	} else if (r.getDesc().equals("b")) {
		    		r.setFirstReview(true);
		    	} else if (r.getDesc().equals("c")) {
		    		r.setReply(true);
		    	}
		    	if (res.getInt("uid")!=0) {
		    		r.setUserId(res.getInt("uid"));
		    		String fname = LoginDao.retrieveFirstName(r.getUserId());
		    		String lname = LoginDao.retrieveLastName(r.getUserId());
		    		r.setFirstName(fname);
		    		r.setLastName(lname);
		    	} else {
		    		r.setFirstName(res.getString("name"));
		    	}
		    	r.setProgramId(res.getInt("pid"));
		    	String pname = ProgramDao.retrieveProgramName(r.getProgramId());
		    	r.setProgramName(pname);
		    	int count = PostingDao.retrievePidCount(r.getProgramId());
		    	r.setTotalCount(count);
		    	rList.add(r);
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
		return rList;
	}

	public static int retrievePidCount(int pid) {
		int count = 0;
		try
		{
			Connection con = ConnectionDao.getConnection();
			Statement st = con.createStatement();
			st.execute("USE test");
			PreparedStatement ps = con.prepareStatement("SELECT count(*) as totalCount FROM review where pid = ? group by pid");
			ps.setInt(1, pid);
		    ResultSet res = ps.executeQuery();
		    while (res.next())
		    {
		    	count = res.getInt("totalCount");
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
		return count;
	}

	public static void createReview(String post, String desc, String uid, String pid) {
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = null;
			ps = con.prepareStatement("insert into review(review, description, uid, pid) values (?,?,?,?)");
			ps.setString(1, post);
			ps.setString(2, desc);
			ps.setString(3, uid);
			ps.setString(4, pid);
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

	public static void deleteReview(String rid, String pid) {
		boolean hasB = false;
		boolean hasC = false;
		try
		{
			Connection con = ConnectionDao.getConnection();
		    Statement st = con.createStatement();  
		    st.execute("USE test");
		    PreparedStatement ps = null;
			ps = con.prepareStatement("delete from review where id = ?");
			ps.setString(1, rid);
			ps.executeUpdate();
			ps = con.prepareStatement("select 1 from review where pid = ? and description = 'b'");
			ps.setString(1,pid);
			ResultSet res = ps.executeQuery();
			while (res.next())
		    {
		    	hasB = true;
		    }
			ps = con.prepareStatement("select 1 from review where pid = ? and description = 'c'");
			ps.setString(1,pid);
			res = ps.executeQuery();
			while (res.next())
		    {
		    	hasC = true;
		    }
			if (!hasB && hasC) {
			ps = con.prepareStatement("update review set description = 'b' "
					+ "where pid = ? and description = 'c' Limit 1");
			ps.setString(1,pid);
			ps.executeUpdate();
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
}
