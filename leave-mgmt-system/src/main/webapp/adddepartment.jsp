<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cmi.lms.beans.Login"%>
<%@page import="com.cmi.lms.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	padding: 10px;
}

h3 {
	COLOR: GREEN;
}

td, tr {
	padding: 10px;
}

legend {
	color: blue;
	padding-bottom: 30px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if (session.getAttribute("add") != null && session.getAttribute("add").equals("added"))
			out.println("<h3>Department has been added successfully!!!</h3>");
	%>
	<fieldset>
		<center>
			<legend>fill details</legend>
		</center>
		<table>
			<form action="/adminoperations/adddepartment" method="post">
				<tr>
					<td>Department Id::</td>
					<td><input type="text" name="departmentId" /></td>
				</tr>
				<tr>
					<td>Manager Id::</td>
					<td><select name="managerId">
					

							<%
							if(session.getAttribute("empids")!=null){			
					ArrayList<Login> list= (ArrayList<Login>)session.getAttribute("empids");
					Iterator<Login> iterate=list.iterator();
					while(iterate.hasNext()){
						String empid=iterate.next().getEmployeeId().getEmployeeId();
					
					out.print("<option value="+(char)34+ empid+(char)34+">"+empid+"</option>"); 
					
					}
					
					
					}
					session.removeAttribute("empids");%>



					</select></td>
					
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="Department" value="add" /></td>
				</tr>
			</form>
		</table>
	</fieldset>
</body>
</html>