import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a=request.getParameter("action");
        if (a != null) {
        	System.out.println("Action? "+a);
        	if (a.equals("Delete")) {
        		String mid=request.getParameter("mid");
        		String pid=request.getParameter("pid");
        		System.out.println("delete member "+mid+" from program "+pid);
        		ProgramDao.updateProgram(pid,"cancel");
        		ProgramDao.unbook(mid, pid);
            	response.sendRedirect("/4020G3A2/member.jsp?status=success");
        	}
        	if (a.equals("Add")) {
        		String mid=request.getParameter("mid");
        		String pid=request.getParameter("pid");
        		System.out.println("add member "+mid+" to program "+pid);
        		if (ProgramDao.checkAvailability(pid)) {
	        		ProgramDao.updateProgram(pid,"book");
	        		ProgramDao.book(mid, pid);
        		}
        		response.sendRedirect("/4020G3A2/add.jsp?status=success");
        	}
        }
	}
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
