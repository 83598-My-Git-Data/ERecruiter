package com.app.payload.request;


import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPassword {

	@NotBlank(message = "Password cannot be blank")
	// Add more constraints as needed for your password policy
	private String password;
}
