<div id="welcome">
<%@ page import ="java.sql.*" %>
Welcome, <%=request.getAttribute("uname")%>!
<br/>
You are logged in as <%=request.getAttribute("utype")%>.
<br><br><br>
<form method="post" action="<%= request.getRequestURI() %>">
	<input type="hidden" name="action" value="logout"/>
	<input type="submit" class="button" value="Logout"/>
</form>
</div>