package com.app.payload.request;

import java.time.LocalDate;

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
public class ProjectRequest {
	
	@NotNull(message = "Project ID is required field")
	private Long id;
	
	@NotBlank(message = "Project title is required field")
	@Size(min = 3,max =150,message = "Project title is not valid")
	private String projectTitle;
	
	@NotNull(message = " Project Status is required field")
	private boolean projectStatus;
	
	@NotNull(message = "Project Start Date is required field")
	private LocalDate projectStartDate;
	
	private LocalDate projectEndDate;
	
	@NotBlank(message = "Project Description is required field")
	@Size(min = 3,max =255,message = "Project Description is not valid")
	private String projectDescription;
	
	public boolean getProjectStatus() {
		return this.projectStatus;
	}
}
