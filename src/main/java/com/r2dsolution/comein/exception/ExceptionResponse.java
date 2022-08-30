package com.r2dsolution.comein.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
	private String token;
	private String message;
	private LocalDateTime time;
	
	public ExceptionResponse(String token, String message, LocalDateTime time) {
		super();
		this.token = token;
		this.message = message;
		this.time = time;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

}
