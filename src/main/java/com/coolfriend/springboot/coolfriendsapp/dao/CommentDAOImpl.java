package com.coolfriend.springboot.coolfriendsapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.coolfriend.springboot.coolfriendsapp.entity.Comment;
import com.coolfriend.springboot.coolfriendsapp.entity.Like;



@Repository
public class CommentDAOImpl implements CommentDAO {

	private EntityManager entityManager;

	@Autowired
	public CommentDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Comment> getComments() {
		Query theQuery =
				entityManager.createQuery("from Comment");
		
		//execute query and get result list
		List<Comment> comments = theQuery.getResultList();

		// return the results
		return comments;
	}

	@Override
	public void saveComment(Comment theComment) {
		//save or update the comment
		Comment dbComment = entityManager.merge(theComment);
		
		// update with id from db... so we can get generated id for save/insert
		theComment.setId(dbComment.getId());
		
	}

	@Override
	public List<Comment> getComments(int commentUserId) {
		List<Comment> comments;

		Query theQuery = entityManager.createQuery("FROM Comment u WHERE u.commentUserId=:user_id "
				+ "ORDER BY dateTime DESC");
		
		theQuery.setParameter("user_id", commentUserId);
		
		try {

			comments = (List<Comment>)theQuery.getResultList();

		} catch (Exception ex) {
			System.out.println("getComments: " + ex.getMessage());
			comments = null;
		}
		return comments;
	}

	@Override
	public List<Comment> getCommenteds(int commentedUserId) {
		List<Comment> commenteds;

		Query theQuery = entityManager.createQuery("FROM Comment u WHERE u.commentedUserId=:user_id "
				+ "ORDER BY dateTime DESC");
		
		theQuery.setParameter("user_id", commentedUserId);
		
		try {

			commenteds = (List<Comment>)theQuery.getResultList();

		} catch (Exception ex) {
			System.out.println("getCommenteds: " + ex.getMessage());
			commenteds = null;
		}
		return commenteds;
	}

	@Override
	public List<Comment> getCommentAndCommenteds(int commentUserId, int commentedUserId) {
		List<Comment> commentAndCommenteds;

		Query theQuery = entityManager.createQuery("FROM Comment u WHERE u.commentedUserId=:commented_user_id  AND "
				+ " u.commentUserId=:commented_user_id ORDER BY dateTime DESC");
		
		theQuery.setParameter("commented_user_id", commentedUserId);
		theQuery.setParameter("comment_user_id" , commentUserId);
		
		try {

			commentAndCommenteds = (List<Comment>)theQuery.getResultList();

		} catch (Exception ex) {
			System.out.println("getCommentAndCommenteds: " + ex.getMessage());
			commentAndCommenteds = null;
		}
		return commentAndCommenteds;
	}
	
	

}
