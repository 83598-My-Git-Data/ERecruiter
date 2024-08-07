package com.app.payload.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.app.entities.Gender;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PersonalDetailRequest {
	
	@Past
	private LocalDate dob;
	
	
	private Gender gender;	
	
	@NotBlank(message = "Marital status should not be null")
	@Size(min=3,max = 10)
	private String maritalStatus;
}
