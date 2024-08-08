package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payload.response.ApiResponse;
import com.app.payload.response.JobDetails;
import com.app.payload.response.JobInfoDetailsResponse;
import com.app.service.JobInfoService;

@RestController
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	private JobInfoService jobService;
	
	
	// Rest API end point
	// URL : http://localhost:7878/job/available-jobs
	// Method : GET
	// Payload : appliedJobRequest
	@GetMapping("/available-job")
	public ResponseEntity<?> availableJob() {
	
		List<JobDetails> jobList=jobService.availableJobFun();
		
		return new ResponseEntity<>(jobList,HttpStatus.OK);
	}	
	
	// Rest API end point
	// URL : http://localhost:7878/job/apply-job
	// Method : POST
	// Payload : appliedJobRequest
	@PostMapping("/apply-job/{jobId}")
	public ResponseEntity<ApiResponse> applyJob(@PathVariable @Valid Long jobId ) {
	
		ApiResponse apiResponse=jobService.applyJobFun(jobId);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	

	// Rest API end point
	// URL : http://localhost:7878/job/unapply-job
	// Method : POST
	// Payload : appliedJobRequest
	@DeleteMapping("/unapply-job/{jobId}")
	public ResponseEntity<ApiResponse> unApplyJob(@PathVariable @Valid Long jobId ) {
	
		ApiResponse apiResponse=jobService.unApplyJobFun(jobId);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/job/get-applied-job
	// Method : GET
	// Payload : JobInfoDetailsResponse
	@GetMapping("/get-applied-job")
	public ResponseEntity<?> getAppliedJob() {
	
		List<JobInfoDetailsResponse> jobList=jobService.getAppliedJobFun();
		
		return new ResponseEntity<>(jobList,HttpStatus.OK);
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/job/get-sortlisted-job
	// Method : GET
	// Payload : JobInfoDetailsResponse
	@GetMapping("/get-shortlisted-job")
	public ResponseEntity<?> getSortListedJob() {
	
		List<JobInfoDetailsResponse> jobList=jobService.getSortListedJobFun();
		
		return new ResponseEntity<>(jobList,HttpStatus.OK);
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/job/save-job
	// Method : POST
	// Payload : Long
	@PostMapping("/save-job/{jobId}")
	public ResponseEntity<ApiResponse> saveJob(@PathVariable @Valid Long jobId ) {
	
		ApiResponse apiResponse=jobService.saveJobFun(jobId);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/job/unsave-job
	// Method : POST
	// Payload : Long
	@PostMapping("/unsave-job/{jobId}")
	public ResponseEntity<ApiResponse> unSaveJob(@PathVariable @Valid Long jobId ) {
	
		ApiResponse apiResponse=jobService.unSaveJobFun(jobId);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/job/get-saved-job
	// Method : GET
	// Payload : JobInfoDetailsResponse
	@GetMapping("/get-saved-job")
	public ResponseEntity<?> getSavedJob() {
	
		List<JobInfoDetailsResponse> jobList=jobService.getSavedJobFun();
		
		return new ResponseEntity<>(jobList,HttpStatus.OK);
	}
}
