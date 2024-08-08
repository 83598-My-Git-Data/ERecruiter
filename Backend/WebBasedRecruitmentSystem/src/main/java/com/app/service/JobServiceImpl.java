package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.DepartmentEntity;
import com.app.entities.HREntity;
import com.app.entities.JobInfoEntity;
import com.app.exception.ResourceNotFoundException;
import com.app.exception.UnauthorizedAccessException;
import com.app.payload.request.JobDetailsRequest;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.ApplicantAndJobInfo;
import com.app.payload.response.JobInfoDetailsResponse;
import com.app.repository.AppliedJobRepository;
import com.app.repository.DepartmentEntityRepository;
import com.app.repository.HREntityRepository;
import com.app.repository.JobInfoRepository;
import com.app.security.FindAuthenticationDetails;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private HREntityRepository hrRepo;

	@Autowired
	private JobInfoRepository jobRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private DepartmentEntityRepository deptRepo;

	@Autowired
	private FindAuthenticationDetails userDetails;
	
	@Autowired 
	private AppliedJobRepository appliedJobRepo;
	
	@Override
	public ApiResponse createJob(JobDetailsRequest job) {

		// get the respective HR info from HR table
		HREntity hr = hrRepo.findById(userDetails.getUserId()).orElse(null);

		// get the department from the table
		DepartmentEntity department = deptRepo.findById(job.getDepId())
				.orElseThrow(() -> new ResourceNotFoundException("Department", "id", job.getDepId()));

		// mapping the payload to job info
		JobInfoEntity createdJob = mapper.map(job, JobInfoEntity.class);
		createdJob.setHr(hr);
		createdJob.setDepartment(department);
		jobRepo.save(createdJob);
		return new ApiResponse("job created");
	}

	/**
	 * get the all jobs created by the HR
	 * */
	@Override
	public List<JobInfoDetailsResponse> getAllJobsCreatedByHr() {	
		return jobRepo.findAllJobsByHrId(userDetails.getUserId());
	}

	/**
	 * get the job by job id
	 * */	
	@Override
	public JobInfoDetailsResponse getJobByHrAndJobId(Long jobId) {
		return jobRepo.findJobByHrIdAndJobId(userDetails.getUserId(), jobId);
	}

	/**
	 * Deactivating the current job by job id and hr id
	 * */
	@Override
	public ApiResponse deactivateJobById(Long JobId) {
		if (!isJobOwnedByHr(JobId, userDetails.getUserId())) {
	        throw new UnauthorizedAccessException("You are not authorized to deactivate this job.");
	    }
		Long hrId=userDetails.getUserId();
		jobRepo.updateJobStatusToFalse(JobId, hrId);
		return new ApiResponse("job status changed to deactivated");
	}

	/**
	 * Update the job details
	 * */
	@Override
	public ApiResponse updateJobDetails(JobDetailsRequest job,Long JobId) {
	JobInfoEntity existingJobInfo=jobRepo.findById(JobId).orElseThrow(
			()->new ResourceNotFoundException("Job", "id", JobId));
	if (!isJobOwnedByHr(JobId, userDetails.getUserId())) {
        throw new UnauthorizedAccessException("You are not authorized to update this job");
    }
	//this will map the existing job with incoming changed details in request body
	mapper.map(job, existingJobInfo);
	//after this line the changes will saved to the database
	return new ApiResponse("Job updated");
	}

	/**
	 * Get the applicant info --name and the job they have applied
	 * and status
	 * */
	@Override
	public List<ApplicantAndJobInfo> getApplicants(Long jobId) {
		if (!isJobOwnedByHr(jobId, userDetails.getUserId())) {
            throw new UnauthorizedAccessException("You are not authorized to view applicants for this job.");
        }
			return appliedJobRepo.getApplicantInfoByJobId(jobId);
	}

	/**
	 * To check if the job belongs to the particular hr or not
	 * */
	  private boolean isJobOwnedByHr(Long jobId, Long userId) {
		  JobInfoEntity jobInfo= jobRepo.findById(jobId).orElseThrow(()->new ResourceNotFoundException("Job", "id", jobId));
		  if(jobInfo.getHr().getId()==userDetails.getUserId())
			  return true;
		  return false;
	  }
}
