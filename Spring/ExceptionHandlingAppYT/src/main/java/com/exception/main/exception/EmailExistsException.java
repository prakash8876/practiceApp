package com.exception.main.exception;

public class EmailExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6939510640437557287L;

	public EmailExistsException(String message) {
		super(message);
	}
	
	public EmailExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
