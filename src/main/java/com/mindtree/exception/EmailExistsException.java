package com.mindtree.exception;

public class EmailExistsException extends ImsException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailExistsException(String message) {
		super(message);
	}

	public EmailExistsException(String message, EmailExistsException e) {
		super(message);
	}
	
	

}
