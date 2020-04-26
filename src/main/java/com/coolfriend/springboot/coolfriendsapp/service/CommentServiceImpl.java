package com.coolfriend.springboot.coolfriendsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolfriend.springboot.coolfriendsapp.dao.CommentDAO;
import com.coolfriend.springboot.coolfriendsapp.entity.Comment;



@Service("commentServiceImpl")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDAO commentDAO;

	@Override
	@Transactional
	public List<Comment> getComments() {

		return commentDAO.getComments();
	}

	@Override
	@Transactional
	public void saveComment(Comment comment) {
		commentDAO.saveComment(comment);
	}

	@Override
	@Transactional
	public List<Comment> getComments(int commentUserId) {
		return commentDAO.getComments(commentUserId);
	}

	@Override
	@Transactional
	public List<Comment> getCommenteds(int commentedUserId) {
		return commentDAO.getCommenteds(commentedUserId);
	}
	
	@Override
	@Transactional
	public List<Comment> getCommentAndCommenteds(int commentUserId, int commentedUserId) {
		return commentDAO.getCommentAndCommenteds(commentUserId, commentedUserId);
	}

	

}
