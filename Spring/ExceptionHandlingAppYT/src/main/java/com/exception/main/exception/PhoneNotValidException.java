package com.exception.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PhoneNotValidException extends RuntimeException {

	private static final long serialVersionUID = 4658686090056692222L;
	
	public PhoneNotValidException(String message) {
		super(message);
	}
	
	public PhoneNotValidException(String message, Throwable cause) {
		super(message, cause);
	}
}
