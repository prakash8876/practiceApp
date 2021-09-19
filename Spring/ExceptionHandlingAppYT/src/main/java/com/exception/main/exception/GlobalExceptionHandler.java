package com.exception.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException exp) {
		ErrorResponse error = new ErrorResponse(exp.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmailExistsException(EmailExistsException exp) {
		ErrorResponse error = new ErrorResponse(exp.getMessage(), HttpStatus.CONFLICT.value(), System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
		ErrorResponse error = new ErrorResponse(exp.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exp) {
		ErrorResponse error = new ErrorResponse(exp.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}
