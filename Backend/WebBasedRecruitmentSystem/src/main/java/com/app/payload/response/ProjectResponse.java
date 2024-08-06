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
public class ProjectResponse {
	private Long id;
	private String projectTitle;
	private boolean projectStatus;
	private LocalDate projectStartDate;
	private LocalDate projectEndDate;
	private String projectDescription;
}
