package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.HREntity;
import com.app.entities.UserEntity;

public interface HREntityRepository extends JpaRepository<HREntity, Long> {

	/**
	 * get active HR counts using custom query
	 * */
	@Query("SELECT COUNT(h) from HREntity h where activeStatus=true")
	Long countActiveUser();
	
	
	/**
	 * User defined method to find hr details by user object
	 * */
	Optional<HREntity> findByUser(UserEntity user);
}
