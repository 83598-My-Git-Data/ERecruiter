package com.app.service;

import java.util.List;

import com.app.payload.response.ApiResponse;
import com.app.payload.response.JobDetails;
import com.app.payload.response.JobInfoDetailsResponse;

public interface JobInfoService {

	/**
	 * Apply a job
	 **/
	ApiResponse applyJobFun( Long jobId);
	
	
	/**
	 * UnApply a job
	 **/
	ApiResponse unApplyJobFun(Long jobId);

	
	/**
	 * get applied job
	 **/
	List<JobInfoDetailsResponse> getAppliedJobFun();
	
	
	/**
	 * save a job
	 **/
	ApiResponse saveJobFun(Long jobId);


	/**
	 * Unsave a job
	 **/
	ApiResponse unSaveJobFun(Long jobId);

	/**
	 * get Saved job
	 **/
	List<JobInfoDetailsResponse> getSavedJobFun();


	/**
	 * Get SortListed jobs
	 * **/
	List<JobInfoDetailsResponse> getSortListedJobFun();

	
	/**
	 * Get avalable jobs
	 * **/
	List<JobDetails> availableJobFun();

}
