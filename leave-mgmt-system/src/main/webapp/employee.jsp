<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="com.cmi.lms.beans.*"
	pageEncoding="ISO-8859-1"%>
<%
	if (request.getAttribute("Error") != null && request.getAttribute("Error").equals("Error Occured")) {
		out.print("Your Leave Cannot be Applied....Sorry!!!!!!\n");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<td>
				<%
					
					if (session.getAttribute("role").equals("employee")||session.getAttribute("role").equals("admin")) {
				%>

				<fieldset>
					<center>
						<legend></legend>
					</center>
					<a href="/employee/applyleave" target="iframe1" />Apply for Leave</a><br />
					<a href="/employee/cancelleave" target="iframe1" />Cancel Leave</a><br />
					<a href="/employee/track" target="iframe1" />CheckStatus/TrackMyleave</a><br />
					<a href="/employee/balanceleaves" target="iframe1" />Leave Balance</a>
			</td>

			</fieldset>
			<%
				} else if (session.getAttribute("role").equals("manager") || session.getAttribute("role").equals("CEO")) {
			%>
			<fieldset>
				<center>
					<legend></legend>
				</center>
				<a href="/employee/applyleave" target="iframe1" />Apply for Leave</a><br />
				<a href="/employee/grantleave" target="iframe1" />Grant Leave</a><br />
				<a href="/employee/cancelleave" target="iframe1" />Cancel Leave</a><br />
				<a href="/employee/track" target="iframe1" />CheckStatus/TrackMyleave</a><br />
				<a href="/employee/balanceleaves" target="iframe1" />Leave Balance</a>
				</td>

			</fieldset>
			<%
				}session.removeAttribute("adminchoice");
			%>
			<td><iframe width="800px" height="500px" src="/welcome.jsp"
					name="iframe1"></iframe></td>
		</tr>
	</table>
</body>
</html>