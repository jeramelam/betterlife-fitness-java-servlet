<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<div id="session" class="box">
<h3>Program Sessions</h3>
<div class="tablestyle">
<table>
    <tr>
    	<c:if test="${loggedIn}">
    	<td>Action</td>
    	</c:if>
		<td width="200px">
		Fitness Program
        </td>
        <td  width="400px">
        Time
        </td>
        <td width="200px">
        Availability
        </td>
        <td width="200px">
        Instructor
        </td>
	</tr>
<c:forEach items="${programs}" var="p" varStatus="count"> 
  <tr>
  	<c:if test="${loggedIn && utype=='member'}">
  	<td>
  	<c:if test="${!p.cancelled}">
  	<form method="post" action="program">
  	<c:choose>
    	<c:when test="${p.booked}">
	  	<input type="submit" value="Cancel" onclick="alert('session is unbooked');">
	  	<input type="hidden" name="id" value="${p.programId}">
	  	<input type="hidden" name="action" value="Unbook">
		</c:when>
  		<c:otherwise>
	  	<input type="submit" value="Book" onclick="alert('session is booked');" ${p.availability > 0? '' : 'disabled="disabled"'}>
	  	<input type="hidden" name="id" value="${p.programId}">
	  	<input type="hidden" name="action" value="Book">
		</c:otherwise>
	</c:choose>
  	</form>
  	</c:if>
  	</td>
  	</c:if>
  	<c:if test="${loggedIn && utype!='member'}">
  	<td>
	<c:url value="reschedule.jsp" var="popup">
		<c:param name="pid" value="${p.programId}" />
	</c:url>
	<input type="submit" value="Re-schedule" onclick="window.open('${popup}','','width=800, height=300');" ${p.cancelled? 'disabled="disabled"' : ''}>
	<form method="post" action="program">
	  	<input type="submit" value="Cancel" ${p.cancelled? 'disabled="disabled"' : ''}>
	  	<input type="hidden" name="id" value="${p.programId}">
	  	<input type="hidden" name="action" value="Cancel">
	</form>
  	</td>
  	</c:if>
    <td><a href="detail.jsp#${p.programId}">${p.programName}</a></td>
    <td>${p.time}</td>
    <td>
    <c:choose>
    	<c:when test="${p.cancelled}">
    		cancelled
    	</c:when>
    	<c:otherwise>
	    	<c:if test="${p.availability > 0}">
	    	<span class="specialdata">${p.availability}</span>
	    	</c:if>
	    	<c:if test="${p.availability <= 0}">
	    	&nbsp&nbsp&nbsp&nbspFull&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	    	</c:if>
	    	<c:if test="${loggedIn && utype!='member'}">
	    		<c:url value="member.jsp" var="mlink">
					<c:param name="pid" value="${p.programId}" />
				</c:url>
	    		<a onclick="window.open('${mlink}','','width=900, height=300');">Members</a>
	    	</c:if>
		</c:otherwise>
	</c:choose>
    </td>
    <td>${p.instructor}</td>
  </tr>
</c:forEach>
</table>
<% String msg = (String) request.getAttribute("cancelmsg"); %>
    <% if (msg != null) { %>
		<div style="color:red;">
			<output id="msg"><%=msg%></output>
		</div>
<% } %>
</div>
</div>