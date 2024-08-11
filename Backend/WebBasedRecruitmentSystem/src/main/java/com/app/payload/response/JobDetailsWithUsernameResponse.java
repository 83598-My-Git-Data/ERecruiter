package com.app.payload.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

public class JobDetailsWithUsernameResponse {

	public Long jobId;
	public String jobTitle;
	public String firstName;
	public String lastName;
	public LocalDate jobCreatedDate;
	public LocalDate applicationDeadline;
	public String location;
	private int vacancies;
	private boolean status;
	public JobDetailsWithUsernameResponse(Long jobId, String jobTitle, String firstName, String lastName, LocalDate jobCreatedDate,
			LocalDate applicationDeadline, String location, int vacancies,boolean status) {
		super();
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobCreatedDate = jobCreatedDate;
		this.applicationDeadline = applicationDeadline;
		this.location = location;
		this.vacancies = vacancies;
		this.status=status;
	}
	
}
