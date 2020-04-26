package com.coolfriend.springboot.coolfriendsapp.service;

import java.util.List;

import com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory;



public interface LoginoutHistoryService {
	public List<LoginoutHistory> getLoginoutHistories();

	public void saveLoginoutHistory(LoginoutHistory theLoginoutHistory);

	public LoginoutHistory getLastLoginHistory(int theUserId);
	
	public List<LoginoutHistory> getLoginoutHistories(int theUserId);
}
