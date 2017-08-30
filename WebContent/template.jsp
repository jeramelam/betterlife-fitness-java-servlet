<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
          "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="style.css" />
<script>
var loc = "<%= request.getServletPath() %>";
if (loc == "/program.jsp"||loc == "/detail.jsp"||loc == "/contact.jsp" || loc == "/registration.jsp"){
	window.location.hash = "sidebar";
	
}
</script>
<title>${param.title}</title>
</head>
<%
   response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
   response.addHeader("Pragma", "no-cache"); 
   response.addDateHeader ("Expires", 0);
   %>
<body>
<div id="bg1"></div>
<div id="bg2"></div>
<div id="outer">
<jsp:include page="header.jsp" />
<div id="main">
	<div id="sidebar">
		<jsp:include page="sidebar.jsp" />
		<c:choose>
            <c:when test="${loggedIn}">
            <jsp:include page="welcome.jsp" />
			</c:when>
			<c:otherwise>
			<jsp:include page="login.jsp" />
			</c:otherwise>
		</c:choose>
	</div>	
	<jsp:include page="${param.content}.jsp" />
</div>
</div>
<div id="copyright">
	ITEC 4020 Group 3 Assignment 2<br/> Prepared by Jerame, James, Zeke and Tiger
</div>
</body>
</html>