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

import com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory;


@Service
public class LoginoutHistoryRestServiceImpl implements LoginoutHistoryRestService {
	
	private RestTemplate restTemplate;

	private String loginoutRestUrl;

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	public LoginoutHistoryRestServiceImpl(RestTemplate theRestTemplate, @Value("${coolfriends.rest.loginout}") String theUrl) {
		restTemplate = theRestTemplate;
		loginoutRestUrl = theUrl;

		logger.info("Loaded property:  coolfriends.rest.loginout=" + loginoutRestUrl);
	}

	@Override
	public List<LoginoutHistory> getLoginoutHistories() {
		logger.info("in getLoginoutHistories(): Calling REST API " + loginoutRestUrl);

		// make REST call
		ResponseEntity<List<LoginoutHistory>> responseEntity = restTemplate.exchange(loginoutRestUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<LoginoutHistory>>() {
				});

		// get the list of users from response
		List<LoginoutHistory> loginoutHistories = responseEntity.getBody();

		logger.info("in getLoginoutHistories(): loginoutHistories" + loginoutHistories);

		return loginoutHistories;
	}

	@Override
	public void saveLoginoutHistory(LoginoutHistory theLoginoutHistory) {
		logger.info("in saveLoginoutHistory(): Calling REST API " + loginoutRestUrl);

		
		// add loginout history
		restTemplate.postForEntity(loginoutRestUrl, theLoginoutHistory, String.class);

		
		logger.info("in saveLoginoutHistory(): success");

	}


	@Override
	public LoginoutHistory getLastLoginHistory(int theUserId) {
		logger.info("in getLastLoginHistory(): Calling REST API " + loginoutRestUrl + "/last/" + theUserId);

		// make REST call
		LoginoutHistory theLoginoutHistory = restTemplate.getForObject(loginoutRestUrl + "/last/" + theUserId, LoginoutHistory.class);

		logger.info("in getLastLoginHistory(): theLoginoutHistory=" + theLoginoutHistory);

		return theLoginoutHistory;
	}

	@Override
	public List<LoginoutHistory> getLoginoutHistories(int theUserId) {
		logger.info("in getLoginoutHistories(): Calling REST API " + loginoutRestUrl + "/" + theUserId);

		// make REST call
		ResponseEntity<List<LoginoutHistory>> responseEntity = restTemplate.exchange(loginoutRestUrl + "/" + theUserId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<LoginoutHistory>>() {
				});

		// get the list of users from response
		List<LoginoutHistory> loginoutHistories = responseEntity.getBody();

		logger.info("in getLoginoutHistories(): loginoutHistories" + loginoutHistories);

		return loginoutHistories;
	}

}
