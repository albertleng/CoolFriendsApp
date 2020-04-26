<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<title>About Cool Friends App</title>
</head>
<body>
<%@include file='navbar.jsp'%>
<H1>About Cool Friends App</H1>

<h2>Why Cool Friends Matters<% 
if (userType == 1 && login_name != null) {
    out.println(", " + login_name);
}
%></h2>
Healthy relationships are central to living a positive, productive life. Cool Friends app is a social network that allows you to feel empowered while you make those connections, whether you’re dating, looking for friends, or growing your professional network. One first move on Cool Friends App could change your life.
<p>
<div align="center"><img src="<c:url value="/resources/images/about.png" />" alt="Cool Friends" width="700" height="500" /></div>

<h2>How Cool Friends Works</h2>
When members of the opposite sex match on Cool Friends App, women are required to make the first move, shifting old-fashioned power dynamics and encouraging equality from the start.
<p>
<div align="center">
<img src="<c:url value="/resources/images/about02.png" />" alt="Cool Friends" /></div>


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
