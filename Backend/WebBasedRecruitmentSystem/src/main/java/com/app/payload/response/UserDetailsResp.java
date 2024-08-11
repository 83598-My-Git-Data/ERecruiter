package com.app.payload.response;



import java.time.LocalDate;

import com.app.entities.Gender;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDetailsResp {
	
	private String firstName;
	private String lastName;
	private Gender gender;
	private String email;
	private String phoneNumber;
	private LocalDate dob; 
}
