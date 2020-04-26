package com.coolfriend.springboot.coolfriendsapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coolfriend.springboot.coolfriendsapp.entity.Like;
import com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory;



@Repository
public class LikeDAOImpl implements LikeDAO {

	private EntityManager entityManager;

	@Autowired
	public LikeDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Like> getLikes() {
		Query theQuery =
				entityManager.createQuery("from Like");
		
		//execute query and get result list
		List<Like> likes = theQuery.getResultList();

		// return the results
		return likes;
	}

	@Override
	public void saveLike(Like theLike) {
		//save or update the employee
		Like dbLike = entityManager.merge(theLike);
		
		// update with id from db... so we can get generated id for save/insert
		theLike.setId(dbLike.getId());
		
	}

	@Override
	public List<Like> getLike(int likeUserId) {
		List<Like> likes;

		Query theQuery = entityManager.createQuery("FROM Like u WHERE u.likeUserId=:user_id "
				+ "ORDER BY dateTime DESC");
		
		theQuery.setParameter("user_id", likeUserId);
		
		try {

			likes = (List<Like>)theQuery.getResultList();

		} catch (Exception ex) {
			System.out.println("getLike: " + ex.getMessage());
			likes = null;
		}
		return likes;
	}

	@Override
	public List<Like> getLiked(int likedUserId) {
		List<Like> likeds;

		Query theQuery = entityManager.createQuery("FROM Like u WHERE u.likedUserId=:user_id "
				+ "ORDER BY dateTime DESC");
		
		theQuery.setParameter("user_id", likedUserId);
		
		try {

			likeds = (List<Like>)theQuery.getResultList();

		} catch (Exception ex) {
			System.out.println("getLiked: " + ex.getMessage());
			likeds = null;
		}
		return likeds;
	}

	@Override
	public List<Like> getLikeAndLiked(int likeUserId, int likedUserId) {
		List<Like> likeAndLikeds;

		Query theQuery = entityManager.createQuery("FROM Like u WHERE u.likedUserId=:liked_user_id  AND "
				+ " u.likeUserId=:like_user_id ORDER BY dateTime DESC");
		
		theQuery.setParameter("liked_user_id", likedUserId);
		theQuery.setParameter("like_user_id" , likeUserId);
		
		try {

			likeAndLikeds = (List<Like>)theQuery.getResultList();

		} catch (Exception ex) {
			System.out.println("getLikeAndLiked: " + ex.getMessage());
			likeAndLikeds = null;
		}
		return likeAndLikeds;
	}
	
	

}
