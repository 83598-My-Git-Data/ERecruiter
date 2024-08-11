package com.app.payload.request;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.app.entities.WorkSchedule;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter 
@NoArgsConstructor @AllArgsConstructor
public class JobDetailsRequest {
	@NotBlank(message = "title should not be null")
	@Size(min=3,max = 30)
	private String jobTitle;
	@NotNull
	private int experienceRequired;
	@NotNull
	private WorkSchedule workSchedule;
	@NotNull
	private int salary;
	@NotNull
	private LocalDate applicationDeadline;
	@NotBlank(message="location should not be empty")
	private String location;
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDate jobCreatedDate=LocalDate.now();
	@NotBlank(message="qualification should not be empty")
	private String qualification;
	@NotNull(message = "enter department id")
	private Long depId;
	@NotNull(message ="enter number of vacancies")
	@Min(3)
	private int vacancies;
	private boolean status;
	@NotBlank
	private String description;
}
