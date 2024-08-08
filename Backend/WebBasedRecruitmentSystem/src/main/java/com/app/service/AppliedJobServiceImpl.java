package com.app.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.ApplicantJobId;
import com.app.entities.AppliedJob;
import com.app.entities.JobStatus;
import com.app.exception.ResourceNotFoundException;
import com.app.payload.request.UpdateStatusRequest;
import com.app.payload.response.ApiResponse;
import com.app.repository.AppliedJobRepository;


@Service
@Transactional
public class AppliedJobServiceImpl implements AppliedJobService {
	
	@Autowired
	private AppliedJobRepository appliedJobRepo;
		
	@Override
	public ApiResponse updateStatusFun(UpdateStatusRequest status) {
			
		AppliedJob appliedJob =appliedJobRepo.findById(new ApplicantJobId(status.getJobId(),status.getApplicantId())).
						orElseThrow(()-> new ResourceNotFoundException
								("Applied Job", "Applicant ID", status.getApplicantId()));
		
		if(status.getStatus().equals(JobStatus.INTERVIEW)) {
			appliedJob.setStatus(JobStatus.INTERVIEW);
			return new ApiResponse("AppliedJob status updated with jobId "+status.getJobId());
		}
		else if(status.getStatus().equals(JobStatus.REJECTED)) {
			appliedJob.setStatus(JobStatus.REJECTED);
			return new ApiResponse("AppliedJob status updated with jobId "+status.getJobId());
		}
		appliedJobRepo.save(appliedJob);
		
		return new ApiResponse("Failed to update status with jobId "+status.getJobId());
	}

}
