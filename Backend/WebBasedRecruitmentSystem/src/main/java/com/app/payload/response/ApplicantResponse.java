package com.app.payload.response;



import com.app.entities.NoticePeriod;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApplicantResponse {
	
	private boolean emailIdVerifyStatus; 
	private boolean mobileNumVerifyStatus;
	private String resumeLink;
	private String resumeHeadLine;
	private String profileSummary;
	private String profilePictureLink;
	private String maritalStatus;
	private NoticePeriod noticePeriod;
	
}
