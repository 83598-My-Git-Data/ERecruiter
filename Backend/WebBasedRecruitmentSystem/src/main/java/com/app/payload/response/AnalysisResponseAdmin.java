package com.app.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisResponseAdmin {

	private long totalNumberOfHr;
	private long activeHr;
	private long totalNumberOfJobs;
	private long openJobs;
}
