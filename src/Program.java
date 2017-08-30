import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class Program {
	String pname = "";
	int space = 0;
	String instructor = "";
	int id = 0;
	boolean booked = false;
	boolean cancelled = false;
	Date st = null;
	Date et = null;
	String time = "";
	List<Members> mList = new ArrayList<Members>();
	List<Review> rList = new ArrayList<Review>();;
    
	public void setProgramName(String pname) {
		this.pname = pname;
	}
	
	public String getProgramName() {
		return this.pname;
	}

	public void setStartTime(Date st) {
		this.st = st;
	}
	
	public String getStartTime() {
		return new SimpleDateFormat("E (MMMdd) h:mma").format(this.st);
	}
	
	public void setEndTime(Date et) {
		this.et = et;
	}
	
	public String getEndTime() {
		return new SimpleDateFormat("E (MMMdd) h:mma").format(this.et);
	}
	
	public String getTime(){
		return getStartTime()+" - "+getEndTime().substring(12);
	}

	public void setAvailability(int space) {
		this.space = space;
	}
	
	public int getAvailability() {
		return this.space;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public String getInstructor() {
		return this.instructor;
	}

	public void setProgramId(int id) {
		this.id = id;
	}
	
	public int getProgramId() {
		return this.id;
	}
	
	public void setBooked(boolean booked) {
		this.booked = booked;
	}
	
	public boolean isBooked() {
		return this.booked;
	}
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public boolean isCancelled() {
		return this.cancelled;
	}

	public void setMembers(List<Members> mList) {
		this.mList = mList;
	}
	
	public List<Members> getMembers() {
		return this.mList;
	}

	public void setReviews(List<Review> rList) {
		this.rList = rList;
	}
	
	public List<Review> getReviews() {
		return this.rList;
	}
}
