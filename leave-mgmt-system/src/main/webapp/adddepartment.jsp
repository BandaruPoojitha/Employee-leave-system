<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

#f1 {
	background-color: lightblue;
	color: white;
}

table {
	padding: 10px;
}

h3 {
	COLOR: GREEN;
}

td, tr {
	padding: 10px;
}

legend {
	color: blue;
	padding-bottom: 30px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if (session.getAttribute("add") != null && session.getAttribute("add").equals("added"))
			out.println("<h3>Department has been added successfully!!!</h3>");
	%>
	<fieldset>
		<center>
			<legend>fill details</legend>
		</center>
		<table>
			<form action="/adminoperations/adddepartment" method="post">
				<tr>
					<td>Department Id::</td>
					<td><input type="text" name="departmentId" /></td>
				</tr>
				<tr>
					<td>Manager Id::</td>
					<td><input type="text" name="managerId" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="Department" value="add" /></td>
				</tr>
			</form>
		</table>
	</fieldset>
</body>
</html>