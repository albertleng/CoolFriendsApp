package com.coolfriend.springboot.coolfriendsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolfriend.springboot.coolfriendsapp.dao.LoginoutHistoryDAO;
import com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory;



@Service("loginoutHistoryServiceImpl")
public class LoginoutHistoryServiceImpl implements LoginoutHistoryService {

	@Autowired
	private LoginoutHistoryDAO historyDAO;

	@Override
	@Transactional
	public List<LoginoutHistory> getLoginoutHistories() {
		return historyDAO.getLoginoutHistories();
	}

	@Override
	@Transactional
	public void saveLoginoutHistory(LoginoutHistory theLoginoutHistory) {
		historyDAO.saveLoginoutHistory(theLoginoutHistory);
	}

	@Override
	@Transactional
	public LoginoutHistory getLastLoginHistory(int theUserId) {
		return historyDAO.getLastLoginHistory(theUserId);
	}

	@Override
	@Transactional
	public List<LoginoutHistory> getLoginoutHistories(int theUserId) {
		return historyDAO.getLoginoutHistories(theUserId);
	}

}
