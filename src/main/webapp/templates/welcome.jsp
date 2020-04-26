<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Cool Friends App</title>
</head>
<body>
<%@include file='navbar.jsp'%>
	<%
		String username = (String)request.getAttribute("name");
	%>
<h1>Welcome <%=username%>! Please login</h1>
<p>You will be redirected to <a href="login">login</a> in 3 seconds...</p>
    <script>
        var timer = setTimeout(function() {
            window.location='login'
        }, 3000);
    </script>


<p>
<footer class="footer">
	<div class="container">
		<div align="center">
			<%@include file='footer.html'%>
		</div>
	</div>
</footer>
</html>