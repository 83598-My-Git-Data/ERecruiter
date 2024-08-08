package com.app.service;

import static com.app.utils.ApplicantHelper.findApplicantByUserId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.ApplicantEntity;
import com.app.entities.ApplicantJobId;
import com.app.entities.AppliedJob;
import com.app.entities.JobInfoEntity;
import com.app.entities.JobStatus;
import com.app.exception.ResourceNotFoundException;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.JobDetails;
import com.app.payload.response.JobInfoDetailsResponse;
import com.app.repository.ApplicantRepository;
import com.app.repository.AppliedJobRepository;
import com.app.repository.JobInfoRepository;
import com.app.security.FindAuthenticationDetails;

@Service
@Transactional
public class JobInfoServiceImpl implements JobInfoService {
	
	@Autowired
	private AppliedJobRepository appliedJobRepo;
	
	@Autowired
	private FindAuthenticationDetails findUser;
	
	@Autowired //To map entity with the DTO 
	private ModelMapper mapper;
	
	@Autowired
	private JobInfoRepository jobInfoRepo;
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	
	/**
	 * Apply to a job
	 * **/
	@Override
	public ApiResponse applyJobFun(Long jobId) {
		
		Long userId=findUser.getUserId();
		
		ApplicantJobId appliedJobId=new ApplicantJobId(jobId,userId);
		
		
		
		if(jobInfoRepo.existsById(jobId)) {
			
			AppliedJob appliedJobEntity	=new AppliedJob(appliedJobId,JobStatus.APPLIED);
						
			appliedJobRepo.save(appliedJobEntity);
			
			return new ApiResponse("Applicant with ID : " +userId+" Applied for job with ID : "+jobId);
		}
		
		return new ApiResponse("Job you're applying for job with ID : "+jobId+" does't exists");
		
	}
	
	
	/**
	 * UnApply job
	 * **/
	@Override
	public ApiResponse unApplyJobFun(Long jobId) {
		
		Long userId=findUser.getUserId();
		// TODO:add logic to check if job id exist in job table
		ApplicantJobId appliedJobId=new ApplicantJobId(jobId,userId);
		
		if(jobInfoRepo.existsById(jobId)) {
			
		appliedJobRepo.deleteById(appliedJobId);
		
		return new ApiResponse("Applicant with ID : " +userId+" UnApplied for job with ID : "+jobId);
		
		}
		return new ApiResponse("Job you're unApplying for job with ID : "+jobId+" does't exists");
	}


	/**
	 * Get Applied jobs
	 * **/
	@Override
	public List<JobInfoDetailsResponse> getAppliedJobFun() {
		Long userId=findUser.getUserId();


		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		
		List<AppliedJob> idList =appliedJobRepo.findAllAppliedJobByApplicant(applicant).
						orElseThrow(()-> new ResourceNotFoundException
								("AppliedJobId", "applicant", applicant.getId()));
		
		List<Long> jobIdList=new ArrayList<Long>();
		
		for (AppliedJob appliedJob : idList) {
			jobIdList.add(appliedJob.getId().getJobId());
		}
		
		List<JobInfoEntity> jobList=jobInfoRepo.findAllById(jobIdList);
		System.out.println(jobList.toString());
		return jobList.stream().map(jobInfo->mapper.map(jobInfo, JobInfoDetailsResponse.class)).
								collect(Collectors.toList());
	}
	
	
	/**
	 * Get SortListed jobs
	 * **/
	@Override
	public List<JobInfoDetailsResponse> getSortListedJobFun() {
		Long userId=findUser.getUserId();


		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		
		List<AppliedJob> appliedJobList =appliedJobRepo.findAllSortListedJobOfApplicant(applicant,JobStatus.INTERVIEW).
						orElseThrow(()-> new ResourceNotFoundException
								("AppliedJobId", "applicant", applicant.getId()));
		
		List<Long> jobIdList=new ArrayList<Long>();
		
		for (AppliedJob appliedJob : appliedJobList) {
			jobIdList.add(appliedJob.getId().getJobId());
		}
		
		List<JobInfoEntity> jobList=jobInfoRepo.findAllById(jobIdList);
		System.out.println(jobList.toString());
		return jobList.stream().map(jobInfo->mapper.map(jobInfo, JobInfoDetailsResponse.class)).
								collect(Collectors.toList());
	}
	
	
	/**
	 * Save a job
	 **/
	@Override
	public ApiResponse saveJobFun(Long jobId) {
		
		Long userId=findUser.getUserId();
				
		if(jobInfoRepo.existsById(jobId)) {
	
			//statically imported method from ApplicantHelper class
			//to find persistent ApplicantEntity by userId
			ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
			
			// Returns the value in case of non empty Optional
			// OR throws supplied exception
			
			//Find job by jobId from payload
			JobInfoEntity job=jobInfoRepo.findById(jobId).
					orElseThrow(()-> new ResourceNotFoundException
							("JOB", "jobId", userId));
			
			//removing applicant to job applicant List
			//and job to applicant Saved jobs
			job.addApplicant(applicant);
			
			return new ApiResponse("Applicant with ID : " +userId+" saved job with ID : "+jobId);
		}
		
		return new ApiResponse("Job you're saving for job with ID : "+jobId+" does't exists");
		
	}
	
	
	
	/**
	 * Unsave a job
	 **/
	@Override
	public ApiResponse unSaveJobFun(Long jobId) {
		
		Long userId=findUser.getUserId();
				
		if(jobInfoRepo.existsById(jobId)) {
			
			//statically imported method from ApplicantHelper class
			//to find persistent ApplicantEntity by userId
			ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
			
			// Returns the value in case of non empty Optional
			// OR throws supplied exception
			
			//Find job by jobId from payload
			JobInfoEntity job=jobInfoRepo.findById(jobId).
					orElseThrow(()-> new ResourceNotFoundException
							("JOB", "jobId", userId));
			//removing applicant to job applicant List
			//and job to applicant Saved jobs
			job.removeApplicant(applicant);
			
			return new ApiResponse("Applicant with ID : " +userId+" Unsaved job with ID : "+jobId);
		}
		
		return new ApiResponse("Job you're Unsaving for job with ID : "+jobId+" does't exists");
		
	}


	@Override
	public List<JobInfoDetailsResponse> getSavedJobFun() {

		Long userId=findUser.getUserId();
				
		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		
		return applicant.getJob().stream().
				map(jobInfo->mapper.map(jobInfo, JobInfoDetailsResponse.class)).
				collect(Collectors.toList());
	}


	@Override
	public List<JobDetails> availableJobFun() {
		List<JobInfoEntity> jobList =jobInfoRepo.findAll();
			
		return jobList.stream().
				map(jobInfo->mapper.map(jobInfo, JobDetails.class)).
				collect(Collectors.toList());
	}
	
	
	
	
	
}
