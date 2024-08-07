package com.app.payload.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmploymentRequest {
	
	@NotNull(message = "Employment ID is required field")
	private Long id;
	
	private boolean currentlyEmployed;
	
	@NotBlank(message = "Employment Type is required field")
	private String employementType;
		
	private int experienceYears;
		
	private int experienceMonths;
	
	@Size(min = 3,max =255,message = "Company name is not valid")
	private String currentCompanyName;
	
	@Size(min = 3,max =255,message = "Company name is not valid")
	private String previousCompanyName;
	
	@Size(min = 3,max =40,message = "Current Designation name is not valid")
	private String currentDesignation;
	
	@Size(min = 3,max =40,message = "Previous Designation name is not valid")
	private String previousDesignation;
	
	
	private String jobProfile;
	
	@Size(min = 3,max =40,message = "department name is not valid")
	private String department;
		
	
	private int currentSalary;
	
	public boolean getCurrentlyEmployed() {
		return this.currentlyEmployed;
	}
}
