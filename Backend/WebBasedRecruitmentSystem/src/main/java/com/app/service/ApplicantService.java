package com.app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.payload.response.ApiResponse;
import com.app.payload.response.ApplicantResponse;
import com.app.payload.response.LanguageResponse;
import com.app.payload.response.SkillResponse;

public interface ApplicantService {

	public ApplicantResponse getProfileInfo();
		
	public List<SkillResponse> getAllSkills();
	
	public List<LanguageResponse> getAllLanguages();

	public ApiResponse updateHeadLine( String headLine);

	public ApiResponse updateSkills(Long skillId);

	public ApiResponse updateLanguage( List<LanguageResponse> languages);

	public ApiResponse updateProfileSmry(String summary);


	//upload image
	public ApiResponse uploadImage(MultipartFile file);
	//update image
//	public ApiResponse updateImage(MultipartFile file);
	//remove image
	public ApiResponse removeImage();
	
	//upload resume
	public ApiResponse uploadResume(MultipartFile file);
	//update resume
//	public ApiResponse updateResume(MultipartFile file);
	//remove resume
	public ApiResponse removeResume();
	//add skill
	public ApiResponse addSkill(Long skillId);
		
}
