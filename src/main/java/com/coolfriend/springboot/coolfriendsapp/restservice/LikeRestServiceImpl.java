package com.coolfriend.springboot.coolfriendsapp.restservice;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.coolfriend.springboot.coolfriendsapp.entity.Like;
import com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory;


@Service
public class LikeRestServiceImpl implements LikeRestService {
	
	private RestTemplate restTemplate;

	private String likeRestUrl;

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public LikeRestServiceImpl(RestTemplate theRestTemplate, @Value("${coolfriends.rest.like}") String theUrl) {
		restTemplate = theRestTemplate;
		likeRestUrl = theUrl;

		logger.info("Loaded property:  coolfriends.rest.like=" + likeRestUrl);
	}
	
	@Override
	public List<Like> getLikes() {
		logger.info("in getLikes(): Calling REST API " + likeRestUrl);

		// make REST call
		ResponseEntity<List<Like>> responseEntity = restTemplate.exchange(likeRestUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Like>>() {
				});

		// get the list of likes from response
		List<Like> likes = responseEntity.getBody();

		logger.info("in getLikes(): likes" + likes);

		return likes;
	}

	@Override
	public void saveLike(Like like) {
		logger.info("in saveLike(): Calling REST API " + likeRestUrl);

		// add Like 
		restTemplate.postForEntity(likeRestUrl, like, String.class);

		logger.info("in saveLike(): success");		
	}

	@Override
	public List<Like> getLike(int likeUserId) {
		logger.info("in getLike(): Calling REST API " + likeRestUrl + "/like/" + likeUserId);

		// make REST call
		ResponseEntity<List<Like>> responseEntity = restTemplate.exchange(likeRestUrl + "/like/" + likeUserId, 
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Like>>() {
				});

		// get the list of likes from response
		List<Like> likes = responseEntity.getBody();

		logger.info("in getLike(): like" + likes);

		return likes;
	}

	@Override
	public List<Like> getLiked(int likedUserId) {
		logger.info("in getLike(): Calling REST API " + likeRestUrl + "/liked/" + likedUserId);

		// make REST call
		ResponseEntity<List<Like>> responseEntity = restTemplate.exchange(likeRestUrl + "/liked/" + likedUserId, 
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Like>>() {
				});

		// get the list of likes from response
		List<Like> likeds = responseEntity.getBody();

		logger.info("in getLiked(): liked" + likeds);
		
		return likeds;
	}

	@Override
	public List<Like> getLikeAndLiked(int likeUserId, int likedUserId) {
		logger.info("in getLikeAndLiked(): Calling REST API " + likeRestUrl + "/search/" + likeUserId + "/" + likedUserId);

		// make REST call
		ResponseEntity<List<Like>> responseEntity = restTemplate.exchange(likeRestUrl + "/search/" + likeUserId + "/" + likedUserId, 
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Like>>() {
				});

		// get the list of like and likeds from response
		List<Like> likeAndLikeds = responseEntity.getBody();

		logger.info("in getLikeAndLiked(): likeAndLikeds" + likeAndLikeds);
		
		return likeAndLikeds;
	}

}
