package com.app.service;

import java.util.List;

import com.app.payload.request.ProjectRequest;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.ProjectResponse;

public interface ProjectService {

	List<ProjectResponse> getProjectDetail();

	ApiResponse addProjectFun( ProjectRequest project);

	ApiResponse updateProjectFun(ProjectRequest project);

	ApiResponse deleteProjectFun(Long projectId);

}
