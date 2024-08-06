package com.app.payload.response;





import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmploymentResponse {
	private Long id;
	private boolean currentlyEmployed;
	private String employementType;
	private int experienceYears;
	private int experienceMonths;
	private String currentCompanyName;
	private String previousCompanyName;
	private String currentDesignation;
	private String previousDesignation;
	private String jobProfile;
	private String department;
	private int currentSalary;

}
