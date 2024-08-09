package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payload.request.HrRegistrationDetailsRequest;
import com.app.payload.response.AnalysisResponseAdmin;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.HrDetailsResponse;
import com.app.payload.response.JobDetailsWithUsernameResponse;
import com.app.service.AdminService;


@RestController
@RequestMapping("/admin")
public class AdminController {

	public AdminController() {
		System.out.println("Admin controller");
	}
	@Autowired
	private AdminService adminService;
	
	
	//creating a hr account
	// https://localhost:7878/admin/registerHr
	@PostMapping("/register-hr")
	public ResponseEntity<?> registerHr(@RequestBody @Valid HrRegistrationDetailsRequest hr){
		System.out.println("Inside controller of admin register hr");
		ApiResponse res=adminService.registerHr(hr);
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	//getting the list of hr
	// https://localhost:7878/admin/hrlist
	@GetMapping("/hr-list")
	public ResponseEntity<List<HrDetailsResponse>> getAllHrInfo()
	{
		return new ResponseEntity<List<HrDetailsResponse>>(adminService.getAllHr(),HttpStatus.OK);
	}
	
	//get t whole list of jobs
	// https://localhost:7878/admin/jobList
	@GetMapping("/job-list")
	public ResponseEntity<List<JobDetailsWithUsernameResponse>> getAllJobs()
	{
		return new ResponseEntity<List<JobDetailsWithUsernameResponse>>(adminService.getAllJobs(),HttpStatus.OK);
	}
	
	
	//deactivating HR account
	// https://localhost:7878/admin/deactivatehr/{hrId}
	@PutMapping("/deactivate-hr/{hrId}")
	public ResponseEntity<ApiResponse> deactivateHrAccount(@PathVariable Long hrId)
	{
		return new ResponseEntity<ApiResponse>(adminService.deactivateHrById(hrId),HttpStatus.OK);
	}
	
	//get total number of jobs and hr as well as active jobs and hr
	// https://localhost:7878/admin/analysis
	@GetMapping("/analysis")
	public ResponseEntity<AnalysisResponseAdmin> getReport()
	{
		return new ResponseEntity<AnalysisResponseAdmin>(adminService.getReport(),HttpStatus.OK);
	}
}
