package com.mindtree.serviceimpl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.when;

import com.mindtree.dao.UserDao;
import com.mindtree.dto.UserDto;
import com.mindtree.entity.User;
import com.mindtree.exception.EmailExistsException;
import com.mindtree.exception.PasswordMismatchException;
import com.mindtree.exception.UserAlreadyExistsException;
import com.mindtree.exception.UserNotExistsException;

public class UserServiceImplTest {

	@Autowired
	UserDao userRepo;

	@InjectMocks
	private UserServiceImpl userService;

	@Spy
	List<User> userList = new ArrayList<>();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}


	@Test
	public void testRegisterUser() throws UserAlreadyExistsException, EmailExistsException {
		User user = new User();

		user.setUserName("Akshay");
		user.setUserEmail("akshay.hemaraju@mindtree.com");
		user.setUserRole("Department Manager");
		user.setUserPassword("123456Aa@");
		user.setUserId(1);

//		User userInfo = null;

		System.out.println(user.toString());

		/*
		 * when(userInfo.getUserEmail() == null).thenReturn(true); when(userInfo ==
		 * null).thenReturn(true);
		 */
		// assertEquals("Registered User", userService.registerUser(user));
	}

	
	@Test
	public void testLoginCheck() throws UserNotExistsException, PasswordMismatchException {
		UserDto userDto = new UserDto("Akshay", "123456Aa@");
		System.out.println(userDto);

		User user = new User();

		user.setUserName("Akshay");
		user.setUserEmail("akshay.hemaraju@mindtree.com");
		user.setUserRole("Department Manager");
		user.setUserPassword("123456Aa@");
		
		when(userRepo.validUser(userDto.getUserName())).thenReturn(user);
		when(userDto.getUserPassword().equals(user.getUserPassword())).thenReturn(true);
		assertEquals("Department Manager", userService.loginCheck(userDto));
		
	}
	
	@Test(expected = EmailExistsException.class)
	public void authenticateException() throws UserAlreadyExistsException, EmailExistsException  {
		User user = new User();
		user.setUserName("Akshay");
		user.setUserEmail("akshay.hemaraju@mindtree.com");
		user.setUserRole("Department Manager");
		user.setUserPassword("123456Aa@");
		UserDto userDto = new UserDto("akshauranjdjdjga9478@gmail.com", "12345Aa@");
		System.out.println(userDto.getUserName());
		when(userRepo.validUser(userDto.getUserName())).thenReturn(user);
		userService.registerUser(user);
	}

}
