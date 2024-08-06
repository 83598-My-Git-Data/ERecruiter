package com.app.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
@AllArgsConstructor
public class UserAndOtp {

	@Email(message="email is not valid")
	private String email;
	
	@Size(min = 6, max = 6, message = "The string must have exactly 6 characters")
	@Pattern(regexp = "\\d+", message = "The string must contain only digits")
	private String otp;
}
