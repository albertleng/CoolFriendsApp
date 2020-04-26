package com.coolfriend.springboot.coolfriendsapp.restservice;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coolfriend.springboot.coolfriendsapp.entity.Comment;
import com.coolfriend.springboot.coolfriendsapp.entity.Like;


@Service
public class CommentRestServiceImpl implements CommentRestService {
	
	private RestTemplate restTemplate;

	private String commentRestUrl;

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	public CommentRestServiceImpl(RestTemplate theRestTemplate, @Value("${coolfriends.rest.comment}") String theUrl) {
		restTemplate = theRestTemplate;
		commentRestUrl = theUrl;

		logger.info("Loaded property:  coolfriends.rest.comment=" + commentRestUrl);
	}
	
	@Override
	public List<Comment> getComments() {
		logger.info("in getComments(): Calling REST API " + commentRestUrl);

		// make REST call
		ResponseEntity<List<Comment>> responseEntity = restTemplate.exchange(commentRestUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Comment>>() {
				});

		// get the list of comments from response
		List<Comment> comments = responseEntity.getBody();

		logger.info("in getComments(): comments" + comments);

		return comments;
	}

	@Override
	public void saveComment(Comment comment) {
		logger.info("in saveComment(): Calling REST API " + commentRestUrl);

		// add Like 
		restTemplate.postForEntity(commentRestUrl, comment, String.class);

		logger.info("in saveComment(): success");		
	}

	@Override
	public List<Comment> getComments(int commentUserId) {
		logger.info("in getComments(): Calling REST API " + commentRestUrl + "/comment/" + commentUserId);

		// make REST call
		ResponseEntity<List<Comment>> responseEntity = restTemplate.exchange(commentRestUrl + "/comment/" + commentUserId, 
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Comment>>() {
				});

		// get the list of comments from response
		List<Comment> comments = responseEntity.getBody();

		logger.info("in getComments(): comments" + comments);

		return comments;
	}

	@Override
	public List<Comment> getCommenteds(int commentedUserId) {
		logger.info("in getCommenteds(): Calling REST API " + commentRestUrl + "/commented/" + commentedUserId);

		// make REST call
		ResponseEntity<List<Comment>> responseEntity = restTemplate.exchange(commentRestUrl + "/commented/" + commentedUserId, 
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Comment>>() {
				});

		// get the list of comments from response
		List<Comment> commenteds = responseEntity.getBody();

		logger.info("in getCommenteds(): commenteds" + commenteds);
		
		return commenteds;
	}

	@Override
	public List<Comment> getCommentAndCommenteds(int commentUserId, int commentedUserId) {
		logger.info("in getCommentAndCommenteds(): Calling REST API " + commentRestUrl + "/search/" + commentUserId + "/" + commentedUserId);

		// make REST call
		ResponseEntity<List<Comment>> responseEntity = restTemplate.exchange(commentRestUrl + "/search/" + commentUserId + "/" + commentedUserId, 
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Comment>>() {
				});

		// get the list of comment and commenteds from response
		List<Comment> commentAndCommenteds = responseEntity.getBody();

		logger.info("in getCommentAndCommenteds(): commentAndCommenteds" + commentAndCommenteds);
		
		return commentAndCommenteds;
	}

}
