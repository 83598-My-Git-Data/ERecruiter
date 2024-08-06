package com.app.payload.response;

import com.app.entities.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApplicantAndJobInfo {
	private Long applicantId;
	private String applicantName;
    private Long jobId;
    private JobStatus status;
}
