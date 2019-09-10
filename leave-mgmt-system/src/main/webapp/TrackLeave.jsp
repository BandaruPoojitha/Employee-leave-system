<%@page import="java.time.temporal.ChronoUnit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="java.util.*,com.cmi.lms.beans.*"
	pageEncoding="ISO-8859-1"%>

<%
	ArrayList<String> ld = (ArrayList<String>) request.getAttribute("track");
%>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#f1 {
	background-color: lightblue;
	color: white;
}

table {
	border: 5px solid pink;
}

th {
	padding: 10px;
	background-color: grey;
	color: white;
}

td, tr {
	background-color: lightgrey;
}

legend {
	color: blue;
	padding-bottom: 30px;
}
</style>
<meta charset="ISO-8859-1">
<title>track leave</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>sno</th>
			<th>EmployeeId</th>
			<th>LeaveType</th>
			<th>startdate</th>
			<th>enddate</th>
			<th>applyTo</th>
			<th>Reason</th>
			<th>status</th>
		</tr>
		<%
			//session.setAttribute("sizeal",al1.size());
			//out.println(al1.size()+"size");
			for (int i = 0; i < ld.size(); i++) {

				out.println("<tr><td>" + ld.get(i + 7) + "</td><td>" + ld.get(i) + "</td><td>" + ld.get(i + 1)
						+ "</td><td>" + ld.get(i + 2) + "</td><td>" + ld.get(i + 3) + "</td><td>" + ld.get(i + 4)
						+ "</td><td>" + ld.get(i + 5) + "</td><td>" + ld.get(i + 6) + "</td>");
				i = i + 7;
			}
			session.removeAttribute("track");
		%>
	
</body>
</html>