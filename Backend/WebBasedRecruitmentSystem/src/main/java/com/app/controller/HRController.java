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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.payload.request.HrRequest;
import com.app.payload.request.JobDetailsRequest;
import com.app.payload.request.UpdateStatusRequest;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.ApplicantAndJobInfo;
import com.app.payload.response.HrResponse;
import com.app.service.AppliedJobService;
import com.app.service.HrService;
import com.app.service.JobService;


@RestController
@RequestMapping("/hr")
public class HRController {

	@Autowired
	private HrService hrService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private AppliedJobService appliedJobService;
	
	@GetMapping
	public ResponseEntity<HrResponse> getHrDetails()
	{
		return new ResponseEntity<HrResponse>(hrService.getHrDetails(),HttpStatus.OK);
	}
	

	@PostMapping
	public ResponseEntity<ApiResponse> updateHr(@RequestBody HrRequest hr){
		return new ResponseEntity<ApiResponse>(hrService.updateHr(hr),HttpStatus.OK);
	}
	
	@PostMapping("/create-job")
	public ResponseEntity<ApiResponse> createJob(@RequestBody JobDetailsRequest job)
	{
		return new ResponseEntity<ApiResponse>(jobService.createJob(job),HttpStatus.CREATED);
	}
	@GetMapping("/jobs")
	public ResponseEntity<?> getAllJobsCreatedByHr()
	{
		return new ResponseEntity<>(jobService.getAllJobsCreatedByHr(),HttpStatus.OK);
	}
	
	@GetMapping("/job/{jobId}")
	public ResponseEntity<?> getJobCreatedByHr(@PathVariable Long jobId)
	{
		return new ResponseEntity<>(jobService.getJobByHrAndJobId(jobId),HttpStatus.OK);
	}
	
	
	/**
	 * Update the status of job
	 * */
	@PutMapping("/job/deactivate-job/{jobId}")
	public ResponseEntity<ApiResponse> deactivateJob(@PathVariable Long jobId)
	{
		return new ResponseEntity<ApiResponse>(jobService.deactivateJobById(jobId),HttpStatus.OK);
	}
	
	
	/**
	 * Update the job
	 * */
	@PutMapping("/job/update-job/{jobId}")
	public ResponseEntity<ApiResponse> updateJobDetails(@RequestBody JobDetailsRequest job,@PathVariable Long jobId)
	{
		ApiResponse response=jobService.updateJobDetails(job,jobId);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.CREATED);
	}
	
	/**
	 * Get the applicants for particular job by jobId
	 * */
	@GetMapping("/applicants/{jobId}")
	public ResponseEntity<List<ApplicantAndJobInfo>> getApplicantsForJobId(@PathVariable Long jobId)
	{
		return new ResponseEntity<List<ApplicantAndJobInfo>>(jobService.getApplicants(jobId),HttpStatus.OK);
	}
	
	/**
	 * HR will upload his/her profile image 
	 * */
//	@PostMapping("/upload-image")
//	public ResponseEntity<ApiResponse> uploadImage(@RequestParam(value="file",required = true) MultipartFile file)
//	{
//		if (!isValidImage(file)) {
//            return new ResponseEntity<ApiResponse>(new ApiResponse("Invalid file type"),HttpStatus.BAD_REQUEST);
//        }
//		return new ResponseEntity<ApiResponse>(hrService.uploadImage(file),HttpStatus.OK);
//	}
//	
	
	/**
	 * HR can update his/her image 
	 * */
//	@PutMapping("/update-image")
//	public ResponseEntity<ApiResponse> updateImage(@RequestParam(value="file",required = true) MultipartFile file)
//	{
//		if (!isValidImage(file)) {
//            return new ResponseEntity<ApiResponse>(new ApiResponse("Invalid file type"),HttpStatus.BAD_REQUEST);
//        }
//		return new ResponseEntity<ApiResponse>(hrService.updateImage(file),HttpStatus.OK);
//	}
//	
	/**
	 * Remove image from AWS S3 and user account
	 * */
//	@DeleteMapping("/remove-image")
//	public ResponseEntity<ApiResponse> removeImage()
//	{
//		return new ResponseEntity<ApiResponse>(hrService.removeImage(),HttpStatus.OK);
//	}
	
	
	  /**
	   * Custom validation method to check if the file is an image
	   * */ 
//    private boolean isValidImage(MultipartFile file) {
//        String fileName = file.getOriginalFilename();
//        return fileName != null && (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg"));
//    }
    

	// Rest API end point
	// URL : http://localhost:7878/hr/updateStatus
	// Method : PUT
	// Payload : String
	@PutMapping("/update-status")
	public ResponseEntity<ApiResponse> updateStatus(@RequestBody @Valid UpdateStatusRequest status) {
	
		ApiResponse apiResponse=appliedJobService.updateStatusFun(status);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
}
