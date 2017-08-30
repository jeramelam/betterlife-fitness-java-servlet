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
<title>Member</title>
</head>
<body onunload="opener.location=('program.jsp');">
<div class="tablestyle">
<table>
	<tr>
		<td width="50px"></td>
		<td width="150px">		
			Member
			<c:if test="${utype == 'admin' || utype == 'staff'}">
				<c:url value="add.jsp" var="popup">
					<c:param name="pid" value="${param.pid}" />
					<c:param name="action" value="addmember" />
				</c:url>
				<input type="button" value="Add" onclick="window.open('${popup}','','width=800, height=300');">
			</c:if>
		</td>
		<c:if test="${utype == 'admin' || utype == 'staff'}">
			<td>Action</td>
		</c:if>
	</tr>
<c:forEach items="${programs}" var="p" varStatus="count">
	<c:if test="${count.count eq param.pid}">
	<c:forEach items="${p.members}" var="m" varStatus="count">
		<tr>
		<td>${count.count}</td>
		<td>${m.fullName}</td>
		<c:if test="${utype == 'admin' || utype == 'staff'}">
		<td>
		<form method="post" action="member">
		  	<input type="submit" value="Delete">
		  	<input type="hidden" name="mid" value="${m.memberId}">
		  	<input type="hidden" name="pid" value="${param.pid}">
		  	<input type="hidden" name="action" value="Delete">
		</form>
		</td>
		</c:if>
		</tr>
	</c:forEach>
	</c:if>
</c:forEach>
</table>
</div>
</body>
</html>