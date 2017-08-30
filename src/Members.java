public class Members {
	String fname = "";
	String lname = "";
	String mtype = "";
	String name = "";
	int mid = 0;
	
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
	
	public void setMemberType(String mtype) {
		this.mtype = mtype;
	}

	public String getMemberType() {
		return this.mtype;
	}
	
	public String getFullName() {
		this.name = getFirstName()+" "+getLastName();
		return this.name.trim();
	}

	public void setMemberId(int mid) {
		this.mid  = mid;
	}
	
	public int getMemberId() {
		return this.mid;
	}	
}
