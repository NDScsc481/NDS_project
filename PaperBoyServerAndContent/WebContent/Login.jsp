<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Paperboy Login</title>
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
</div>

<div><h2>Employee Web Portal Login</h2>
${errorMsg}<br>
<form action="LoginSrvlt" method="post">	
	<input type="text" name="name"/><br><sup>Username</sup><br><br>       
    <input type="password" name="pass"/><br><sup>Password</sup><br><br>
    <input type="submit" value="Login">            
</form>
</div>

<div id="footer">
Copyright © Team One - CSC 481
</div>

</body>
</html>