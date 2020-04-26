<%@ page import="com.coolfriend.springboot.coolfriendsapp.entity.User"%>
<%@ page import="com.coolfriend.springboot.coolfriendsapp.entity.Like"%>
<%@ page import="com.coolfriend.springboot.coolfriendsapp.entity.Comment"%>
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
	if (userType == 0) {
	 String site = new String("home");
     response.setStatus(response.SC_MOVED_TEMPORARILY);
     response.setHeader("Location", site); 
	}

%>
	<%
	
		User theUser = (User) request.getAttribute("theUser");
		List<Like> theLikes = (List<Like>) request.getAttribute("theLikes");
		int numLikes = theLikes.size();
		
		
		String adminView = "";
		if (userType == 2) {
			adminView = "(Admin View)";
		}
	%>
	<div id="wrapper">
		<div id="header" align="center">
			<h2>
				Your Inbox<%=adminView%> (<%=numLikes%> likes)</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">

			<b>Likes</b>
			<table>
				<tr>
					<th>Date/Time</th>
					<th>Sender</th>
					<th>Message</th>
				</tr>


				<%
					for (Like like : theLikes) {
						
						
						DateFormat df      = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						String dateTime    = df.format(like.getDateTime());
						int likeId         = like.getLikeUserId();
						String profileLink = "profile?id=" + likeId;
						String senderName  = like.getLikeName();
						String message     = senderName + " likes you!";
						boolean read       = like.getReadStatus();
				%>
				<tr>
					
					<td><%=dateTime%></td>
					<td><a href="<%=profileLink%>"><%=senderName%></a></td>
					<td><%=message%></td>

				

				</tr>
				<%
					}

				%>
			</table>
	
		<% 		
			List<Comment> theComments = (List<Comment>) request.getAttribute("theComments");
			int numComments = theComments.size();
			
			if (numComments > 0) {
		%>	
		<br>
		<b>Comments</b>
		<table>
				<tr>
					<th>Date/Time</th>
					<th>Sender</th>
					<th>Message</th>
				</tr>
				
					<%
					for (Comment comment : theComments) {
						
						
						DateFormat df      = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						String dateTime    = df.format(comment.getDateTime());
						int commentUserId  = comment.getCommentUserId();
						String profileLink = "profile?id=" + commentUserId;
						String senderName  = comment.getCommentName();
						String message     = comment.getMessage();
						boolean read       = comment.getReadStatus();
				%>
				<tr>					
					<td><%=dateTime%></td>
					<td><a href="<%=profileLink%>"><%=senderName%></a></td>
					<td><%=message%></td>

				</tr>
				<%} %>
				</table>
		
		<% } else { %>
		<i> You have 0 comments on your profile.</i>
		<% } %>
			
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