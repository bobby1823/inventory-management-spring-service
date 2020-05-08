package com.mindtree.service;

import com.mindtree.dto.UserDto;
import com.mindtree.entity.User;
import com.mindtree.exception.EmailExistsException;
import com.mindtree.exception.PasswordMismatchException;
import com.mindtree.exception.UserAlreadyExistsException;
import com.mindtree.exception.UserNotExistsException;

public interface UserLoginService {
	
	public String loginCheck(UserDto user) throws UserNotExistsException, PasswordMismatchException;
	public String registerUser(User user) throws UserAlreadyExistsException, EmailExistsException;

}
