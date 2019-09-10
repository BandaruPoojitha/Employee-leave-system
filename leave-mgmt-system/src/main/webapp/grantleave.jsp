<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="java.util.*"
	import="com.cmi.lms.beans.*"
	pageEncoding="ISO-8859-1"%>

<%
	if (request.getAttribute("list") != null) {
		ArrayList<String> ld = (ArrayList<String>) request.getAttribute("list");
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
		<form action="/employeeoperations/updatestatus" method="post">
			<%!int i = 0;%>
			<tr>
				<%
					for (i = 0; i < ld.size(); i++) {

							if (ld.get(6).equals("processing")) {
								out.println("<td>" + ld.get(i + 7) + "</td><td>" + ld.get(i) + "</td><td>" + ld.get(i + 1)
										+ "</td><td>" + ld.get(i + 2) + "</td><td>" + ld.get(i + 3) + "</td><td>"
										+ ld.get(i + 4) + "</td><td>" + ld.get(i + 5) + "</td><td>" + ld.get(i + 6) + "</td>");
								session.setAttribute("sno", ld.get(7));
				%>
				<td>Accept::<input id="<%out.print("A" + i);%>" name="JK<%=i%>"
					type="radio" value="accept" /> Reject:: <input
					id="<%out.print("B" + i);%>" name="JK<%=i%>" type="radio" value="reject" /> 
					<%Bean bean = new Bean();
						if (!bean.getEmployeeType().equals("CEO")) {%>
					Forward::<input id="<%out.print("C" + i);%>"
					name="JK<%=i%>" type="radio" value="forward" /> <%
 	}
 %>
				</td>
			</tr>

			<%
				i = i + 7;
						}
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