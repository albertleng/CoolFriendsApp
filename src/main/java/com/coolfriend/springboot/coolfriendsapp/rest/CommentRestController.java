package com.coolfriend.springboot.coolfriendsapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coolfriend.springboot.coolfriendsapp.entity.Comment;
import com.coolfriend.springboot.coolfriendsapp.entity.Like;
import com.coolfriend.springboot.coolfriendsapp.service.CommentService;



@RestController
@RequestMapping("/api")
public class CommentRestController {
    
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/comment")
	public List<Comment> getComments() {
		return commentService.getComments();
	}
	
	@GetMapping("/comment/comment/{commentUserId}")
	public List<Comment> getComments(@PathVariable int commentUserId) {
		System.out.println("CommentRestController, getComments: " + commentUserId);
		
		
		
		if (commentUserId < 0) {
			System.out.println("commentUserId is invalid");
		}
		else {
			System.out.println("commentUserId is valid, :" + commentUserId);		
		}
		return commentService.getComments(commentUserId);
	}
	
	@GetMapping("/comment/commented/{commentedUserId}")
	public List<Comment> getCommenteds(@PathVariable int commentedUserId) {
		System.out.println("CommentRestController, getCommenteds: " + commentedUserId);
		
		
		
		if (commentedUserId < 0) {
			System.out.println("commentedUserId is invalid");
		}
		else {
			System.out.println("commentedUserId is valid, :" + commentedUserId);		
		}
		return commentService.getCommenteds(commentedUserId);
	}
	
	@GetMapping("/comment/search/{commentId}/{commentedId}")
	public List<Comment> getCommentAndCommented(@PathVariable int commentId, @PathVariable int commentedId) {
		System.out.println("CommentRestController, getCommentAndCommented: " + commentId + ", " + commentedId);
		
		if (commentId < 0 || commentedId < 0) {
			System.out.println("commentId or commentedId is invalid");
		}
		else {
			System.out.println("commentId and commentIdedId are valid, :" + commentId + ", " + commentedId);		
		}
		return commentService.getCommentAndCommenteds(commentId, commentedId);
	}
	
	// add mapping for POST /comment - add new comment
	@PostMapping("/comment")
	public Comment addComment(@RequestBody Comment theComment) {
		//Set 0 for Insert, otherwise, Hibernate will update.
		theComment.setId(0);
		commentService.saveComment(theComment);
		return theComment;
		
	}
	
	
	

}
