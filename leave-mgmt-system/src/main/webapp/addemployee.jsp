<%@page import="com.cmi.lms.beans.Employee"%>
<%@page import="java.util.Iterator"%>
<%@page import="javax.servlet.jsp.tagext.IterationTag"%>
<%@page import="com.cmi.lms.beans.Login"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	session.setAttribute("value", "0");
%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
fieldset {
	background-color: lightgrey;
	padding: 20px;
	border: 3px solid red;
}

#f1 {
	background-color: lightblue;
	color: white;
}

table {
	padding: 10px;
}

h3 {
	COLOR: GREEN;
}

h2 {
	COLOR: RED;
}

td, tr {
	padding: 10px;
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
	<%
		if (session.getAttribute("addemployee") != null && session.getAttribute("addemployee").equals("added"))
			out.println("<h3>Employee has been added successfully!!!</h3>");
		else if (session.getAttribute("addemployee") != null
				&& session.getAttribute("addemployee").equals("notadded"))
			out.println("<h2>Employee has been NOT added!!!</h2>");
		else if (session.getAttribute("addemployee") != null
				&& session.getAttribute("addemployee").equals("contact"))
			out.println("<h2>Please enter valid phonenumber!!!</h2>");
		else if (session.getAttribute("addemployee") != null && session.getAttribute("addemployee").equals("email"))
			out.println("<h2>please enter correct email-Id!!!</h2>");
	%>
	<fieldset>
		<center>
			<legend>FIll the form</legend>
		</center>
		<form action="/adminoperations/addemployee" method="POST">
			<table>
				<tr>
					<td>Employee Name::</td>
					<td><input type="text" name="employeeName" /></td>
				</tr>
				<tr>
					<td>Employee Id::</td>
					<td><input type="text" name="employeeId" /></td>
				</tr>
				<tr>
					<td>Department Id::</td>
					<td><input type="text" name="department" /></td>
				</tr>
				<tr>
					<td>ManagerId::</td>
					<td><select name="managerId">
					

							<%
							if(true){
								Employee emp=new Employee();
								emp.setEmployeeId("100");
								Login log=new Login();
								log.setEmployeeId(emp);
								
					ArrayList<Login> list= new ArrayList<>();//(ArrayList<Login>)request.getAttribute("empid");
					list.add(log);
					Iterator<Login> iterate=list.iterator();
					while(iterate.hasNext()){
						String empid=iterate.next().getEmployeeId().getEmployeeId();
					out.print("<option value="+(char)34+ empid+(char)34+">"+empid+"</option>"); 
					
					}
					
					
					}%>



					</select></td>
				</tr>
				<tr>
					<td>Address::</td>
					<td><input type="text" name="address" /></td>
				</tr>
				<tr>
					<td>Email::</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td>Contact number::</td>
					<td><input type="number" name="phonenumber" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="add" value="Add" /></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>