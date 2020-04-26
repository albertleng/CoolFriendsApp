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

import com.coolfriend.springboot.coolfriendsapp.entity.Login;
import com.coolfriend.springboot.coolfriendsapp.entity.User;
import com.coolfriend.springboot.coolfriendsapp.service.UserService;



@RestController
@RequestMapping("/api")
public class UserRestController {
    
	@Autowired
	private UserService userService;
	
	@GetMapping(value= {"/users", "/search"})
	public List<User> getUsers() {
		
		
		return userService.getUsers();
	}
	
	@GetMapping("/search/{searchName}")
	public List<User> searchUsers(@PathVariable String searchName) {
		System.out.println("userRestController, searchUsers: " + searchName);
		
		if (searchName == null) {
			System.out.println("searchName is null");
		}
		else {
			System.out.println("searchName is not null, :" + searchName + ", " + searchName.trim());		
		}
		return userService.searchUsers(searchName);
	}
	
	@GetMapping("/users/{userId}") 
	public User getUser(@PathVariable int userId) {
		User theUser = userService.getUser(userId);
		

		if ((userId < 0) || theUser == null) {
					throw new RestNotFoundException("User id not found - " + userId);
		}
		
		return theUser;
	}
	
	// add mapping for POST /users - add new user
	@PostMapping("/users")
	public User addUser(@RequestBody User theUser) {
		//Set 0 for Insert, otherwise, Hibernate will update.
		theUser.setId(0);
		userService.saveUser(theUser);
		return theUser;
		
	}
	
	// add mapping for PUT /users - update user
	@PutMapping("/users")
	public User saveUser(@RequestBody User theUser) {
		userService.saveUser(theUser);
		return theUser;
		
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userService.deleteUser(userId);
	}
	
	// add mapping for POST /users  
	@PostMapping("/users/login")
	public User validateUser(@RequestBody Login theLogin) {
		User theUser = userService.validateUser(theLogin);
		return theUser;
		
	}
	

}
