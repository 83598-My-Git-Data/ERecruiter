package com.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.JobInfoEntity;
import com.app.payload.response.JobDetailsWithUsernameResponse;
import com.app.payload.response.JobInfoDetailsResponse;

public interface JobInfoRepository extends JpaRepository<JobInfoEntity, Long>{


	/**
	 * Using a custom query to retrieve job details along with user information
	 * */
	@Query("SELECT new com.app.payload.response.JobDetailsWithUsernameResponse(j.jobId, j.jobTitle, u.firstName, u.lastName, j.jobCreatedDate, j.applicationDeadline, j.location, j.vacancies,j.status) "
			+ "FROM UserEntity u JOIN JobInfoEntity j ON u.id = j.hr.id")
	List<JobDetailsWithUsernameResponse> findAllJobs();
	
	/**
	 * custom query to find no. of active jobs 
	 * */
	@Query("SELECT COUNT(j) FROM JobInfoEntity j WHERE j.applicationDeadline > CURRENT_DATE")
	Long countActiveJobs();
	
	
	/**
	 * Get jobs created by the HR using HR id with JPQL
	 * */
	@Query("SELECT new com.app.payload.response.JobInfoDetailsResponse(j.jobId, j.jobTitle, j.experienceRequired, j.workSchedule, " +
	        "j.salary, j.applicationDeadline, j.jobCreatedDate, j.qualification, j.department.departmentName, j.vacancies,j.status,j.description) " +
	        "FROM JobInfoEntity j WHERE j.hr.id = :hrId")
	List<JobInfoDetailsResponse> findAllJobsByHrId(@Param("hrId") Long hrId);
	
	/**
	 * Get job by job id created by the HR using HR id with JPQL 
	 * */
	@Query("SELECT new com.app.payload.response.JobInfoDetailsResponse(j.jobId, j.jobTitle, j.experienceRequired, j.workSchedule, " +
	        "j.salary, j.applicationDeadline, j.jobCreatedDate, j.qualification, j.department.departmentName, j.vacancies,j.status,j.description) " +
	        "FROM JobInfoEntity j WHERE j.hr.id = :hrId and j.id= :jobId")
	JobInfoDetailsResponse findJobByHrIdAndJobId(@Param("hrId")Long hrId,@Param("jobId")Long jobId);
	
	
	/**
	 * @Modifying annotation is used to indicate that the query is modifying the state of the database
	 * */
	@Modifying
    @Query("UPDATE JobInfoEntity j SET j.status = false WHERE j.jobId = :jobId AND j.hr.id = :hrId")
    int updateJobStatusToFalse(@Param("jobId") Long jobId, @Param("hrId") Long hrId);
	
	
	
}
