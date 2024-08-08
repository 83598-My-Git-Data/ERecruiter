package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ApplicantEntity;
import com.app.entities.ProjectEntity;

public interface ProjectEntityRepository extends JpaRepository<ProjectEntity, Long> {
	
	/**
	 * Finding Projects by applicant
	 * **/
	Optional<List<ProjectEntity>> findAllByApplicant(ApplicantEntity applicant);
}
