package com.coolfriend.springboot.coolfriendsapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory;
import com.coolfriend.springboot.coolfriendsapp.entity.User;
import com.coolfriend.springboot.coolfriendsapp.service.LoginoutHistoryService;



@RestController
@RequestMapping("/api")
public class LoginoutHistoryRestController {
	
	@Autowired
	private LoginoutHistoryService loginoutHistoryService;
	
	@GetMapping("/loginout")
	public List<LoginoutHistory> getLoginoutHistory() {
		return loginoutHistoryService.getLoginoutHistories();
	}
	
	@GetMapping("/loginout/{userId}") 
	public List<LoginoutHistory> getLoginoutHistories(@PathVariable int userId) {
		List<LoginoutHistory> theHistories = loginoutHistoryService.getLoginoutHistories(userId);
		

		if ((userId < 0)) {
			throw new RestNotFoundException("Loginout history not found for user id: " + userId);
		}
//		
		return theHistories;
	}
	
	@GetMapping("/loginout/last/{userId}") 
	public LoginoutHistory getLastLoginoutHistory(@PathVariable int userId) {
		LoginoutHistory theHistory = loginoutHistoryService.getLastLoginHistory(userId);
		

		if ((userId < 0)) {
			throw new RestNotFoundException("Loginout history not found for user id: " + userId);
		}
//		
		return theHistory;
	}
	
	// add mapping for POST /loginout - add loginout
		@PostMapping("/loginout")
		public LoginoutHistory addLoginout(@RequestBody LoginoutHistory theHistory) {
			//Set 0 for Insert, otherwise, Hibernate will update.
			theHistory.setId(0);
			loginoutHistoryService.saveLoginoutHistory(theHistory);
			return theHistory;
			
		}

}
