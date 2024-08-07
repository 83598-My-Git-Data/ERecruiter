package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ApplicantEntity;
import com.app.entities.SchoolingEntity;

public interface SchoolingEntityRepository extends JpaRepository<SchoolingEntity, Long> {
	
	/**
	 * Finding schooling details by applicant
	 * **/
	Optional<SchoolingEntity> findByApplicant(ApplicantEntity applicant);
}
