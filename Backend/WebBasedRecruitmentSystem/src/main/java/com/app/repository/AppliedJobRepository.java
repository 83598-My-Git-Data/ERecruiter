package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.ApplicantEntity;
import com.app.entities.ApplicantJobId;
import com.app.entities.AppliedJob;
import com.app.entities.JobStatus;
import com.app.payload.response.ApplicantAndJobInfo;

public interface AppliedJobRepository extends JpaRepository<AppliedJob, ApplicantJobId> {

	/**
	 * JPQL query selects a new ApplicantAndJobInfo DTO, 
	 * combining the first name and last name of the user as applicantName, 
	 * the jobId from the AppliedJob entity, and the status from the AppliedJob entity. 
	 * It joins the AppliedJob entity with the UserEntity based on the applicantId and id fields
	 * */
	@Query("SELECT NEW com.app.payload.response.ApplicantAndJobInfo(u.id,u.firstName || ' ' || u.lastName AS applicantName, aj.id.jobId, aj.status) " +
	        "FROM AppliedJob aj " +
	        "JOIN UserEntity u ON aj.id.applicantId = u.id " +
	        "WHERE aj.id.jobId = :jobId")
	List<ApplicantAndJobInfo> getApplicantInfoByJobId(@Param("jobId") Long jobId);
	
	
	
	/**
	 * Get Applied jobs by particular applicant
	 * */
	@Query("select aj from AppliedJob aj where aj.applicant=:applicant")
	Optional<List<AppliedJob>> findAllAppliedJobByApplicant(@Param("applicant") ApplicantEntity applicant);
	
	/**
	 * Get SortListed jobs of particular applicant
	 * */
	@Query("select aj from AppliedJob aj where aj.applicant=:applicant and aj.status=:status")
	Optional<List<AppliedJob>> findAllSortListedJobOfApplicant(@Param("applicant") ApplicantEntity applicant,@Param("status")JobStatus status);
}
