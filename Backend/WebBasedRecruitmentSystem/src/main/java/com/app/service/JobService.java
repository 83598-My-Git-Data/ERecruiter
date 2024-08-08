package com.app.service;

import java.util.List;

import com.app.payload.request.JobDetailsRequest;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.ApplicantAndJobInfo;
import com.app.payload.response.JobInfoDetailsResponse;

public interface JobService {

	//post method to post a job by HR
	public ApiResponse createJob(JobDetailsRequest job);
	
	//get job lists created by HR only
	public List<JobInfoDetailsResponse> getAllJobsCreatedByHr();
	
	//deactivate the job --job which are only created by the HR
	public ApiResponse deactivateJobById(Long JobId);
	
	//get the particular job by id and HR name
	public JobInfoDetailsResponse getJobByHrAndJobId(Long jobId);
	
	//get no. of vacancies opening in the job posted by HR
	
	//update the job details
	public ApiResponse updateJobDetails(JobDetailsRequest job,Long jobId);
	
	//get applicant list for particular job
	public List<ApplicantAndJobInfo> getApplicants(Long jobId);
	
	
	
}
