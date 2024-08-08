package com.app.service;

import java.util.List;

import com.app.payload.request.EducationRequest;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.EducationResponse;

public interface EducationService {

	
	List<EducationResponse> getEducationDetail(	);

	ApiResponse addEducationFun( EducationRequest education);

	ApiResponse updateEducationFun(EducationRequest education);

}
