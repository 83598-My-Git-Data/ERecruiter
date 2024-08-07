package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.UserEntity;
import com.app.payload.response.HrDetailsResponse;

public interface UserEntityRepository extends JpaRepository<UserEntity,Long>{
//derived finder 
	Optional<UserEntity> findByEmail(String email);
	
	/**
	 *Using a custom query to extract hr details with user(firstname lastname) etc
	*/
	 @Query("SELECT new com.app.payload.response.HrDetailsResponse(u.id,u.firstName, u.lastName,u.gender,u.email,u.phoneNumber,h.qualification,h.activeStatus) "
	 		+ "FROM UserEntity u JOIN HREntity h ON u.id = h.user.id ")
	 List<HrDetailsResponse> findUserAndHrInfo();

	Optional<UserEntity> findByEmailAndPassword(String email, String password);
	
}
