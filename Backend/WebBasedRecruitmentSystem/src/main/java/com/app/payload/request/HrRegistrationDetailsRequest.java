package com.app.payload.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.app.entities.Gender;
import com.app.entities.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HrRegistrationDetailsRequest {

	@NotBlank
	@Size(min = 3,max = 30,message = "first name is not valid")
	private String firstName;
	@NotBlank
	@Size(min = 3,max = 30,message = "last name is not valid")
	private String lastName;
	@NotNull
	private Gender gender;
	@NotBlank
	@Email
	private String email;
	@NotNull
	@Size(min = 10,max = 13,message = "mobile number is not valid")
	@Pattern(regexp = "^\\d{10}$")
	private String phoneNumber;
	@NotNull
	@Size(min = 8,max = 13,message = "password is not valid")
	// TODO:implement the password regex
	private String password;
	
	@NotNull
	private LocalDate dob;
	
	@JsonProperty(access = Access.READ_ONLY) 
	private UserRole role=UserRole.ROLE_HR;
	
	@NotBlank
	@Size(min=3,message = "enter valid location")
	private String officeLocation;
	
	@NotBlank(message = "enter valid department")
	private String department;
	
	@JsonProperty(access = Access.READ_ONLY)
	public boolean activeStatus =true;
	
	@NotBlank(message = "qualification should not be empty")
	@Size(min =3,max=40,message="enter valid qualification")
	private String qualification;
}
