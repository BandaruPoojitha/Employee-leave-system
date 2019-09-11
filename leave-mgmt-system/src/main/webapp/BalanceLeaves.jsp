<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="java.util.*,com.cmi.lms.beans.*"
	pageEncoding="ISO-8859-1"%>

<%
	ArrayList<BalanceLeaves> al = (ArrayList<BalanceLeaves>) request.getAttribute("balance");
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
	background-color: grey;
	text-color: white;
}

div {
	color: white;
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
	<table border="1">
		<tr>
			<td>Available PaidLeaves</td>
			<td>Used/Taken LOPLeaves</td>
		</tr>
		<tr>
			<td><div>
					<%
						out.print(al.get(0).getPaid());
					%>
				</div></td>
			<td><div>
					<%
						out.print(al.get(0).getLOP());
					%>
				</div></td>
		</tr>
	</table>
	<%
		session.removeAttribute("leavebal");
	%>

</body>
</html>