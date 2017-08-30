import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RegisterServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String fn=request.getParameter("fname");
    	String ln=request.getParameter("lname");
		String u=request.getParameter("uname");
    	String p=request.getParameter("pwd");
    	if (!LoginDao.checkUserExists(u) && fn != "" && ln != "" && u != null && p != null) {
    		LoginDao.registerUser(fn,ln,u,p);
    		request.setAttribute("msg","successfully registered.");
    		RequestDispatcher rd=request.getRequestDispatcher("ITEC4020-A2.jsp");
			rd.forward(request,response);
			return;
    	} else {
    		request.setAttribute("regerror","not registered.");
    		RequestDispatcher rd=request.getRequestDispatcher("registration.jsp");
			rd.forward(request,response);
			return;
    	}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
