package com.coolfriend.springboot.coolfriendsapp.dao;

import java.util.List;

import com.coolfriend.springboot.coolfriendsapp.entity.Login;
import com.coolfriend.springboot.coolfriendsapp.entity.User;



public interface UserDAO {
	
	public List<User> getUsers();

	public void saveUser(User theUser);

	public User getUser(int theId);

	public void deleteUser(int theId);

	public User validateUser(Login login);
	
	public List<User> searchUsers(String theSearchName);

}
