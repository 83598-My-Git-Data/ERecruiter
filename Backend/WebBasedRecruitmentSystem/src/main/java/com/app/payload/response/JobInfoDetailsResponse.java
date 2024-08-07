package com.app.payload.response;

import java.time.LocalDate;

import com.app.entities.WorkSchedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter 
@NoArgsConstructor
@AllArgsConstructor
public class JobInfoDetailsResponse {

	private Long jobId;
	private String jobTitle;
	private int experienceRequired;
	private WorkSchedule workSchedule;
	private int salary;
	private LocalDate applicationDeadline;
	private LocalDate jobCreatedDate;
	private String qualification;
	private String departmentName;
	private int vacancies;
	private boolean status;
	private String description;
	
	
	
}
