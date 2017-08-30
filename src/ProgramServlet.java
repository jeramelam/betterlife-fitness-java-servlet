import java.io.IOException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProgramServlet")
public class ProgramServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProgramServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a=request.getParameter("action");
        String uid=request.getAttribute("uid").toString();
        if (a != null) {
        	System.out.println("Action? "+a);
        	if (a.equals("Book")) {
        		String id=request.getParameter("id");
        		System.out.println("Book ID? "+id + " User ID? "+uid);
        		if (ProgramDao.checkAvailability(id)) {
	        		ProgramDao.updateProgram(id,"book");
	        		ProgramDao.book(uid, id);
        		}
        	}
        	if (a.equals("Unbook")) {
        		String id=request.getParameter("id");
        		System.out.println("Unbook ID? "+id);
        		ProgramDao.updateProgram(id,"cancel");
        		ProgramDao.unbook(uid, id);
        	}
        	if (a.equals("Cancel")) {
        		String id=request.getParameter("id");
        		System.out.println("Cancel ID? "+id);
        		Date today = new Date();
        		Date startDate = ProgramDao.retrieveProgramStartDate(id);
        		long now = today.getTime();
        		long startTime = startDate.getTime();
        		long diff = startTime - now;
        		long days = diff / (1000 * 60 * 60 * 24);
        		if (days >=2) {
        			ProgramDao.cancelProgram(id);
        		} else {
        			request.setAttribute("cancelmsg", "Cannot be cancelled.");
        			RequestDispatcher rd=request.getRequestDispatcher("program.jsp");
        			rd.forward(request,response);
        			return;
        		}
        	}
        }
    	response.sendRedirect("/4020G3A2/program.jsp");
	}
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
