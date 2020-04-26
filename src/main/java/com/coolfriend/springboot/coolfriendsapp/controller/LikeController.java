package com.coolfriend.springboot.coolfriendsapp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coolfriend.springboot.coolfriendsapp.entity.Comment;
import com.coolfriend.springboot.coolfriendsapp.entity.Like;
import com.coolfriend.springboot.coolfriendsapp.entity.User;
import com.coolfriend.springboot.coolfriendsapp.restservice.CommentRestService;
import com.coolfriend.springboot.coolfriendsapp.restservice.LikeRestService;
import com.coolfriend.springboot.coolfriendsapp.restservice.UserRestService;

@Controller
public class LikeController {

	@Autowired
	@Qualifier("userRestServiceImpl")
	private UserRestService userRestService;

	@Autowired
	@Qualifier("likeRestServiceImpl")
	private LikeRestService likeRestService;

	@Autowired
	@Qualifier("commentRestServiceImpl")
	private CommentRestService commentRestService;

	@PostMapping("/like")
	public String Like(@RequestParam("likeId") int likeId, @RequestParam("likedId") int likedId, Model theModel) {
		User theLikedUser = userRestService.getUser(likedId);
		User theLikeUser = userRestService.getUser(likeId);

		// Also post user id and return user

		System.out.println("Like, likeId:" + likeId + ", likedId:" + likedId + ", theLikedUser:" + theLikedUser
				+ ", theLikeUser:" + theLikeUser);

		Like theLike = new Like();

		System.out.println("Like, like.setLikedUserId: " + likedId);
		theLike.setLikedUserId(likedId);
		System.out.println("Like, like.setLikedName: " + theLikedUser.getName());
		theLike.setLikedName(theLikedUser.getName());
		
		System.out.println("Like, like.setLikeUserId: " + likeId);
		theLike.setLikeUserId(likeId);
		System.out.println("Like, like.setLikeName: " + theLikeUser.getName());
		theLike.setLikeName(theLikeUser.getName());
		
		Date currentTime = new Date();
		System.out.println("Like, like.setDateTime: " + currentTime.toString());
		theLike.setDateTime(currentTime);

		// Insert into like table
		likeRestService.saveLike(theLike);

		// Get total liked of likedId
		List<Like> theLikeds = likeRestService.getLiked(likedId);
		List<Like> theLikeAndLikeds = likeRestService.getLikeAndLiked(likeId, likedId);
		// Get list of comments
		List<Comment> theCommenteds = commentRestService.getCommenteds(likedId);

		
		theModel.addAttribute("theUser", theLikedUser);
		theModel.addAttribute("theLikeds", theLikeds);
		theModel.addAttribute("theLikeAndLikeds", theLikeAndLikeds);
		theModel.addAttribute("theCommenteds", theCommenteds);

		
		return "user-profile";
	}

	@PostMapping("/comment")
	public String Comment(@RequestParam("commentId") int commentId, @RequestParam("commentedId") int commentedId,
			@RequestParam("message") String message, Model theModel) {
		User theCommentedUser = userRestService.getUser(commentedId);
		User theCommentUser = userRestService.getUser(commentId);

		// Also post user id and return user

		System.out.println("Comment, likeId:" + commentId + ", likedId:" + commentedId + ", theCommentedUser:"
				+ theCommentedUser + ", theCommentUser:" + theCommentUser);

		Comment theComment = new Comment();

		if (message.trim().length() > 0 && message != null) {
			System.out.println("Comment, comment.setCommentedUserId: " + commentedId);
			theComment.setCommentedUserId(commentedId);
			System.out.println("Comment, comment.setCommentedUserName: " + theCommentedUser.getName());
			theComment.setCommentedName(theCommentedUser.getName());
			;

			System.out.println("Comment, comment.setCommentUserId: " + commentId);
			theComment.setCommentUserId(commentId);
			System.out.println("Comment, comment.setCommentUserName: " + theCommentUser.getName());
			theComment.setCommentName(theCommentUser.getName());
			
			System.out.println("Comment, comment.setCommentMessage: " + message);
			theComment.setMessage(message);
			
			Date currentTime = new Date();
			System.out.println("Comment, comment.setDateTime: " + currentTime.toString());
			theComment.setDateTime(currentTime);

			commentRestService.saveComment(theComment);

		}
		// Get total liked of likedId
		List<Like> theLikeds = likeRestService.getLiked(commentedId);
		List<Like> theLikeAndLikeds = likeRestService.getLikeAndLiked(commentId, commentedId);

		// Get list of comments
		List<Comment> theCommenteds = commentRestService.getCommenteds(commentedId);

		theModel.addAttribute("theUser", theCommentedUser);
		theModel.addAttribute("theLikeds", theLikeds);
		theModel.addAttribute("theLikeAndLikeds", theLikeAndLikeds);
		theModel.addAttribute("theCommenteds", theCommenteds);

		
		return "user-profile";
	}

}
