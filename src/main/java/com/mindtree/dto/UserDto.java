package com.mindtree.dto;

public class UserDto {
	
	private String userName;
	private String userPassword;
	
	
	public UserDto() {
	
	}

	

	public UserDto(String userName, String userPassword) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}



	@Override
	public String toString() {
		return "UserDto [userName=" + userName + ", userPassword=" + userPassword + "]";
	}

	
	
	
	
	

}
