package com.app.service;

import static com.app.utils.ApplicantHelper.findApplicantByUserId;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.ApplicantEntity;
import com.app.entities.ProjectEntity;
import com.app.exception.ResourceNotFoundException;
import com.app.payload.request.ProjectRequest;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.ProjectResponse;
import com.app.repository.ApplicantRepository;
import com.app.repository.ProjectEntityRepository;
import com.app.repository.UserEntityRepository;
import com.app.security.FindAuthenticationDetails;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectEntityRepository projectRepo;
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private UserEntityRepository userRepo;
	
	@Autowired //To map entity with the DTO 
	private ModelMapper mapper;
	
	@Autowired
	private FindAuthenticationDetails findUser;
	
	
	/**
	 * Get applicant Project
	 * **/
	@Override
	public List<ProjectResponse> getProjectDetail() {
		
		Long userId=findUser.getUserId();
		

		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		
		List<ProjectEntity> projectList =projectRepo.findAllByApplicant(applicant).
				orElseThrow(()-> new ResourceNotFoundException
						("project in project service","Applicant ID", applicant.getId()));
		
		return  projectList.stream().
				map(project -> mapper.map(project, ProjectResponse.class)).
				collect(Collectors.toList());
	}
	
	
	/**
	 * Add applicant Project
	 * **/
	@Override
	public ApiResponse addProjectFun(ProjectRequest projectDTO) {
		
		Long userId=findUser.getUserId();

		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		ProjectEntity projectEntity=mapper.map(projectDTO, ProjectEntity.class);
		projectEntity.setApplicant(applicant);
		projectRepo.save(projectEntity);
		
		return new ApiResponse("Applicant Project added with id "+applicant.getId());
	}
	
	
	/**
	 * Update applicant Project
	 * **/
	@Override
	public ApiResponse updateProjectFun(ProjectRequest projectDTO) {
		
		Long userId=findUser.getUserId();

		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
	
		
		ProjectEntity projectEntity=projectRepo.findById(projectDTO.getId()).
					orElseThrow(()-> new ResourceNotFoundException
						("Project in project service", "project ID", projectDTO.getId()));

		projectEntity.setProjectDescription(projectDTO.getProjectDescription());
		projectEntity.setProjectEndDate(projectDTO.getProjectEndDate());
		projectEntity.setProjectStartDate(projectDTO.getProjectStartDate());
		projectEntity.setProjectStatus(projectDTO.getProjectStatus());
		projectEntity.setProjectTitle(projectDTO.getProjectTitle());
		projectEntity.setApplicant(applicant);
		projectRepo.save(projectEntity);
		
		return new ApiResponse("Applicant Project added with id "+applicant.getId());
	}


	@Override
	public ApiResponse deleteProjectFun(Long projectId) {
		projectRepo.deleteById(projectId);
		return new ApiResponse("Applicant project deleted with project id : "+projectId);
	}
	
	
	
	
	
	


}
