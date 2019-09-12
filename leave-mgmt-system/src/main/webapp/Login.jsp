<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.cmi.lms.beans.*"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
fieldset {
	padding: 20px;
	border: 3px solid red;
	width: 300px;
	background-color: white;
}

h2 {
	color: red;
}

table {
	padding: 10px;
}

td, tr {
	padding: 10px;
}

legend {
	color: blue;
	padding-bottom: 30px;
}

body {
	background-color: lightgrey;
}
</style>

<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<%
		if (session.getAttribute("username") != null && session.getAttribute("username").equals("nullusername")) {
			out.println("<h2>please enter valid username!!!!<h2>");
		} else if (session.getAttribute("password") != null
				&& session.getAttribute("password").equals("nullpassword")) {
			out.println("<h2>please enter valid password!!!!<h2>");
		}
	%>


	<center>
		<fieldset>
			<center>
				<legend>login with valid details</legend>
			</center>
			<table>
				<form action="/login/getUser" method="post">
					<tr>
						<td>Username:</td>
						<td><input type="text" name="username" /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" /></td>
					</tr>
				</form>
			</table>
		</fieldset>
	</center>
	<%session.removeAttribute("password") ;
	session.removeAttribute("username") ;%>
</body>
</html>