<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Publications</title>
<head>
<style>
#header {
    background-color:gray;
    color:white;
    text-align:center;
    padding:5px;
}
#nav {
    line-height:30px;
    background-color:#eeeeee;
    height:720px;
    width:200px;
    float:left;
    padding:5px;	      
}
#section {
    width:70%;
    float:left;
    padding:10px;	 	 
}
#footer {
 	position: fixed;
  	bottom: 0;
    width: 100%;
    background-color:black;
    color:white;
    clear:both;
    text-align:center;
   	padding:5px;	 	 
}
</style>
</head>
<body>

<div id="header">
<h1>Newspaper Delivery System</h1>
</div>

<div id="nav">
<a href="EmployeeHome.jsp">Home</a><br>
<a href="CustomerCenter.jsp">Customer Center</a><br>
<a href="PublicationCenter.jsp">Publications</a><br>
<a href="Printing.jsp">Printing</a><br>
<a href="Login.jsp">Log Out</a><br>
</div>

<div id="section">
<h2>Publications</h2>
<a href="PublicationAdd.jsp">Add</a><br>
<a href="PublicationModify.jsp">Modify</a><br>
<a href="PublicationPrint.jsp">View</a><br>
</div>

<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>

