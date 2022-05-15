<%@page import="model.BillManagement" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
<meta charset="ISO-8859-1">
<title>Create Bill</title>
</head>
<body>



<%
//Insert User Data----------------------------------
if (request.getParameter("id") != null)
{
	BillManagement userObj = new BillManagement();
String stsMsg = userObj.insertBill(request.getParameter("id"),


request.getParameter("billNo"),
request.getParameter("accNo"),
request.getParameter("amount"));
session.setAttribute("description", stsMsg);
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Bill</title>
</head>

<body>

<h1>Create Bill</h1>
<br>

<form method="post" action="add.jsp">

Bill Number: <input name="billNo" type="text"><br><br>
Account Number: <input name="accNo" type="text"><br><br>
Amount: <input name="amount" type="text"><br><br>
Description: <input name="description" type="text"><br><br>




<input name="btnSubmit" type="submit" value="Submit"><br><br>
</form>

<%
out.print(session.getAttribute("statusMsg"));
%>

<br>





</body>
</html>