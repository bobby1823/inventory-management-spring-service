package com.mindtree.daoImpl;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.mindtree.GenericDaoImpl.GenericDaoImpl;
import com.mindtree.dao.UserDao;
import com.mindtree.entity.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

	

	@Override
	public User validUser(String userName) {
		User result = null;
		try {
			result = (User) currentSession().createQuery("from User where userName =:userName").setParameter("userName", userName).getSingleResult();
		} catch (NoResultException e) {
	
		}
		return result;
	}

/*	@Override
	public void registerUser(User user)  {
			sessionFactory.getCurrentSession().save(user);
	}*/

	@Override
	public User validEmail(String userEmail) {
		User result = null;
		try {
			result = (User) currentSession().createQuery("from User where userEmail =:userEmail").setParameter("userEmail", userEmail).getSingleResult();
		} catch (NoResultException e) {
	
		}
		return result;
		
	}

	
	
}
