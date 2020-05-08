package com.mindtree.dao;

import com.mindtree.GenericDao.GenericDao;
import com.mindtree.entity.User;

public interface UserDao extends GenericDao<User, Integer>{
	
	public User validUser(String userName);
	//public void registerUser(User user) throws EmailExistsException;
	public User validEmail(String userEmail);
}
