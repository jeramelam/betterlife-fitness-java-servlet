public class Review {
	int rid = 0;
	int pid = 0;
	int uid = 0;
	int count = 0;
	String review = "";
	String desc = "";
	String fname = "";
	String lname = "";
	String name = "";
	String pname = "";
	boolean a = false;
	boolean b = false;
	boolean c = false;

	public void setReviewId(int rid) {
		this.rid = rid;
	}
	
	public int getReviewId() {
		return this.rid;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	public String getReview() {
		return this.review;
	}

	public void setProgramId(int pid) {
		this.pid = pid;
	}
	
	public int getProgramId() {
		return this.pid;
	}
	
	public void setUserId(int uid) {
		this.uid = uid;
	}

	public int getUserId() {
		return this.uid;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return this.desc;
	}

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

	public String getFullName() {
		this.name = getFirstName()+" "+getLastName();
		return this.name.trim();
	}
	
	public void setProgramName(String pname) {
		this.pname = pname;
	}
	
	public String getProgramName() {
		return this.pname;
	}

	public void setFirstReview(boolean b) {
		this.b = b;
	}
	
	public boolean isFirstReview() {
		return this.b;
	}

	public void setDetail(boolean a) {
		this.a = a;
	}
	
	public boolean getDetail() {
		return this.a;
	}

	public void setReply(boolean c) {
		this.c = c;
	}
	
	public boolean isReply() {
		return this.c;
	}

	public void setTotalCount(int count) {
		this.count = count;
	}
	
	public int getTotalCount() {
		return this.count;
	}
}
