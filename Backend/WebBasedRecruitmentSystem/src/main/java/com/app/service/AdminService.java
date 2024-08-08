package com.app.service;

import java.util.List;

import com.app.payload.request.HrRegistrationDetailsRequest;
import com.app.payload.response.AnalysisResponseAdmin;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.HrDetailsResponse;
import com.app.payload.response.JobDetailsWithUsernameResponse;

public interface AdminService {

	
	//register hr
	public ApiResponse registerHr(HrRegistrationDetailsRequest hr);
	
	
	//get list of all hr
	public List<HrDetailsResponse>  getAllHr();
	
	//get list of all jobs
	public List<JobDetailsWithUsernameResponse> getAllJobs();
	
	
	
	//deactivate hr
	public ApiResponse deactivateHrById(Long hrId);
	
	//get no of {total hr,activehr,total jobs,openjobs,}
	public AnalysisResponseAdmin getReport();
	
	
}
