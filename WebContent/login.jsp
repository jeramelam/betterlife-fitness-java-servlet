<div id="login" class="form">
	<h3>
	User Login
	</h3>
	<form method="post" action="<%= request.getRequestURI() %>">
    User Name:<br>
    <input type="text" name="username" class="textfield"/>
    <br>
    Password:<br>
    <input type="password" name="password" class="textfield"/>
    <br><br>
    <input type="hidden" name="action" value="login"/>
    <input type="submit" value="Login"  class="button"/>
    <input type="reset" value="Reset"  class="button" onclick="document.form.msg.value = null; return false;">
    <% String msg = (String) request.getAttribute("msg"); %>
    <% if (msg != null) { %>
		<div style="color:red;">
			<output id="msg"><%=msg%></output>
		</div>
	<% } %>
    </form>
	<br/><p>Not a Member?<br/><a href="registration.jsp">Please register now.</a></p>
</div>