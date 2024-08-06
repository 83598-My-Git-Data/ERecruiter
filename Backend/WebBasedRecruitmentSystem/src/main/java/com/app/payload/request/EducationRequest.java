package com.app.payload.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EducationRequest {

	@NotNull(message = "Employment ID is required field")
	private Long id;
	
	@NotBlank(message = "Education Type is required field")
	private String educationType;
	
	@NotBlank(message = "Course is required field")
	private String course;
	
	@NotBlank(message = "University is required field")
	private String university;
	
	@NotBlank(message = "Specialization is required field")
	private String specialization;
	
	@Past(message = "Course Start date should be from past")
	private LocalDate courseStartDate;
	
	
	private LocalDate courseEndDate;
	
}
