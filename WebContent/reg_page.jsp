<div id="reg" class="form">
	<h3>
	Member Registration
	</h3>
	<form method="post" action="register">
    <div style="text-align:right">
    <table>
    <tr>
    <td>First Name:</td>
    <td><input type="text" name="fname" class="textfield"/></td>
    </tr>
    <tr>
    <td>Last Name:</td>
    <td><input type="text" name="lname" class="textfield"/></td>
    </tr>
	<tr>
	<td>User Name:</td>
    <td><input type="text" name="uname" class="textfield"/></td>
    </tr>
	<tr>
	<td>Password:</td>
    <td><input type="password" name="pwd" class="textfield"/></td>
    </tr>
    </table>
    </div>
    <input type="hidden" name="action" value="register"/>
    <input type="submit" value="Register"  class="button"/>
    <input type="reset" value="Reset"  class="button" onclick="document.form.msg.value = null; return false;">
    <% String msg = (String) request.getAttribute("regerror"); %>
    <% if (msg != null) { %>
		<div style="color:red;">
			<output id="msg"><%=msg%></output>
		</div>
	<% } %>
    </form>
</div>