<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Publications - Add Publication</title>
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
<h2>Add Publication</h2><br>
${Msg}<br>
<form action="PubAddSrvlt" method="post">
	
	<input type="text" name="title"/><br><sup>Title</sup><p>
	<input type="text" name="genre"/><br><sup>Genre</sup><p>
    <input type="text" name="price"/><br><sup>Price</sup><p>
    Sun<input type="checkbox" name="day1" value="Sun"> | 
    Mon<input type="checkbox" name="day2" value="Mon"> |
    Tue<input type="checkbox" name="day3" value="Tue"> |
    Wed<input type="checkbox" name="day4" value="Wed"> |
    Thu<input type="checkbox" name="day5" value="Thu"> |
    Fri<input type="checkbox" name="day6" value="Fri"> |
    Sat<input type="checkbox" name="day7" value="Sat"> <p>
    <input type="radio" name="freq" value="day" checked> Daily<br>
    <input type="radio" name="freq" value="week"> Weekly<br>
    <input type="radio" name="freq" value="month"> Monthly<p>
    	
    <input type="submit" value="Add">     
    </form> 
</div>

<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>

