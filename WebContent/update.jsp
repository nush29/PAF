<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="model.BillManagement" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
<meta charset="ISO-8859-1">
<title>Bill Update</title>
</head>
<body>



<%

//Update user data----------------------------------
if (request.getParameter("id") != null)
{
	BillManagement userObj = new BillManagement();
String stsMsg = userObj.EditBillDetails(request.getParameter("id"),

request.getParameter("billNo"),
request.getParameter("accNo"),
request.getParameter("amount"),
request.getParameter("description"));
session.setAttribute("statusMsg", stsMsg);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

</head>

<body>

<h1> Update Bill Details</h1>

<form method="post" action="UpdateBill.jsp">
Bill ID:
<input name="id" type="text"
class="form-control form-control-sm">
Bill No:
<input name="billNo" type="text"
class="form-control form-control-sm">
<br> Account Number:
<input name="accNo" type="text"
class="form-control form-control-sm">
<br> Amount:
<input name="amount" type="text"
class="form-control form-control-sm">
<br> Description:
<input name="description" type="text"
class="form-control form-control-sm">
<br>
<input name="btnsubmit" type="submit" value="update"
class="btn btn-primary">
</form>



<%
out.print(session.getAttribute("statusMsg"));
%>
<br>
<%
BillManagement userObj = new BillManagement();
out.print(userObj.readBillDetails());
%>

</body>
</html>