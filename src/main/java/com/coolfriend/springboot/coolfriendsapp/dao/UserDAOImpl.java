package com.coolfriend.springboot.coolfriendsapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coolfriend.springboot.coolfriendsapp.entity.Login;
import com.coolfriend.springboot.coolfriendsapp.entity.User;



@Repository
public class UserDAOImpl implements UserDAO {

	private EntityManager entityManager;
	
	@Autowired
	public UserDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		
		// create a query
		Query theQuery =
				entityManager.createQuery("from User order by name");
		
		//execute query and get result list
		List<User> users = theQuery.getResultList();
		
		//return the results
		return users;
	}
	
	@Override
	@Transactional
    public List<User> searchUsers(String theSearchName) {

        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for name, country, city or gender ... case insensitive
         	theQuery = entityManager.createQuery("from User where lower(name) like :theName or lower(country) like :theName or lower(gender) like :theName or lower(city) like :theName order by name");
    		
    		theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
    		
    		System.out.println("searchUsers, query: from User where lower(name) like :theName or lower(country) like :theName or lower(gender) like :theName or lower(city) like :theName order by name");
        }
        else {
        	// create a query
    		theQuery =
    				entityManager.createQuery("from User order by name");
    		System.out.println("searchUsers, query: from User order by name");
    		        
        }
        
        // execute query and get result list
        List<User> users = theQuery.getResultList();
                
        // return the results        
        return users;
        
    }
	

	@Override
	public void saveUser(User theUser) {
		
		//save or update the user
		User dbUser = entityManager.merge(theUser);
		
		// update with id from db... so we can get generated id for save/insert
		theUser.setId(dbUser.getId());
	}

	@Override
	public User getUser(int theId) {
		// get user
		User theUser = entityManager.find(User.class, theId);
		// return user
		return theUser;
	}

	@Override
	public void deleteUser(int theId) {
		
		// delete object with primary key
		Query theQuery = entityManager.createQuery("delete from User where id=:userId");
		
		theQuery.setParameter("userId", theId);
		
		theQuery.executeUpdate();
	}

	@Override
	public User validateUser(Login login) {
		
		String name = login.getName();
		String password = login.getPassword();
		User user;
		System.out.println("validateUser, name: " + name);
		System.out.println("validateUser, password: " + password);
		
		

		// create a query ... sort by last name
		Query theQuery = entityManager.createQuery("FROM User u WHERE u.name=:name and u.password=:password");
		
		theQuery.setParameter("name", name);
		theQuery.setParameter("password", password);
		
		try {
			// execute query and get result list
			user = (User)theQuery.getSingleResult();
		
		}
		catch (NoResultException ex) {
			System.out.println("validateUser: " + ex.getMessage());
			user = null;
		}
		return user;
	}
	
	

}
