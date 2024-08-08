package com.app.service;

import com.app.payload.request.SchoolingRequest;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.SchoolingResponse;

public interface SchoolingService {
	SchoolingResponse getSchooling();

	ApiResponse addSchoolingFun(SchoolingRequest schooling);

	ApiResponse updateSchoolingFun(SchoolingRequest schooling);
}
