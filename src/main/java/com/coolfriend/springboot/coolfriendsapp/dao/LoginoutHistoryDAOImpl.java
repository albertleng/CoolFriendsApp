package com.coolfriend.springboot.coolfriendsapp.dao;

import java.util.List;

import javax.persistence.EntityManager;


import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coolfriend.springboot.coolfriendsapp.entity.LoginoutHistory;



@Repository
public class LoginoutHistoryDAOImpl implements LoginoutHistoryDAO {

	private EntityManager entityManager;

	@Autowired
	public LoginoutHistoryDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	@Transactional
	public List<LoginoutHistory> getLoginoutHistories() {
		
		Query theQuery =
				entityManager.createQuery("from LoginoutHistory");
		
		//execute query and get result list
		List<LoginoutHistory> loginoutHistories = theQuery.getResultList();

		// return the results
		return loginoutHistories;
	}

	@Override
	public void saveLoginoutHistory(LoginoutHistory theLoginoutHistory) {
		//save or update the employee
		LoginoutHistory dbLoginoutHistory = entityManager.merge(theLoginoutHistory);
		
		// update with id from db... so we can get generated id for save/insert
		theLoginoutHistory.setId(dbLoginoutHistory.getId());
	}

	@Override
	public LoginoutHistory getLastLoginHistory(int theUserId) {
		LoginoutHistory loginoutHistory;

		Query theQuery = entityManager.createQuery("FROM LoginoutHistory u WHERE u.user_id=:user_id AND u.eventType=:login "
				+ "ORDER BY event_datetime DESC");
		
		theQuery.setParameter("user_id", theUserId);
		theQuery.setParameter("login", "login");
		

		try {

			loginoutHistory = (LoginoutHistory)theQuery.getResultList().get(0);

		} catch (Exception ex) {
			System.out.println("getLoginoutHistory: " + ex.getMessage());
			loginoutHistory = null;
		}
		return loginoutHistory;
	}

	@Override
	public List<LoginoutHistory> getLoginoutHistories(int theUserId) {
		Query theQuery =
				entityManager.createQuery("FROM LoginoutHistory u WHERE u.user_id=:user_id " 
										+ "ORDER BY event_datetime DESC");
		
		theQuery.setParameter("user_id", theUserId);
		
		
		//execute query and get result list
		List<LoginoutHistory> loginoutHistories = theQuery.getResultList();

		// return the results
		return loginoutHistories;
	}

}
