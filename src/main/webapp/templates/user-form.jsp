<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<title>Add/Edit User Profile</title>
</head>

<body>
	<%@include file='navbar.jsp'%>
	<%
		LoginoutHistory loginoutHistory = (LoginoutHistory) request.getAttribute("loginouthistory");
		int loginout_id;
		int loginout_user_id;
		String loginout_event_type;
		Date loginout_time = null;
		if (loginoutHistory != null) {
			loginout_id = loginoutHistory.getId();
			loginout_user_id = loginoutHistory.getUser_id();
			loginout_event_type = loginoutHistory.getEventType();
			loginout_time = loginoutHistory.getDateTime();
		}
	%>


	<div id="wrapper">
		<div id="header" align="center">

			<%
				String current_id = request.getParameter("id");
				int updateUserType = 1;
				if (current_id != null) {
					if (userType == 2) {
						if (!current_id.equals(user_id)) {
			%>
			<h2>(Admin View)-Add/Edit User:</h2>
			<%
				} else {
					updateUserType = 2;
			%>
			<h2>
				Edit Your Profile (Admin User):
				<%=login_name%></h2>
			<%
				}
			%>
			<%
				} else if (userType == 1) {
						if (current_id.equals(user_id)) {
			%>
			<h2>
				Edit Your Profile:
				<%=login_name%></h2>
			<%
				}

						else {
							String site = new String("home");
							response.setStatus(response.SC_MOVED_TEMPORARILY);
							response.setHeader("Location", site);
						}
					} else {
						String site = new String("home");
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location", site);
					}
				} else {
			%>
			<h2>(Admin View)-Add User:</h2>
			<%
				}
			%>
		</div>
	</div>
	<%
		if (loginoutHistory != null) {
	%>

	<i>Last Login: <%=loginout_time%></i>
	<%
		}
	%>

	<div id="container">
		<form:form action="save" modelAttribute="user" method="POST">
			<!-- associate this data with user id -->
			<form:hidden path="id" />
			<table>

				<tbody>
					<tr>
						<td><label>Name (*):</label></td>
						<td><form:input path="name" /> <form:errors path="name"
								cssClass="error" /></td>
					</tr>

					<tr>
						<td><label>Password (*):</label></td>
						<td><form:input path="password" /> <form:errors
								path="password" cssClass="error" /></td>
					</tr>

					<tr>
						<td><label>Contact Number (*):</label></td>
						<td><form:input path="contact" /> <form:errors
								path="contact" cssClass="error" /></td>
					</tr>

					<tr>
						<td><label>Email (*):</label></td>
						<td><form:input path="email" /> <form:errors path="email"
								cssClass="error" /></td>
					</tr>

					<tr>
						<td><label>Gender (*):</label></td>
						<td><form:input path="gender" /> <form:errors path="gender"
								cssClass="error" /></td>
					</tr>

					<tr>
						<td><label>Date of Birth:</label></td>
						<td><form:input path="dob" /> <form:errors path="dob"
								cssClass="error" /></td>
					</tr>

					<tr>
						<td><label>Country:</label></td>
						<td><form:input path="country" /></td>
					</tr>

					<tr>
						<td><label>City:</label></td>
						<td><form:input path="city" /></td>
					</tr>

					<tr>
						<td><label>Postal Code:</label></td>
						<td><form:input path="postalcode" /></td>
					</tr>


					<tr>
						<td><label></label></td>

						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				</tbody>

			</table>

			<%
				if (updateUserType == 1) {
			%>
			<form:hidden path="type" value="user" />
			<%
				} else if (updateUserType == 2)  {
			%>
			<form:hidden path="type" value="admin" />
			<%
					}
					
			%>


		</form:form>

		<div style=""></div>

	</div>

	<p>
	<footer class="footer">
		<div class="container">
			<div align="center">
				<%@include file='footer.html'%></div>
		</div>
	</footer>
</body>

</html>