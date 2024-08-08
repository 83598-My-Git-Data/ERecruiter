package com.app.service;

import org.springframework.web.multipart.MultipartFile;

import com.app.payload.request.HrRequest;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.HrResponse;

public interface HrService {

	//get HR whole details
	public HrResponse getHrDetails();
	
	//update the hr details password etc
	public ApiResponse updateHr(HrRequest hr);
		
	//upload image 
	public ApiResponse uploadImage(MultipartFile file);
	
	//update image
	public ApiResponse updateImage(MultipartFile file);
	
	//remove image
	public ApiResponse removeImage();
	
}
