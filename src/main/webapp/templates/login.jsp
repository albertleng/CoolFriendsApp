<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<%@include file='navbar.jsp'%>
	<%
		String message = (String) request.getAttribute("message");
		if (message == null)
			message = "";
	%>
	<div id="wrapper">
		<div id="header" align="center">
			<h2>Login Page</h2>
		</div>
	</div>
	<div id="container">

		<form:form id="loginForm" modelAttribute="login" action="${pageContext.request.contextPath}/loginProcess"
			method="POST">
			<table>
				<tr>
					<td align="right"><label>Username:</label></td>
					<td><form:input path="name" name="name" id="name" /></td>
				</tr>
				<tr>
					<td align="right"><label>Password:</label></td>
					<td><form:password path="password" name="password"
							id="password" /></td>
				</tr>
				<tr>
				<td></td>
					<td><form:button id="login" name="login">Login</form:button>
					</td>
				</tr>
				<tr></tr>
			</table>
		</form:form>
		<div align="center" style="font-style: italic; color: red;"><%=message%></div>

		<div style=""></div>

	</div>
</body>
<p>
<footer class="footer">
<div class="container">
	<div align="center">
		<%@include file='footer.html'%>
	</div>
</div>
</footer>
</html>