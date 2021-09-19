package com.exception.main.exception;

public class ErrorResponse {

	private String message;

	private int statusCode;

	private long timestamp;

	public ErrorResponse() {}

	public ErrorResponse(String message, int statusCode, long timestamp) {
		this.message = message;
		this.statusCode = statusCode;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
