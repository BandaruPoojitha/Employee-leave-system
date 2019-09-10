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

td, tr {
	padding: 10px;
}

legend {
	color: blue;
	padding-bottom: 30px;
}
</style>
<meta charset="UTF-8">

<title>edit</title>
</head>

<body>
	<fieldset>
		<center>
			<legend>Fill the corresponding Textfields which you want to
				edit and leave remaining empty!!</legend>
		</center>
		<table>
			<form action="/adminoperations/edit" method="post">
				<tr>
					<td>Enter employeeId:::</td>
					<td><input type="text" name="employeeId" /></td>
				</tr>
				<tr>
					<td>Edit Address::</td>
					<td><input type="text" name="address" /></td>
				</tr>
				<tr>
					<td>Edit PhoneNumber::</td>
					<td><input type="text" name="phonenumber" /></td>
				</tr>
				<tr>
					<td>Edit E-mail::</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="EditEmployee" value="edit" /></td>
				</tr>
			</form>
		</table>
	</fieldset>
</body>
</html>