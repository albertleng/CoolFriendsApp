package com.coolfriend.springboot.coolfriendsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolfriend.springboot.coolfriendsapp.dao.UserDAO;
import com.coolfriend.springboot.coolfriendsapp.entity.Login;
import com.coolfriend.springboot.coolfriendsapp.entity.User;



@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public List<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	@Transactional
	public void saveUser(User theUser) {
		userDAO.saveUser(theUser);

	}

	@Override
	@Transactional
	public User getUser(int theId) {
		return userDAO.getUser(theId);
	}

	@Override
	@Transactional
	public void deleteUser(int theId) {
		userDAO.deleteUser(theId);
	}

	@Override
	@Transactional
	public User validateUser(Login login) {
		return userDAO.validateUser(login);
	}
	
	@Override
    @Transactional
    public List<User> searchUsers(String theSearchName) {

        return userDAO.searchUsers(theSearchName);
    }

}
