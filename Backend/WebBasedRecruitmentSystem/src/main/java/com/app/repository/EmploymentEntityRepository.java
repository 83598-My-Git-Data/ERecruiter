package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.ApplicantEntity;
import com.app.entities.EmploymentEntity;

public interface EmploymentEntityRepository extends JpaRepository<EmploymentEntity, Long> {
	
	Optional<List<EmploymentEntity>> findAllByApplicant(ApplicantEntity applicant);
	
	/**
	 * get Employment Entity with Current Company name  using custom query
	 * */
	@Query("SELECT employment from EmploymentEntity employment where currentCompanyName=:currentCompanyName")
	Optional<EmploymentEntity> getLanguage(@Param("currentCompanyName") String currentCompanyName);
}
