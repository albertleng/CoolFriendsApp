<%@ page
	import="com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory"%>
<%@ page import="com.coolfriend.springboot.coolfriendsapp.entity.User"%>

<%@ page import="java.util.List"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>

<head>
<title>List Friends</title>

</head>
<body>
	<%@include file='navbar.jsp'%>
	<%
		List<LoginoutHistory> histories = (List<LoginoutHistory>) request.getAttribute("histories");
		User theUser = (User) request.getAttribute("user");
		String userName = theUser.getName();
		String adminView = "";
		if (userType == 2) {
			
			
			
	%>
	<div id="wrapper">
		<div id="header" align="center">
			<h2>
				Login History for
				<%=userName%>
				(Admin View)
			</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
				<%  if (histories.size() == 0) { %>
					<h3>No Login History Found</h3>	
				<% } else {%>
			
			<table>
				<tr>
					<th>Time</th>
					<th>IP Address</th>
					<th>Event</th>

				</tr>
				
			

				<%
					
					for (LoginoutHistory history : histories) {
						
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:SS");
						String time   = df.format(history.getDateTime());
						
						String ipAddress = history.getIPAddress();
						String event     = history.getEventType();
				%>
				<tr>
					<td><%=time%></td>

					<td><%=ipAddress%></td>

					<td><%=event%></td>



				</tr>
				<% } %>
			</table>
			<% } %>
		</div>
	</div>
	
	
	
	
	<% }
		else { %>
	<h2>Access Denied</h2>
	<% } %>
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