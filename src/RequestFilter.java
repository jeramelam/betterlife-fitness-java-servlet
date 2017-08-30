import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/RequestFilter")
public class RequestFilter implements Filter {
    public RequestFilter() {
        // TODO Auto-generated constructor stub
    }
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
        String reqUrl = req.getRequestURI();
        System.out.println("requested Url------"+reqUrl);
        response.setContentType("text/html");
        HttpSession session=req.getSession();
        List<Program> programs = ProgramDao.retrieveProgram();
        req.setAttribute("programs", programs);
        List<User> uList = LoginDao.retrieveAllUser();
        String a=req.getParameter("action");
	    if (a != null && session.getAttribute("loggedIn") == null && a.equals("login")) {
	    	String n=req.getParameter("username");
	    	String p=req.getParameter("password");		
	    	if(LoginDao.validate(n, p)){
	    		session.setAttribute("loggedIn", true);
	    		User u = LoginDao.retrieveUser(n);
	    		session.setAttribute("uname", u.getFullName());
	    		session.setAttribute("utype", u.getUserType());
	    		session.setAttribute("loggedIn",true);
	    		session.setAttribute("uid", u.getUserId());
	    		req.setAttribute("uid", u.getUserId());
	    		req.setAttribute("uname", u.getFullName());
	    		req.setAttribute("utype", u.getUserType());
	    		req.setAttribute("loggedIn",true);
	    		System.out.println("login success");
	    	} else {
	    		req.setAttribute("loggedIn",false);
	    		req.setAttribute("msg","login failed");
	    		System.out.println(req.getAttribute("msg"));
	    	}
	    } else if (a != null && a.equals("logout")) {
	    	session.invalidate();
	        req.setAttribute("loggedIn",false);
	        req.setAttribute("msg","You have successfully logged out.");
			System.out.println(req.getAttribute("msg"));
			RequestDispatcher rd=req.getRequestDispatcher(req.getServletPath());
			rd.forward(req,res);
			return;
	    } else if (a != null && a.equals("addmember")) {
	    	String pid = req.getParameter("pid");
	    	req.setAttribute("pid", pid);
	    	req.setAttribute("list", uList);
	    } else if (session.getAttribute("loggedIn") != null) {
	    	req.setAttribute("uname", session.getAttribute("uname"));
			req.setAttribute("utype", session.getAttribute("utype"));
			req.setAttribute("uid", session.getAttribute("uid"));
			req.setAttribute("loggedIn",true);
	    }
	    if (session.getAttribute("uid") != null) {
	        for (int i=0;i < programs.size();i++) {
	        	String uid = session.getAttribute("uid").toString();
	        	int pid = programs.get(i).getProgramId();
	        	if (ProgramDao.checkBooking(uid, pid)) {
	        		programs.get(i).setBooked(true);
	        	}
	        	for (int j=0;j < programs.get(i).getMembers().size();j++) {
	        		System.out.println(programs.get(i).getProgramId()+"-----"+programs.get(i).getMembers().get(j).getFullName());
	        	}
	        }
	        req.setAttribute("programs", programs);
	    }
        chain.doFilter(request, response);
	}
    
	public void init(FilterConfig fConfig) throws ServletException {
		LoginDao.preload();
		ProgramDao.preload();
		PostingDao.preload();
	}

	public void destroy() {
		
	}
}
