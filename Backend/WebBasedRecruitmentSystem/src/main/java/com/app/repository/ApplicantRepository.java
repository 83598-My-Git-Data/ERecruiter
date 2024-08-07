package com.app.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ApplicantEntity;
import com.app.entities.UserEntity;


public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long> {
	
	/**
	 * Get applicant by user
	 * **/
	Optional<ApplicantEntity> findByUser(UserEntity user);
}
