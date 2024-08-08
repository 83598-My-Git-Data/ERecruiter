package com.app.service;

import static com.app.utils.ApplicantHelper.findApplicantByUserId;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

//import com.amazonaws.services.s3.AmazonS3;
import com.app.entities.ApplicantEntity;
import com.app.entities.LanguageEntity;
import com.app.entities.SkillEntity;
import com.app.exception.ResourceNotFoundException;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.ApplicantResponse;
import com.app.payload.response.LanguageResponse;
import com.app.payload.response.SkillResponse;
import com.app.repository.ApplicantRepository;
import com.app.repository.LanguageEntityRepository;
import com.app.repository.SkillEntityRepository;
import com.app.repository.UserEntityRepository;
import com.app.security.FindAuthenticationDetails;

@Service
@Transactional
public class ApplicantServiceImpl implements ApplicantService {
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired //To map entity with the DTO 
	private ModelMapper mapper;
	
	@Autowired
	private FindAuthenticationDetails findUser;
	
	@Autowired
	private UserEntityRepository userRepo;
	
	@Autowired
	private SkillEntityRepository skillRepo;
	
	@Autowired
	private LanguageEntityRepository languageRepo;
	
	@Autowired
	private StorageService storageService;
	
//	@Autowired
//	private AmazonS3 s3Client;

	@Value("${application.bucket.name}")
    private String bucketName;
	
	
	/**
	 * getting profile details
	 * */
	@Override
	public ApplicantResponse getProfileInfo() {
		
		Long userId=findUser.getUserId();
		
		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
	

		ApplicantResponse response=mapper.map(applicant, ApplicantResponse.class);
		//if the resume or profile picture is removed --sets the values
		// as deleted in response
		if(applicant.getResumeLink().equals("deleted"))
			response.setResumeLink("deleted");	
//		else
//			response.setResumeLink(s3Client.getUrl(bucketName, applicant.getResumeLink()).toString());
//		if(applicant.getProfilePictureLink().equals("deleted"))
//			response.setProfilePictureLink("deleted");
//		else
//			response.setProfilePictureLink(s3Client.getUrl(bucketName, applicant.getProfilePictureLink()).toString());
//		
		return response; 
	}
	
	
	/**
	 * getting All Skills
	 * */
	@Override
	public List<SkillResponse> getAllSkills() {
		
		Long userId=findUser.getUserId();
		
		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
			
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
			
		List<SkillEntity> skillList=applicant.getSkills().stream().collect(Collectors.toList());
		
		return skillList.stream().
				map(skill -> mapper.map(skill, SkillResponse.class)).
				collect(Collectors.toList());
	}
	
	
	/**
	 * getting All langauges
	 * */
	@Override
	public List<LanguageResponse> getAllLanguages() {
		
		Long userId=findUser.getUserId();
		
		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
			
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		List<LanguageEntity> languageList=applicant.getLanguages().stream().collect(Collectors.toList());
		
		return languageList.stream().
				map(language -> mapper.map(language, LanguageResponse.class)).
				collect(Collectors.toList());
	}
	
	
	/**
	 * Updating Applicant Headline
	 * */
	@Override
	public ApiResponse updateHeadLine(String headLine) {
		Long userId=findUser.getUserId();
		
		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		applicant.setResumeHeadLine(headLine);
		applicantRepo.save(applicant);
		return new ApiResponse("Applicant headline updated with id "+applicant.getId());
	}

	
	/**
	 * Updating Applicant Skills
	 * */
	@Override
	public ApiResponse updateSkills(Long skillId) {
		Long userId=findUser.getUserId();
		System.out.println("SKill id :" + skillId);
		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		SkillEntity skillEntity=skillRepo.findById(skillId). 
				orElseThrow(()-> new ResourceNotFoundException
						("Skill", "name", skillId));
		
		applicant.removeSkill(skillEntity);

		applicantRepo.save(applicant);
		
		return new ApiResponse("Applicant Skills updated with id "+applicant.getId()+" with Skill id :" +skillId);
	}

	
	/**
	 * Update applicant Language
	 * **/
	@Override
	public ApiResponse updateLanguage(List<LanguageResponse> languageList) {
		
		Long userId=findUser.getUserId();

		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		for (LanguageResponse language : languageList) {
			LanguageEntity languageEntity=languageRepo.getLanguage(language.getName(),language.getProficiency()).
					orElseThrow(()-> new ResourceNotFoundException
							("Language", "name", language.getName()));
			applicant.getLanguages().add(languageEntity);
		}
		applicantRepo.save(applicant);
		
		return new ApiResponse("Applicant Language updated with id "+applicant.getId());
	}
	
	
	
	/**
	 * Updating Applicant Summary
	 * */
	@Override
	public ApiResponse updateProfileSmry(String summary) {
		
		Long userId=findUser.getUserId();

		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		applicant.setProfileSummary(summary);
		applicantRepo.save(applicant);
		return new ApiResponse("Applicant Profile summary updated with id "+applicant.getId());
	}

	/**
	 * Uploading the image to the AWS S3 Bucket
	 * and storing the file name in the database  
	 * */
	@Override
	public ApiResponse uploadImage(MultipartFile file) {
		Long userId=findUser.getUserId();
		ApplicantEntity applicant=applicantRepo.findById(userId).orElseThrow();
		String filename=storageService.uploadFile("ApplicantId_"+userId, file);
		applicant.setProfilePictureLink(filename);
		return new ApiResponse("Image Uploaded Successfully");
	}

	/**
	 * Update the image in AWS S3 bucket
	 * 1.it will delete the object in s3 bucket than
	 * 2.it will upload the new image
	 * */
//	@Override
//	public ApiResponse updateImage(MultipartFile file) {
//		Long userId=findUser.getUserId();
//		ApplicantEntity applicant=applicantRepo.findById(userId).orElseThrow();
//		String fileName=applicant.getProfilePictureLink();
//		String deletedFileName=storageService.deleteFile(fileName);
//		if(deletedFileName.equals(fileName))
//		{
//			fileName=storageService.uploadFile("UserID_"+userId, file);
//			applicant.setProfilePictureLink(deletedFileName);
//		}
//		return new ApiResponse(fileName+" image update successfully");
//	}

	/**
	 * Removes the image from the AWS S3
	 * */
	@Override
	public ApiResponse removeImage() {
		Long userId = findUser.getUserId();
		ApplicantEntity applicant=applicantRepo.findById(userId).orElseThrow();
		String fileName=applicant.getProfilePictureLink();
//		storageService.deleteFile(fileName);
		applicant.setProfilePictureLink("deleted");
		return new ApiResponse("image removed");
	}

	/**
	 * uploading resume to the
	 * AWS S3 and setting the resume column in database
	 * */
	@Override
	public ApiResponse uploadResume(MultipartFile file) {
		Long userId=findUser.getUserId();
		ApplicantEntity applicant=applicantRepo.findById(userId).orElseThrow();
		String filename=storageService.uploadFile("Resume_ApplicantId_"+userId, file);
		applicant.setResumeLink(filename);
		return new ApiResponse("Resume uploaded of :"+userId);
	}


	/**
	 * Updating the resume as same as 
	 * updating image
	 * */
//	@Override
//	public ApiResponse updateResume(MultipartFile file) {
//		Long userId=findUser.getUserId();
//		ApplicantEntity applicant=applicantRepo.findById(userId).orElseThrow();
//		String fileName=applicant.getResumeLink();
//		String deletedFileName=storageService.deleteFile(fileName);
//		if(deletedFileName.equals(fileName))
//		{
//			fileName=storageService.uploadFile("UserID_"+userId, file);
//			applicant.setResumeLink(deletedFileName);
//		}
//		return new ApiResponse(fileName+" resume update successfully");
//	}

	/**
	 * Remove resume from AWS S3
	 * */
	@Override
	public ApiResponse removeResume() {
		Long userId=findUser.getUserId();
		ApplicantEntity applicant=applicantRepo.findById(userId).orElseThrow();
		String fileName=applicant.getResumeLink();
//		storageService.deleteFile(fileName);
		applicant.setResumeLink("deleted");
		return new ApiResponse("resume removed");
	}


	@Override
	public ApiResponse addSkill(Long skillId) {
		Long userId=findUser.getUserId();

		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		SkillEntity skillEntity=skillRepo.findById(skillId). 
				orElseThrow(()-> new ResourceNotFoundException
						("Skill", "name", skillId));
		applicant.getSkills().add(skillEntity);
		applicantRepo.save(applicant);
		return new ApiResponse("Skill added to applicant skill list wit applicant id : "+ applicant.getId());
	}
	
	

}
