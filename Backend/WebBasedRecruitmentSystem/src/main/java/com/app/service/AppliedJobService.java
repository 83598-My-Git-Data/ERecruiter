package com.app.service;

import com.app.payload.request.UpdateStatusRequest;
import com.app.payload.response.ApiResponse;

public interface AppliedJobService {
	
	//update job status
	ApiResponse updateStatusFun(UpdateStatusRequest status);
}
