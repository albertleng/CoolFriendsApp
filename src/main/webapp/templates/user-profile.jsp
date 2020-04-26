<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.coolfriend.springboot.coolfriendsapp.entity.User"%>
<%@ page import="com.coolfriend.springboot.coolfriendsapp.entity.Like"%>
<%@ page
	import="com.coolfriend.springboot.coolfriendsapp.entity.Comment"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<%@include file='navbar.jsp'%>
<title>User Profile</title>

<%
	User user = (User) request.getAttribute("theUser");
	String contact = user.getContact();
	String name = user.getName();
	String country = user.getCountry();
	String city = user.getCity();
	String email = user.getEmail();
	int theUserid = user.getId();
	String postal = user.getPostalcode();
	String gender = user.getGender();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	String dob = df.format(user.getDob());

	List<Like> theLikeds = (List<Like>) request.getAttribute("theLikeds");
	List<Like> theLikeAndLikeds = (List<Like>) request.getAttribute("theLikeAndLikeds");

	int numLikeds = 0;
	int numLikeAndLikeds = 0;

	if (theLikeds != null) {
		numLikeds = theLikeds.size();
	}

	if (theLikeAndLikeds != null) {
		numLikeAndLikeds = theLikeAndLikeds.size();
	}
%>

</head>

<body>

	<%
		if (userType == 0) {
			String site = new String("home");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}
	%>

	<h2><%=name%>'s Profile
	</h2>


	<form action="like" method="post">
		<input type="submit" name="like" value="Like" />

		<!--  Display message based on whether the logged on user has liked him -->
		<i>You have liked him <%=numLikeAndLikeds%> times! Show <%=name%>
			love by LIKING!
		</i>


		<!-- hidden: current user and liked user -->
		<input type="hidden" name="likedId" value=<%=theUserid%>> <input
			type="hidden" name="likeId" value=<%=user_id%>>
	</form>


	<p>
		<!-- Show number of total likes -->
		<%=name%>
		has
		<%=numLikeds%>
		likes!
	<p>
	<div id="container">

		<table>

			<tbody>
				<tr>
					<td><label>Name:</label></td>
					<td><%=name%></td>
				</tr>

				<tr>
					<td><label>Contact Number:</label></td>
					<td><%=contact%></td>
				</tr>

				<tr>
					<td><label>Email:</label></td>
					<td><%=email%></td>
				</tr>

				<tr>
					<td><label>Gender:</label></td>
					<td><%=gender%></td>
				</tr>

				<tr>
					<td><label>Date of Birth:</label></td>
					<td><%=dob%></td>
				</tr>

				<tr>
					<td><label>Country:</label></td>
					<td><%=country%></td>
				</tr>

				<tr>
					<td><label>City:</label></td>
					<td><%=city%></td>
				</tr>

				<tr>
					<td><label>Postal Code:</label></td>
					<td><%=postal%></td>
				</tr>



			</tbody>

		</table>



		<div style=""></div>

	</div>

	<p>
		<form:form action="comment" method="POST">
                Comments on Profile: <input type="text" name="message" placeholder="Write some awesome words..."
				maxlength="200" size="100" />
			<input type="submit" value="Send" class="add-button" />
			<!-- hidden: current user and commented user -->
			<input type="hidden" name="commentedId" value=<%=theUserid%>>
			<input type="hidden" name="commentId" value=<%=user_id%>>
		</form:form>
<br>
		<%
	List<Comment> comments = (List<Comment>)request.getAttribute("theCommenteds");
	if (comments.size() > 0 && comments != null) {
%>
		Comments for
		<%=name%>
	<p>
	<table>
		<tr>
			<th>Date/Time</th>
			<th>Sender</th>
			<th>Message</th>
		</tr>

		<%
					for (Comment comment : comments) { 
					
						DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						String timeSent = df2.format(comment.getDateTime());
						
						String sender = comment.getCommentName();
						int commentUserId = comment.getCommentUserId();
						String senderLink = "profile?id=" + commentUserId;
						String message = comment.getMessage();
					%>
		<tr>
			<td><%=timeSent%></td>
			
			
			<% if (Integer.parseInt(user_id) == commentUserId) { %>
			<td><%=sender%></td>
			<% } else { %>
			<td><a href="<%=senderLink%>"><%=sender%></a></td>
			<% } %>
			<td><%=message%></td>

		</tr>

		<% } %>

	</table>


	<%} %>

	<footer class="footer">
		<div class="container">
			<div align="center">
				<%@include file='footer.html'%>
			</div>
		</div>
	</footer>
</body>

</html>