<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Center - Add Customer</title>
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
<br>
<a href="EmployeeHome.jsp">Home</a><br>
<a href="CustomerCenter.jsp">Customer Center</a><br>
<a href="PublicationCenter.jsp">Publications</a><br>
<a href="Printing.jsp">Printing</a><br>
<a href="Login.jsp">Log Out</a><br>
</div>

<div id="section">
<h2>Add a Customer</h2><br>
${Msg}<br>
<form action="CustAddSrvlt" method="post">
	
	<input type="text" name="first"/><br><sup>First Name</sup><p>      
    <input type="text" name="last"/><br><sup>Last Name</sup><p>    
    <input type="text" name="add1"/><br><sup>Address Line 1</sup><p>       
    <input type="text" name="add2"/><br><sup>Address Line 2</sup><p>    
    <input type="text" name="city"/><br><sup>City</sup><p>    
    <input type="text" name="state"/><br><sup>State</sup><p>    
    <input type="text" name="zip"/><br><sup>Zip Code</sup><p>        
    <input type="text" name="phone"/><br><sup>Phone</sup><p>    
    	
    <input type="submit" value="Add">     
    </form>   
</div>

<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>

