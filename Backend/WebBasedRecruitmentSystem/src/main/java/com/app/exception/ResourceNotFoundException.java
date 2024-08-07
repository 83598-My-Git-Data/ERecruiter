package com.app.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

	private String resourceName;
	private String fieldName;
	private Long id;
	private String email;
	
	public ResourceNotFoundException(String resourceName,String fieldName,Long id) {
		super(String.format("%s not found with %s:%d",resourceName,fieldName,id));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.id=id;
	}
	
	public ResourceNotFoundException(String resourceName,String fieldName,String email) {
		super(String.format("%s not found with %s:%d",resourceName,fieldName,email));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.email=email;
	}
}
