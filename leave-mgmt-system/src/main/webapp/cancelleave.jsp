<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="java.util.*,com.cmi.lms.beans.*,java.time.*,java.text.*"
	import="leave.*" pageEncoding="ISO-8859-1"%>
<%
	if (request.getAttribute("cancel") != null) {
		ArrayList<ApplyLeave> ld = (ArrayList<ApplyLeave>) request.getAttribute("cancel");
		session.setAttribute("cancellist", ld);
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

#button {
	background-color: lightgreen;
	color: red;
	width: 180px;
	height: 30px;
}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
			<th></th>
		</tr>
		<form action="/employeeoperations/cancel" method="post">
			<%
				for (int i = 0; i < ld.size(); i++) {


						out.println("<tr><td>" + ld.get(i).getSno()+ "</td><td>" + ld.get(i).getEmployeeId().getEmployeeId()+ "</td><td>" + ld.get(i).getLeaveType()
								+ "</td><td>" + ld.get(i).getStartdate()+ "</td><td>" + ld.get(i).getEnddate() + "</td><td>" + ld.get(i).getApplyTo().getEmployeeId()
								+ "</td><td>" + ld.get(i).getReason() + "</td><td>" + ld.get(i).getStatus() + "</td>");
			%>
		
		<td>Cancel:: <input id="<%out.print("A" + i);%>" type="radio"
			name="jk<%=i%>" value="cancel" /> Dont Cancel::<input
			id="<%out.print("B" + i);%>" type="radio" name="jk<%=i%>"
			value="dontcancel" />



		</td>
		<%
			
				}
		%>
	</table>
	<input id="button" value="SUBMIT" type="submit" />

	</form>
	<%
		}
	%>
</body>
</html>