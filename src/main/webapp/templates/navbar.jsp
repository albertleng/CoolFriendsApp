<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<%
	String login_name  = (String) session.getAttribute("name");
	String login_type  = (String) session.getAttribute("type");
	String user_id     =  (String) session.getAttribute("user_id");
	String manage_link = "update?id=" + user_id;
	String inbox_link  = "inbox?id=" + user_id;
	int userType = 0;//0:non-logged-in, 1:User, 2:admin
	if (login_type != null) {
		if (login_type.equals("user")) {
			userType = 1;
		} else {
			userType = 2;
		}
	}
%>


<style>
html, body {
	/* 	margin-left:15px; margin-right:15px;  */
	padding: 0px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
}

table {
	border-collapse: collapse;
	border-bottom: 1px solid gray;
	font-family: Tahoma, Verdana, Segoe, sans-serif;
	width: 72%;
}

th {
	border-bottom: 1px solid gray;
	background: none repeat scroll 0 0 #09c332;
	padding: 10px;
	text-align: left;
	color: #FFFFFF;
}

tr {
	border-top: 1px solid gray;
	text-align: center;
}

td {
	text-align: left;
}

tr:nth-child(even) {
	background: #FFFFFF
}

tr:nth-child(odd) {
	background: #BBBBBB
}

#wrapper {
	width: 100%;
	margin-top: 0px;
}

#header {
	width: 70%;
	background: #09c332;
	margin-top: 0px;
	padding: 15px 0px 15px 15px;
}

#header h2 {
	width: 100%;
	margin: auto;
	color: #FFFFFF;
	
}

#container {
	width: 100%;
	margin: auto
}

#container h3 {
	color: #000;
}

#container #content {
	margin-top: 20px;
}

.add-button {
	border: 1px solid #666;
	border-radius: 5px;
	padding: 4px;
	font-size: 12px;
	font-weight: bold;
	width: 120px;
	padding: 5px 10px;
	margin-bottom: 15px;
	background: #cccccc;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #333;
}

.topnav a {
	float: left;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #4CAF50;
	color: white;
}

.error {color:red}
</style>
</head>
<div class="topnav">
	<a class="active" href="home">Home</a> <a href="list">Friends</a> <a
		href="about">About Us</a>
	<%
		if (userType == 2) {
	%>
	<a href="manage">Manage Users</a>
	<%
		}
	%>

	<%
		if (userType == 0) {
	%>
	<a href="register">Register</a> <a href="login">Login</a>
	<%
		} else {
	%>
	<a href=<%=inbox_link%>>Inbox</a>
	
	<a href=<%=manage_link%>>Account:<%=login_name%></a> <a href="logout"
		onclick="if (!(confirm('Are you sure you want to logout?'))) return false">Logout</a>
		
	
	<%
		}
	%>
</div>
</html>