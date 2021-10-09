package com.exception.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ContactNotValidException extends RuntimeException {

	private static final long serialVersionUID = 6807359738851606001L;
	
	public ContactNotValidException(String message) {
		super(message);
	}
	
	public ContactNotValidException(String message, Throwable cause) {
		super(message, cause);
	}
}
