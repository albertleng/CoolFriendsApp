<%@ page import="com.coolfriend.springboot.coolfriendsapp.entity.User"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>

<head>
<title>List Friends</title>

</head>
<body>
	<%@include file='navbar.jsp'%>
	<%
		List<User> users = (List<User>) request.getAttribute("users");
		String adminView = "";
		if (userType == 2) {
			adminView = "(Admin View)";
		}
	%>
	<div id="wrapper">
		<div id="header" align="center">
			<h2>
				Cool Friends are here...<%=adminView%></h2>
		</div>
	</div>
	<div id="container">
		<div id="content">

			<% if (userType == 2) { %>
			<input type="button" value="Add User"
				onclick="window.location.href='add'; return false;"
				class="add-button" />
			<% } %>
			
			<% if (userType == 1 || userType == 2) { %>
		   <!--  add a search box -->
            <form:form action="search" method="POST">
                Search Friend: <input type="text" name="theSearchName" size=35
                placeholder="Search name, gender, country or city..."/>
                <input type="submit" value="Search" class="add-button" />
            </form:form>
			<% } %>
			<i>Click on name to view profile (only for registered user)</i>
			<table>
				<tr>
					<th>Name</th>
					<th>Gender</th>
					<% if (userType == 2) { %>
					<th>Action</th>
					<% } %>
				</tr>


				<%
					for (User user : users) {
						String name = user.getName();
						String gender = user.getGender();
						
				
						
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						String dob = "1999-02-11";

						String id    = Integer.toString(user.getId());
						String updateLink = "update?id=" + id;
						String deleteLink = "delete?id=" + id;
						String profileLink = "profile?id=" + id;
						String loginHistoryLink = "loginhistory?id=" + id;
						String type = user.getType();
						
						
						boolean showUser = true;
						
						if (user_id != null) {
							if (id.equals(user_id)) {
								showUser = false;
							}
						}
						//Only list users/friends of "user" type.
						//if (type == "user") {
							
						if (type.equals("user") && showUser) {
				%>
				<tr>
					<%if (userType == 0)  {%>
					<td><%=name%></td>
					<%} else { %>
					<td><a href="<%=profileLink%>"><%=name%></a></td>
					<%} %>

					<td><%=gender%></td>

					<% if (userType == 2) { %>
					<td><a href="<%=updateLink%>">Update</a> | <a
						href="<%=deleteLink%>"
						onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
						| <a href= "<%=loginHistoryLink%>">View History</a>
					</td>

					<% } %>

				</tr>
				<%
					}
					}
				%>
			</table>
		</div>
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