<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="javascript" type="text/javascript" src="datetimepicker.js">
</script>
<script>
var status = "<%= request.getParameter("status") %>";
if (status!=null && status=="success"){
	self.close();
}
</script>
<title>Reschedule</title>
</head>
<body onunload="opener.location=('program.jsp');">
<form method="post" action="reschedule">
<table>
<tr>
<th>Start Time</th>
<th></th>
<th>End Time</th>
</tr>
<tr>
	<td>
	<input type="text" id="startdate" name="startdate" maxlength="25" size="25">
	<a href="javascript:NewCal('startdate','MMMddyyyy',true,12)">
		<img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date">
	</a>
	</td>
	<td>&nbsp&nbspto&nbsp&nbsp</td>
	<td>
	<input type="text" id="enddate" name="enddate" maxlength="25" size="25">
	<a href="javascript:NewCal('enddate','MMMddyyyy',true,12)">
		<img src="images/cal.gif" width="16" height="16" border="0" alt="Pick a date">
	</a>
	</td>
	<td>
	&nbsp
		<input type="hidden" name="id" value="<%= request.getParameter("pid") %>">
		<input type="hidden" name="action" value="Re-schedule">
		<input type="submit" value="Change">
	</td>
</tr>
<tr>
<td>
<br>
<% String msg = (String) request.getAttribute("error"); %>
    <% if (msg != null) { %>
		<div style="color:red;">
			<%=msg%>
		</div>
	<% } %>
</td>
<td><br><input type="button" value="Close" onclick="self.close();">
</td>
<td></td>
</tr>
</table>
</form>
</body>
</html>