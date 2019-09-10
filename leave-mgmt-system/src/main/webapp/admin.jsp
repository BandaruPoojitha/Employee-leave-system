<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="ISO-8859-1">
<title>admin page</title>
</head>
<body>
	<table>
		<tr>
			<td><fieldset width="200px" height="500px">

					<a href="/admin/createemployee" target="iframe" />create new
					Employee </a> <br /> <a href="/admin/adddepartment" target="iframe" />Create
					Department </a> <br /> <a href="/adminoperations/viewDepartment"
						target="iframe" />view Department </a> <br /> <a
						href="/admin/editEmployee" target="iframe" />Edit Employee Details
					</a> <br />
				</fieldset></td>

			<td><iframe src="/welcome.jsp" name="iframe" height="500px"
					width="700px"></iframe></td>
		</tr>
	</table>
</body>
</html>