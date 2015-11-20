<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delivery Report</title>
<head>
<style>
body {background-color: #e6c594}
#header {
  	position: relative;
	width: 100%;
	height: 130px;
	background-image: url(http://i.imgur.com/eq7h2xU.png);
	background-position: center;
}
#nav {
    line-height:30px;
    background-color: #bd9c7b;
    height:550px;
    width:200px;
    float:left;
    padding:20px;	      
}
#section {
    width:auto;
    height:200; 
    float:left;
    padding:50px;
    overflow: auto;	 	 
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
<div id="header"></div>

<div id="nav">
<a href="EmployeeHome.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Home</b></font></a><br><br>
<a href="CustomerCenter.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Customer Center</b></font></a><br><br>
<a href="PublicationCenter.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Publications</b></font></a><br><br>
<a href="Printing.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Printing</b></font></a><br><br>
<a href="Login.jsp" style="text-decoration:none"><font color="white" size = "4"><b>Log Out</b></font></a><br><br>
</div>

<div id="section">
<h1><font color=#9c3141 face="arial">Delivery Report</font></h1>
${Msg}
</div>

<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>

