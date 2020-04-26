package com.coolfriend.springboot.coolfriendsapp.dao;

import java.util.List;

import com.coolfriend.springboot.coolfriendsapp.entity.Like;

public interface LikeDAO {
	public List<Like> getLikes();

	public void saveLike(Like like);
	
	public List<Like> getLike(int likeUserId);
	
	public List<Like> getLiked(int likedUserId);
	
	public List<Like> getLikeAndLiked(int likeUserId, int likedUserId);
}
