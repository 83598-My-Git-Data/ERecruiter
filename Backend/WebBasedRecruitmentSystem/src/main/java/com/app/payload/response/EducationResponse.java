package com.app.payload.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EducationResponse {
	
	private String educationType;
	private String course;
	private String university;
	private String specialization;
	private LocalDate courseStartDate;
	private LocalDate courseEndDate;
}
