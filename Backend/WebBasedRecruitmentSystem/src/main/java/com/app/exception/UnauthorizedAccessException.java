package com.app.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnauthorizedAccessException extends RuntimeException{
	private String message;

	public UnauthorizedAccessException(String message) {
		super(message);
		this.message = message;
	}
	
}
