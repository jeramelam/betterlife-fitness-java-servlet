import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RescheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RescheduleServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a=request.getParameter("action");
		if (a.equals("Re-schedule")) {
    		String id=request.getParameter("id");
    		System.out.println("Re-schedule ID? "+id);
    		String sd=request.getParameter("startdate");
    		String ed=request.getParameter("enddate");        		
    		try {
    			if (sd != null && ed != null && sd != "" && ed != "") {
    				Date ns = new SimpleDateFormat("MMM-dd-yyyy h:mm:ss a").parse(sd);
    				Date ne = new SimpleDateFormat("MMM-dd-yyyy h:mm:ss a").parse(ed);
	        		Date today = new Date();
	        		Date startDate = ProgramDao.retrieveProgramStartDate(id);
	        		long now = today.getTime();
	        		long startTime = startDate.getTime();
	        		long diff = startTime - now;
	        		long days = diff / (1000 * 60 * 60 * 24);
	        		long newStart = ns.getTime();
	        		long newDiff = newStart - now;
	        		long newDays = newDiff / (1000 * 60 * 60 * 24);
	        		if (days >= 2 && newDays >= 2) {
	        			ProgramDao.rescheduleProgram(id,ns,ne);
	        			response.sendRedirect("reschedule.jsp?status=success");
	        		} else {
	        			request.setAttribute("error", "Can't be rescheduled.");
	        			RequestDispatcher rd=request.getRequestDispatcher("reschedule.jsp?pid="+id);
	        			rd.forward(request,response);
	        			return;
	        		}
    			} else {
        			request.setAttribute("error", "Must enter both start and end date.");
        			RequestDispatcher rd=request.getRequestDispatcher("reschedule.jsp?pid="+id);
        			rd.forward(request,response);
        			return;
        		}
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
