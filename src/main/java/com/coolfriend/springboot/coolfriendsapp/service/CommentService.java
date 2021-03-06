package com.coolfriend.springboot.coolfriendsapp.service;

import java.util.List;

import com.coolfriend.springboot.coolfriendsapp.entity.Comment;


public interface CommentService {
	public List<Comment> getComments();

	public void saveComment(Comment comment);
	
	public List<Comment> getComments(int commentUserId);
	
	public List<Comment> getCommenteds(int commentedUserId);
	
	public List<Comment> getCommentAndCommenteds(int commentUserId, int commentedUserId);
}
