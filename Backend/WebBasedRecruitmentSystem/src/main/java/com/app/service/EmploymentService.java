package com.app.service;

import java.util.List;

import com.app.payload.request.EmploymentRequest;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.EmploymentResponse;

public interface EmploymentService {

	List<EmploymentResponse> getAllEmployment();

	ApiResponse addEmployementFun(EmploymentRequest employment);

	ApiResponse UpdateEmployementFun(EmploymentRequest employment);

	ApiResponse deleteEmployementFun(Long empID);
	
}
	