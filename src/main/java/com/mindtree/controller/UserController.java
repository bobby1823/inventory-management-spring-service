package com.mindtree.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mindtree.AppConfig.AppConfigSettings;
import com.mindtree.dto.UserDto;
import com.mindtree.entity.User;
import com.mindtree.exception.EmailExistsException;
import com.mindtree.exception.PasswordMismatchException;
import com.mindtree.exception.UserAlreadyExistsException;
import com.mindtree.exception.UserNotExistsException;
import com.mindtree.service.UserLoginService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@RestController
@EnableWebMvc
@RequestMapping(value = "/users")
@CrossOrigin("*")
public class UserController {
	

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfigSettings.class);
	
	UserLoginService userService = context.getBean(UserLoginService.class);
	
	@GetMapping(value = "/check")
	public String check() {
		return "Hello";
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<String> userRegistration(@RequestBody User user) throws MySQLIntegrityConstraintViolationException, EmailExistsException{
		System.out.println(user);
		try {
			userService.registerUser(user);
		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>("fail", HttpStatus.CONFLICT);
		} catch (EmailExistsException e) {
			return new ResponseEntity<>("registered email",HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<String> userLogin(@RequestBody UserDto userDto) throws PasswordMismatchException, UserNotExistsException{
		String userRole;
		System.out.println(userDto.getUserName() + "  " + userDto.getUserPassword());
		
		try {
			userRole = userService.loginCheck(userDto);
		} catch (UserNotExistsException e) {
			return new ResponseEntity<String>("Invalid User Name",HttpStatus.BAD_REQUEST);
		} catch (PasswordMismatchException e) {
			return new ResponseEntity<>("Password Incorrect",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(userRole,HttpStatus.OK);
		
	}



}
