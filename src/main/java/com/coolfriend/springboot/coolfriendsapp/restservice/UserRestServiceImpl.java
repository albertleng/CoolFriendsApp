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

import com.coolfriend.springboot.coolfriendsapp.entity.Login;
import com.coolfriend.springboot.coolfriendsapp.entity.User;

@Service
public class UserRestServiceImpl implements UserRestService {

	private RestTemplate restTemplate;

	private String userRestUrl;
	private String searchRestUrl;

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	public UserRestServiceImpl(RestTemplate theRestTemplate, @Value("${coolfriends.rest.users}") String userUrl,
			@Value("${coolfriends.rest.search}") String searchUrl) {
		restTemplate = theRestTemplate;
		userRestUrl   = userUrl;
		searchRestUrl = searchUrl;
		

		logger.info("Loaded property:  coolfriends.rest.users=" + userRestUrl + ", coolfriends.rest.search=" + searchRestUrl);
	}

	@Override
	public List<User> getUsers() {

		logger.info("in getUsers(): Calling REST API " + userRestUrl);

		// make REST call
		ResponseEntity<List<User>> responseEntity = restTemplate.exchange(userRestUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});

		// get the list of users from response
		List<User> users = responseEntity.getBody();

		logger.info("in getUsers(): users" + users);

		return users;
	}
	
	@Override
	public List<User> searchUsers(String searchName) {
		String url = searchRestUrl + "/" + searchName;
		logger.info("in searchUsers(): before setting url");


		if (searchName == null) {
			url = searchRestUrl;
		}
		logger.info("in searchUsers(): Calling REST API: " + url);

		

		// make REST call
		ResponseEntity<List<User>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});

		// get the list of users from response
		List<User> users = responseEntity.getBody();

		logger.info("in searchUsers(): users" + users);

		return users;
	}

	@Override
	public void saveUser(User theUser) {
		logger.info("in saveUser(): Calling REST API " + userRestUrl);

		int userId = theUser.getId();

		// make REST call
		if (userId == 0) {
			// add user
			restTemplate.postForEntity(userRestUrl, theUser, String.class);

		} else {
			// update user
			restTemplate.put(userRestUrl, theUser);
		}

		logger.info("in saveUser(): success");
	}

	@Override
	public User getUser(int theId) {
		logger.info("in getUser(): Calling REST API " + userRestUrl + "/" + theId);

		// make REST call
		User theUser = restTemplate.getForObject(userRestUrl + "/" + theId, User.class);

		logger.info("in saveUser(): theUser=" + theUser);

		return theUser;
	}

	@Override
	public void deleteUser(int theId) {
		logger.info("in deleteUser(): Calling REST API " + userRestUrl + "/" + theId);

		// make REST call
		restTemplate.delete(userRestUrl + "/" + theId);

		logger.info("in deleteUser(): deleted user theId=" + theId);
	}

	@Override
	public User validateUser(Login login) {
		logger.info("in validateUser(): Calling REST API " + userRestUrl + "/login");
	
		//https://howtodoinjava.com/spring-boot2/resttemplate/spring-restful-client-resttemplate-example/
	    User theUser = restTemplate.postForObject(userRestUrl + "/login" , login, User.class);
	   

		logger.info("in validateUser(): theUser=" + theUser);

		return theUser;
	}

}
