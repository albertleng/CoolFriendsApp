package com.coolfriend.springboot.coolfriendsapp.restservice;

import java.util.List;

import com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory;

public interface LoginoutHistoryRestService {
	public List<LoginoutHistory> getLoginoutHistories();

	public void saveLoginoutHistory(LoginoutHistory theLoginoutHistory);

	public LoginoutHistory getLastLoginHistory(int theUserId);
	
	public List<LoginoutHistory> getLoginoutHistories(int theUserId);
}
