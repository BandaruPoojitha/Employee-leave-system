<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" import="com.cmi.lms.beans.*" pageEncoding="UTF-8"%>
<%
	ArrayList<String> al1 = (ArrayList<String>) request.getAttribute("viewdepartment");
%>
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
	border: 5px solid pink;
	padding: 10px;
}

td, tr {
	padding: 10px;
	background-color:grey;
	text-color:white;
}
div{
color:white;

}
h2{
color:violet;}

legend {
	color: blue;
	padding-bottom: 30px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Department Details!!</h2>
	<table border="1">
		<tr>
			<td>DepartmentId</td>
			<td>ManagerId</td>
		</tr>
		<%
			for (int i = 0; i < al1.size(); i++) {
		%>

		<tr>
			<td><div>
				<%
					out.print(al1.get(i));
				%></div>
			</td>
			<td><div>
				<%
					out.print(al1.get(i + 1));
				%></div>
			</td>
		</tr>
		<%
			i++;
			}
			request.removeAttribute("viewdepartment");
		%>
	</table>
</body>
</html>