package com.app.payload.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.app.entities.NoticePeriod;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BasicDetailRequest {
	
	@NotBlank(message = "first name is required field")
	@Size(min = 3,max = 30,message = "first name is not valid")
	private String firstName;
	
	@NotBlank(message = "last name is required field")
	@Size(min = 3,max = 30,message = "last name is not valid")
	private String lastName;
	
	@NotBlank(message = "email is required field")
	@Email
	private String email;
	
	@NotBlank(message = "Mobile number is required field")
	@Size(min = 10,max = 13,message = "mobile number is not valid")
	@Pattern(regexp = "^\\d{10}$")
	private String phoneNumber;
	
	
	private NoticePeriod noticePeriod;
}
