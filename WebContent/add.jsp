<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<script language="javascript" type="text/javascript" src="datetimepicker.js">
</script>
<script>
var status = "<%= request.getParameter("status") %>";
if (status!=null && status=="success"){
	self.close();
}
</script>
<title>Add Member</title>
</head>
<body onunload="opener.location=('member.jsp?pid='+${pid});">
<div class="tablestyle">
<table>
	<tr>
		<td width="50px">${pid}</td>
		<td width="150px">		
			Member
		</td>
		<td>Action</td>
	</tr>
	<c:forEach items="${list}" var="u" varStatus="count">
		<tr>
		<td>${count.count}</td>
		<td>${u.fullName}</td>
		<td>
		<form method="post" action="member">
		  	<input type="submit" value="Add">
		  	<input type="hidden" name="mid" value="${u.userId}">
		  	<input type="hidden" name="pid" value="${pid}">
		  	<input type="hidden" name="action" value="Add">
		</form>
		</td>		
		</tr>
	</c:forEach>
</table>
</div>
</body>
</html>