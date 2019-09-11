<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="java.util.*"
	import="com.cmi.lms.beans.*"
	pageEncoding="ISO-8859-1"%>

<% System.out.println("hi"+session.getAttribute("list"));
	if (session.getAttribute("list") != null) {
		ArrayList<ApplyLeave> ld = (ArrayList<ApplyLeave>) session.getAttribute("list");
		session.setAttribute("updatelist", ld);
		
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
<title>grant</title>
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
		<form action="/employeeoperations/updatestatus"  method="post">
			<%!int i = 0;%>
			<tr>
				<%
			
					for (i = 0; i < ld.size(); i++) {

							if (ld.get(i).getStatus().equals("processing")) {
								out.println("<tr><td>" + ld.get(i).getSno()+"</td><td>" + ld.get(i).getEmployeeId().getEmployeeId()+ "</td><td>" + ld.get(i).getLeaveType()
										+ "</td><td>" + ld.get(i).getStartdate() + "</td><td>" + ld.get(i).getEnddate() + "</td><td>"
										+ ld.get(i).getApplyTo().getEmployeeId()+ "</td><td>" + ld.get(i).getReason() + "</td><td>" + ld.get(i).getStatus() + "</td>");
				%>
				<td>Accept::<input id="<%out.print("A" + i);%>" name="JK<%=i%>"
					type="radio" value="accept" /> Reject:: <input
					id="<%out.print("B" + i);%>" name="JK<%=i%>" type="radio" value="reject" /> 
					<%
						if (!session.getAttribute("role").equals("CEO")) {%>
					Forward::<input id="<%out.print("C" + i);%>"
					name="JK<%=i%>" type="radio" value="forward" /> </tr><%
 	}
							}
					}
	
 %>
				</td>
			</tr>

			
	
	<input id="button" value="SUBMIT" type="submit" />
	</form>
	</table>
	<%} %>
</body>
</html>