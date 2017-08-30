import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ReviewServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a=request.getParameter("action");
        if (a != null) {
        	System.out.println("Action? "+a);
        	if (a.equals("Post")) {
        		String uid=request.getParameter("uid");
        		String pid=request.getParameter("pid");
        		String post=request.getParameter("Post");
        		PostingDao.createReview(post,"b",uid,pid);
        	}
        	if (a.equals("Reply")) {
        		String uid=request.getParameter("uid");
        		String pid=request.getParameter("pid");
        		String reply=request.getParameter("Reply");
        		PostingDao.createReview(reply,"c",uid,pid);
        	}
        	if (a.equals("Delete")) {
        		String rid=request.getParameter("rid");
        		String pid=request.getParameter("pid");
        		PostingDao.deleteReview(rid,pid);
        	}
        }
		response.sendRedirect("/4020G3A2/detail.jsp");
	}
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
