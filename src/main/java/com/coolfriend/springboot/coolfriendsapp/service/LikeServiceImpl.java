package com.coolfriend.springboot.coolfriendsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolfriend.springboot.coolfriendsapp.dao.LikeDAO;
import com.coolfriend.springboot.coolfriendsapp.dao.LoginoutHistoryDAO;
import com.coolfriend.springboot.coolfriendsapp.entity.Like;
import com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory;



@Service("likeServiceImpl")
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	private LikeDAO likeDAO;

	@Override
	@Transactional
	public List<Like> getLikes() {

		return likeDAO.getLikes();
	}

	@Override
	@Transactional
	public void saveLike(Like like) {
		likeDAO.saveLike(like);
		
	}

	@Override
	@Transactional
	public List<Like> getLike(int likeUserId) {
		return likeDAO.getLike(likeUserId);
	}

	@Override
	@Transactional
	public List<Like> getLiked(int likedUserId) {
		return likeDAO.getLiked(likedUserId);
	}
	
	@Override
	@Transactional
	public List<Like> getLikeAndLiked(int likeUserId, int likedUserId) {
		return likeDAO.getLikeAndLiked(likeUserId, likedUserId);
	}

	

}
