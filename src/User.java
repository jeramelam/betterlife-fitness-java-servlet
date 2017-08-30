
public class User {
	String fname = "";
	String lname = "";
	String uname = "";
	String utype = "";
	String name = "";
	boolean loggedIn = false;
	int uid = 0;
	
	public void setFirstName(String fname) {
		this.fname = fname;
	}
	
	public String getFirstName() {
		return this.fname;
	}
	
	public void setLastName(String lname) {
		this.lname = lname;
	}

	public String getLastName() {
		return this.lname;
	}
	
	public void setUserName(String uname) {
		this.uname = uname;
	}

	public String getUserName() {
		return this.uname;
	}
	
	public void setUserType(String utype) {
		this.utype = utype;
	}

	public String getUserType() {
		return this.utype;
	}
	
	public String getFullName() {
		this.name = getFirstName()+" "+getLastName();
		return this.name.trim();
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public boolean isLoggedIn(){
		return this.loggedIn;
		
	}

	public void setUserId(int uid) {
		this.uid  = uid;
	}
	
	public int getUserId() {
		return this.uid;
	}	
}
