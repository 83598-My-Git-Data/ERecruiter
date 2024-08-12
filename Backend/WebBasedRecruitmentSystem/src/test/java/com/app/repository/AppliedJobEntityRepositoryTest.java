package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.ApplicantEntity;
import com.app.entities.ApplicantJobId;
import com.app.entities.AppliedJob;
import com.app.entities.JobInfoEntity;
import com.app.entities.JobStatus;
import com.app.exception.ResourceNotFoundException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AppliedJobEntityRepositoryTest {
	
	@Autowired
	private AppliedJobRepository appliedJobRepo;
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private JobInfoRepository jobRepo;
	
	@Test
	void testAddAppliedJob() {
		
		ApplicantEntity applicant1= applicantRepo.findById(4l).orElseThrow(()-> new ResourceNotFoundException
				("Address", "Applicant ID", 1l));
		ApplicantEntity applicant2= applicantRepo.findById(5l).orElseThrow(()-> new ResourceNotFoundException
				("Address", "Applicant ID", 1l));
		
		
		JobInfoEntity jobInfo1= jobRepo.findById(1l).orElseThrow();
		JobInfoEntity jobInfo2= jobRepo.findById(2l).orElseThrow();
		
		ApplicantJobId appliedJobId1=new ApplicantJobId(jobInfo1.getJobId(),applicant1.getId());
		ApplicantJobId appliedJobId2=new ApplicantJobId(jobInfo2.getJobId(),applicant2.getId());
		List<AppliedJob> list = List.of(
				new AppliedJob(appliedJobId1,JobStatus.APPLIED),
				new AppliedJob(appliedJobId2,JobStatus.APPLIED)
				);
		List<AppliedJob> list2 = appliedJobRepo.saveAll(list);
		assertEquals(2, list2.size());
		
	}
	
	@Test
	void testFindAppliedJob() {
		AppliedJob ap =appliedJobRepo.findById(new ApplicantJobId(1l,4l)).orElseThrow(()-> new ResourceNotFoundException
				("Address", "Applicant ID", 1l));
		System.out.println("Applied job :"+ap.getId().getApplicantId()+","+ap.getId().getJobId()+","+ap.getStatus());
		ap.setStatus(JobStatus.INTERVIEW);
		appliedJobRepo.save(ap);
	}
}
