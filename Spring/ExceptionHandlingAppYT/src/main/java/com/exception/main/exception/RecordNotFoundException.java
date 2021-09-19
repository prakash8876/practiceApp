package com.exception.main.exception;

public class RecordNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 467072583441737163L;

	public RecordNotFoundException(String message) {
		super(message);
	}
	
	public RecordNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
