package com.coolfriend.springboot.coolfriendsapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coolfriend.springboot.coolfriendsapp.entity.Like;
import com.coolfriend.springboot.coolfriendsapp.entity.Login;
import com.coolfriend.springboot.coolfriendsapp.entity.User;
import com.coolfriend.springboot.coolfriendsapp.service.LikeService;



@RestController
@RequestMapping("/api")
public class LikeRestController {
    
	@Autowired
	private LikeService likeService;
	
	@GetMapping("/like")
	public List<Like> getLikes() {
		return likeService.getLikes();
	}
	
	@GetMapping("/like/like/{likeId}")
	public List<Like> getLike(@PathVariable int likeId) {
		System.out.println("LikeRestController, getLike: " + likeId);
		
		
		
		if (likeId < 0) {
			System.out.println("likeId is invalid");
		}
		else {
			System.out.println("likeId is valid, :" + likeId);		
		}
		return likeService.getLike(likeId);
	}
	
	@GetMapping("/like/liked/{likedId}")
	public List<Like> getLiked(@PathVariable int likedId) {
		System.out.println("LikeRestController, getLiked: " + likedId);
		
		
		
		if (likedId < 0) {
			System.out.println("likedId is invalid");
		}
		else {
			System.out.println("likedId is valid, :" + likedId);		
		}
		return likeService.getLiked(likedId);
	}
	
	@GetMapping("/like/search/{likeId}/{likedId}")
	public List<Like> getLikeAndLiked(@PathVariable int likeId, @PathVariable int likedId) {
		System.out.println("LikeRestController, getLikeAndLiked: " + likeId + ", " + likedId);
		
		if (likeId < 0 || likedId < 0) {
			System.out.println("likeId or likedId is invalid");
		}
		else {
			System.out.println("likeId and likedId are valid, :" + likeId + ", " + likedId);		
		}
		return likeService.getLikeAndLiked(likeId, likedId);
	}
	
	// add mapping for POST /like - add new like
	@PostMapping("/like")
	public Like addLike(@RequestBody Like theLike) {
		//Set 0 for Insert, otherwise, Hibernate will update.
		theLike.setId(0);
		likeService.saveLike(theLike);
		return theLike;
		
	}
	
	
	

}
