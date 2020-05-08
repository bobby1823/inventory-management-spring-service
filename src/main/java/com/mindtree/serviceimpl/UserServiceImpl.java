package com.mindtree.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.dao.UserDao;
import com.mindtree.dto.UserDto;
import com.mindtree.entity.User;
import com.mindtree.exception.EmailExistsException;
import com.mindtree.exception.PasswordMismatchException;
import com.mindtree.exception.UserAlreadyExistsException;
import com.mindtree.exception.UserNotExistsException;
import com.mindtree.service.UserLoginService;

@Service
public class UserServiceImpl implements UserLoginService {
	
	@Autowired
	UserDao userRepo;

	@Transactional
	@Override
	public String loginCheck(UserDto user) throws UserNotExistsException, PasswordMismatchException {
		User userInfo = userRepo.validUser(user.getUserName());
		System.out.println(userInfo);
		if (userInfo == null) {
			throw new UserNotExistsException("Invalid userName");
		} else {
			if (userInfo.getUserPassword().equals(user.getUserPassword())) {
				return userInfo.getUserRole();
			} else {
				throw new PasswordMismatchException("Incorrect Pasword");
			}
		}
	}


	@Transactional
	@Override
	public String registerUser(User user) throws UserAlreadyExistsException, EmailExistsException {
		User userInfo = userRepo.validUser(user.getUserName());
		User userEmail = userRepo.validEmail(user.getUserEmail());
	
		if(userEmail != null) {
			throw new EmailExistsException("Email Exists");
		}
		if(userInfo == null && userEmail == null) {
			userRepo.add(user);
			return "Registered User";
		} else {
			throw new UserAlreadyExistsException("User already registered");
		}
		
	}

}
