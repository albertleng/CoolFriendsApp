<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<%@include file='navbar.jsp'%>
	<div id="wrapper">
		<div id="header" align="center">
			<h2>Registration Page</h2>
		</div>
	</div>
	<div id="container">
		<form:form id="regForm" modelAttribute="user" action="registerProcess"
			method="POST">
			<table>
				<tr>
					<td align="right"><label>Name (*):</label></td>
					<td><form:input path="name" name="name" id="name" />
					<form:errors path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<td align="right"><label>Password (*):</label></td>
					<td><form:password path="password" name="password"
							id="password" />
					<form:errors path="password" cssClass="error" /></td>
				</tr>
				<tr>
					<td align="right"><label>Contact Number (*):</label></td>
					<td><form:input path="contact" name="contact" id="contact" />
					<form:errors path="contact" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td align="right"><label>Email (*):</label></td>
					<td><form:input path="email" name="email" id="email" />
					<form:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td align="right"><label>Gender (*):</label></td>
					<td><form:input path="gender" name="gender" id="gender" />
					<form:errors path="gender" cssClass="error" /></td>
				</tr>
				<tr>
					<td align="right"><label>Date of Birth:</label></td>
					<td><form:input path="dob" name="dob" id="dob" />
					<form:errors path="dob" cssClass="error" /></td>
				</tr>
				<tr>
					<td align="right"><label>Country:</label></td>
					<td><form:input path="country" name="country" id="country" />
					</td>
				</tr>
				<tr>
					<td align="right"><label>City:</label></td>
					<td><form:input path="city" name="city" id="city" /></td>
				</tr>
				<tr>
					<td align="right"><label>Postal Code:</label></td>
					<td><form:input path="postalcode" name="postalcode"
							id="postalcode" /></td>
				</tr>
				<tr>
					<td><form:hidden path="type" value="user" /></td>
					<td><input type="submit" value="Save" class="save" /></td>
				</tr>
				<tr></tr>

			</table>
		</form:form>
		<div style=""></div>

	</div>

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