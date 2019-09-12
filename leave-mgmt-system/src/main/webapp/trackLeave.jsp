<%@page import="java.time.temporal.ChronoUnit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="java.util.*,com.cmi.lms.beans.*"
	pageEncoding="ISO-8859-1"%>

<%
	ArrayList<ApplyLeave> ld = (ArrayList<ApplyLeave>) request.getAttribute("track");
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
			
			for (int i = 0; i < ld.size(); i++) {


				out.println("<tr><td>" + ld.get(i).getSno()+ "</td><td>" + ld.get(i).getEmployeeId().getEmployeeId()+ "</td><td>" + ld.get(i).getLeaveType()
						+ "</td><td>" + ld.get(i).getStartdate()+ "</td><td>" + ld.get(i).getEnddate() + "</td><td>" + ld.get(i).getApplyTo().getEmployeeId()
						+ "</td><td>" + ld.get(i).getReason() + "</td><td>" + ld.get(i).getStatus() + "</td>");
			}
			session.removeAttribute("track");
		%>
	
</body>
</html>