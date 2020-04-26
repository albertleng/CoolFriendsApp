<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Cool Friends App</title>
</head>
<body>
<%@include file='navbar.jsp'%>
<H1>Welcome to Cool Friends App<% 
if (userType == 1 && login_name != null) {
    out.println(", " + login_name);
}
%>
</H1>
<h2>What is Cool Friends App</h2>
Whether you’re new to a city or looking to expand your social circle, Cool Friends App is <b>THE</b> simplified way to create meaningful friendships.
<p>
<div align="center"><img src="<c:url value="/resources/images/cool_friends.jpg" />" alt="Cool Friends" /></div>
<p>
<footer class="footer">
	<div class="container">
		<div align="center">
			<%@include file='footer.html'%>
		</div>
	</div>
</footer>
</body>
</html>
