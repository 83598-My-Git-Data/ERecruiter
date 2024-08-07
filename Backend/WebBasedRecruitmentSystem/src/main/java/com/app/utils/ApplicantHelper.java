package com.app.utils;

import com.app.entities.ApplicantEntity;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.ApplicantRepository;


//helper class to overcome frequently writing logic to find Applicant entity from user

public class ApplicantHelper {
	
	public static ApplicantEntity findApplicantByUserId(Long userId,ApplicantRepository applicantRepo) 
	{
		//finds persistent Applicant entity by user
		ApplicantEntity applicant=applicantRepo.findById(userId).
				orElseThrow(()-> new ResourceNotFoundException
						("Applicant", "User Id", userId));
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		return applicant;
	}
}
