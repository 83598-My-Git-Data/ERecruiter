package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.app.entities.HREntity;
import com.app.entities.UserEntity;
import com.app.entities.UserRole;
import com.app.payload.request.HrRequest;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.HrResponse;
import com.app.repository.HREntityRepository;
import com.app.repository.UserEntityRepository;
import com.app.security.FindAuthenticationDetails;

@Service
@Transactional
public class HrServiceImpl implements HrService {

	@Autowired
	private UserEntityRepository userRepo;

	@Autowired
	private HREntityRepository hrRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private FindAuthenticationDetails user;

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private AmazonS3 s3Client;

	@Value("${application.bucket.name}")
    private String bucketName;
	
	@Override
	public HrResponse getHrDetails() {
		Long userId = user.getUserId();
		UserEntity user = userRepo.findById(userId).orElseThrow();
		HREntity hrEntity = hrRepo.findByUser(user).orElseThrow();
		hrEntity.setUser(user);
		String fileName=hrEntity.getImageURL();
		HrResponse hrResponse = mapper.map(hrEntity, HrResponse.class);
		if(hrResponse.getImageURL().equals("deleted"))
			hrResponse.setImageURL("deleted");
		else
			hrResponse.setImageURL(s3Client.getUrl(bucketName,fileName).toString());
		System.out.println(hrResponse);
		return hrResponse;
	}

	// update hr info
	@Override
	public ApiResponse updateHr(HrRequest hr) {
		if (hr.getUser() != null && hr.getUser().getEmail() != null) {

			// set the hr user id also same as user id
			hr.getUser().setId(user.getUserId());

			// encode the plain password
			hr.getUser().setPassword(encoder.encode(hr.getUser().getPassword()));
			// set the role as hr
			hr.getUser().setRole(UserRole.ROLE_HR);
			// Retrieve the existing user from the database
			UserEntity existingUser = userRepo.findById(hr.getId()).orElse(null);
			// Retrieve the existing hr info from the database
			HREntity exsistingHr = hrRepo.findById(hr.getId()).orElseThrow();
			System.out.println(existingUser);
			if (existingUser != null) {
				// Map and save the updated user details
				mapper.map(hr.getUser(), existingUser);
				userRepo.save(existingUser);

				// Map and save the HR entity
				mapper.map(hr, exsistingHr);
				hrRepo.save(exsistingHr);

				return new ApiResponse("Hr Updated");
			} else {
				return new ApiResponse("User not found");
			}
		} else {
			return new ApiResponse("Email cannot be null");
		}
	}

	
	/**
	 * Uploading the image to the AWS S3 Bucket
	 * and storing the file name in the database  
	 * */
	@Override
	public ApiResponse uploadImage(MultipartFile file) {
		Long userId = user.getUserId();
		HREntity hrEntity = hrRepo.findById(userId).orElseThrow();
		String fileName=storageService.uploadFile("UserID_"+userId, file);
		hrEntity.setImageURL(fileName);
		return new ApiResponse("Image Uploaded Successfully");
	}

	
	/**
	 * Update the image in AWS S3 bucket
	 * 1.it will delete the object in s3 bucket than
	 * 2.it will upload the new image
	 * */
	@Override
	public ApiResponse updateImage(MultipartFile file) {
		Long userId = user.getUserId();
		HREntity hrEntity = hrRepo.findById(userId).orElseThrow();
		String fileName=hrEntity.getImageURL();
		String deletedFileName=storageService.deleteFile(fileName);
		if(deletedFileName.equals(fileName))
		{
			fileName=storageService.uploadFile("UserID_"+userId, file);
			hrEntity.setImageURL(fileName);
		}
		return new ApiResponse(fileName+" image update successfully");
	}

	/**
	 * Removes the image from the AWS S3
	 * */
	@Override
	public ApiResponse removeImage() {
		Long userId = user.getUserId();
		HREntity hrEntity = hrRepo.findById(userId).orElseThrow();
		String fileName=hrEntity.getImageURL();
		storageService.deleteFile(fileName);
		hrEntity.setImageURL("deleted");
		return new ApiResponse("image removed");
	}

}
