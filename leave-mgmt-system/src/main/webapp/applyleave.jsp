<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.time.LocalDate"%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
fieldset {
	background-color: lightgrey;
	padding: 20px;
	width: 400px;
	border: 3px solid red;
}

h3 {
	COLOR: GREEN;
}

h2 {
	COLOR: RED;
}

#f1 {
	background-color: lightblue;
	color: white;
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
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if (session.getAttribute("status") != null && session.getAttribute("status").equals("applied"))
			out.println("<h3>Your Leave has been applied successfully!!!</h3>");
		else if (session.getAttribute("status") != null && session.getAttribute("status").equals("notapplied"))
			out.println("<h2>Your Leave has been NOT applied!!!</h2>");
	%>


	<fieldset>
		<center>
			<legend>leave application form</legend>
		</center>
		<table>
			<form action="/employeeoperations/addleave" method="Post">
			<tr>
				<td>LeaveType::</td>
				<td><select name="LeaveType">
						<option value="Paid" selected>Paid</option>
						<option value="LOP">LOP</option>
				</select></td>
			</tr>
			<tr>
				<td>From date::</td>
				<td><input type="date" name="startdate"
					value="<%=LocalDate.now()%>" placeholder="yyyy-mm-dd"></td>
			</tr>
			<tr>
				<td>To date::</td>
				<td><input type="date" name="enddate" placeholder="yyyy-mm-dd" /></td>
			</tr>

			<tr>
				<td>Reason::</td>
				<td><input type="text" name="reason" /></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" name="Apply" /></td>
			</tr>
			</form>
		</table>
	</fieldset>
</body>
</html>
