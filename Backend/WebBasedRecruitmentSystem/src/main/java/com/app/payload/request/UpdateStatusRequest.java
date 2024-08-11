package com.app.payload.request;

import com.app.entities.JobStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateStatusRequest {
	private Long JobId;
	private Long applicantId;
	private JobStatus status;
}
