package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.payload.request.AddressRequest;
import com.app.payload.request.BasicDetailRequest;
import com.app.payload.request.EducationRequest;
import com.app.payload.request.EmploymentRequest;
import com.app.payload.request.PersonalDetailRequest;
import com.app.payload.request.ProjectRequest;
import com.app.payload.request.SchoolingRequest;
import com.app.payload.response.AddressResp;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.ApplicantResponse;
import com.app.payload.response.EducationResponse;
import com.app.payload.response.EmploymentResponse;
import com.app.payload.response.LanguageResponse;
import com.app.payload.response.ProjectResponse;
import com.app.payload.response.SchoolingResponse;
import com.app.payload.response.SkillResponse;
import com.app.payload.response.UserDetailsResp;
import com.app.service.AddressService;
import com.app.service.ApplicantService;
import com.app.service.EducationService;
import com.app.service.EmploymentService;
import com.app.service.ProjectService;
import com.app.service.SchoolingService;
import com.app.service.UserService;

@RestController
@RequestMapping("/applicant")
public class ApplicantController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private UserService userService;

	@Autowired
	private ApplicantService applicantService;

	@Autowired
	private EducationService educationService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private SchoolingService schoolingService;

	@Autowired
	private EmploymentService employmentService;

	
	
	public ApplicantController() {

	}

	// Rest API end point
	// URL : http://localhost:7878/applicant/address
	// Method : GET
	// Res : AddressResDTO
	@GetMapping("/address")
	public ResponseEntity<?> getAddressDetails() {

		System.out.println("inside address endpoint");

		AddressResp addressDTO = addressService.getAddress();

		return new ResponseEntity<>(addressDTO, HttpStatus.OK);
		// send response as address details to be displayed on profile section of
		// applicant
	}

	// Rest API end point
	// URL : http://localhost:7878/applicant/userDetail
	// Method : GET
	// Res : userDetailsResp
	
	@GetMapping("/user-detail")
	public ResponseEntity<?> getBasicDetail() {
		System.out.println("inside user-detail endpoint");
		
		UserDetailsResp userDetailsResp = userService.getBasicDetail();
		
		return new ResponseEntity<>(userDetailsResp, HttpStatus.OK);
		// send response as user details to be displayed on profile section of applicant
	}

	// Rest API end point
	// URL : http://localhost:7878/applicant/profileInfo
	// Method : GET
	// Res : ApplicantResponse
	@GetMapping("/profile-info")
	public ResponseEntity<?> getProfileInfo() {

		System.out.println("inside profileInfo endpoint");
		
		ApplicantResponse applicantResponse = applicantService.getProfileInfo();

		return new ResponseEntity<>(applicantResponse, HttpStatus.OK);
		// send response as applicant details to be displayed on profile section of
		// applicant
	}

	// Rest API end point
	// URL : http://localhost:7878/applicant/educationDetails
	// Method : GET
	// Res : EducationResponse
	@GetMapping("/education-details")
	public ResponseEntity<?> getEducationDetails() {

		System.out.println("inside Education endpoint");
		List<EducationResponse> educationResponseList = educationService.getEducationDetail();

		return new ResponseEntity<>(educationResponseList, HttpStatus.OK);
		// send response as education details to be displayed on profile section of
		// applicant
	}

	// Rest API end point
	// URL : http://localhost:7878/applicant/projectDetails
	// Method : GET
	// Res : projectResponse
	@GetMapping("/project-details")
	public ResponseEntity<?> getProjectDetails() {

		System.out.println("inside project endpoint");
		
		List<ProjectResponse> projectResponseList = projectService.getProjectDetail();

		return new ResponseEntity<>(projectResponseList, HttpStatus.OK);
		// send response as project details to be displayed on profile section of
		// applicant
	}

	// Rest API end point
	// URL : http://localhost:7878/applicant/skills
	// Method : GET
	// Res : SkillResponse
	@GetMapping("/skills")
	public ResponseEntity<?> getSkills() {

		System.out.println("inside getSkills of applicant");

		List<SkillResponse> skillResponseList = applicantService.getAllSkills();

		return new ResponseEntity<>(skillResponseList, HttpStatus.OK);
		// send response as skill list to be displayed on profile section of applicant
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/skills
	// Method : GET
	// Res : SkillResponse
	@PutMapping("/add-skill/{skillId}")
	public ResponseEntity<?> addSkills(@PathVariable Long skillId) {
			System.out.println("inside addSkills endpoint");
			ApiResponse apiResponse = applicantService.addSkill(skillId);
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		// send response as skill list to be displayed on profile section of applicant
	}

	// Rest API end point
	// URL : http://localhost:7878/applicant/languages
	// Method : GET
	// Res : LanguagesResponse
	@GetMapping("/languages")
	public ResponseEntity<?> getLanguages() {

		System.out.println("inside language endpoint");

		List<LanguageResponse> LanguageResponseList = applicantService.getAllLanguages();

		return new ResponseEntity<>(LanguageResponseList, HttpStatus.OK);
		// send response as skill list to be displayed on profile section of applicant
	}

	// Rest API end point
	// URL : http://localhost:7878/applicant/schooling
	// Method : GET
	// Res : SchoolingResponse
	@GetMapping("/schooling")
	public ResponseEntity<?> getSchoolingDetails() {

		System.out.println("inside schooling endpoint");

		SchoolingResponse schoolingResponse = schoolingService.getSchooling();

		return new ResponseEntity<>(schoolingResponse, HttpStatus.OK);
		// send response as schooling details to be displayed on profile section of
		// applicant
	}

	// Rest API end point
	// URL : http://localhost:7878/applicant/employment
	// Method : GET
	// Res : EmploymentResponse
	@GetMapping("/employment")
	public ResponseEntity<?> getEmployment() {

		System.out.println("inside employment endpoint");

		List<EmploymentResponse> EmploymentResponseList = employmentService.getAllEmployment();

		return new ResponseEntity<>(EmploymentResponseList, HttpStatus.OK);
		// send response as skill list to be displayed on profile section of applicant
	}

	// Rest API end point
	// URL : http://localhost:7878/applicant/basicDetails
	// Method : PUT
	// Payload : BasicDetailRequest
	@PutMapping("/basic-details")
	public ResponseEntity<ApiResponse> updateBasicDetails(@RequestBody @Valid BasicDetailRequest basicDetails ) {
	
		ApiResponse apiResponse=userService.updateBasicDetails(basicDetails);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/headline
	// Method : PUT
	// Payload : String
	@PutMapping("/headline")
	public ResponseEntity<ApiResponse> updateHeadLine(@RequestBody @Valid String headLine ) {
	
		ApiResponse apiResponse=applicantService.updateHeadLine(headLine);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/skills/{skillId}
	// Method : Delete
	// Payload : Long
	@DeleteMapping("/skills/{skillId}")
	public ResponseEntity<ApiResponse> updateSkills(@PathVariable @Valid Long skillId) {
	
		ApiResponse apiResponse=applicantService.updateSkills(skillId);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/skills
	// Method : PUT
	// Payload : List<LanguageEntity>
	@PutMapping("/language")
	public ResponseEntity<ApiResponse> updateLanguage(@RequestBody @Valid List<LanguageResponse> languages) {
	
		ApiResponse apiResponse=applicantService.updateLanguage(languages);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/profileSummary
	// Method : PUT
	// Payload : String
	@PutMapping("/profile-summary")
	public ResponseEntity<ApiResponse> updateProfileSummary(@RequestBody @Valid String summary ) {
	
		ApiResponse apiResponse=applicantService.updateProfileSmry(summary);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/employment
	// Method : PUT
	// Payload : EmployementRequest
	@PostMapping("/employment")
	public ResponseEntity<ApiResponse> addEmployment(@RequestBody @Valid EmploymentRequest employment ) {
		System.out.println("Inside emp post methos");
		ApiResponse apiResponse=employmentService.addEmployementFun(employment);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/employment
	// Method : PUT
	// Payload : EmployementRequest
	@PutMapping("/employment")
	public ResponseEntity<ApiResponse> UpdateEmployment(@RequestBody @Valid EmploymentRequest employment ) {
		System.out.println("Inside emp post methos");
		ApiResponse apiResponse=employmentService.UpdateEmployementFun(employment);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	

	// Rest API end point
	// URL : http://localhost:7878/applicant/employment
	// Method : DELETE
	// Payload : EmployementRequest
	@DeleteMapping("/employment/{empID}")
	public ResponseEntity<ApiResponse> UpdateEmployment(@PathVariable Long empID ) {
		System.out.println("Inside emp delete methos");
		ApiResponse apiResponse=employmentService.deleteEmployementFun(empID);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/project
	// Method : POST
	// DTO : ProjectRequest
	@PostMapping("/project")
	public ResponseEntity<ApiResponse> addProject(@RequestBody @Valid ProjectRequest project ) {
	
		ApiResponse apiResponse=projectService.addProjectFun(project);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
		
	
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/project
	// Method : PUT
	// Payload : ProjectRequest
	@PutMapping("/project")
	public ResponseEntity<ApiResponse> updateProject(@RequestBody @Valid ProjectRequest project ) {
	
		ApiResponse apiResponse=projectService.updateProjectFun(project);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/project
	// Method : Delete
	// Payload : ProjectRequest
	@DeleteMapping("/project/{projectId}")
	public ResponseEntity<ApiResponse> deleteProject(@PathVariable Long projectId ) {
	
		ApiResponse apiResponse=projectService.deleteProjectFun(projectId);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
		
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/education
	// Method : POST
	// Payload : EducationRequest
	@PostMapping("/education")
	public ResponseEntity<ApiResponse> addEducation(@RequestBody @Valid EducationRequest education ) {
	
		ApiResponse apiResponse=educationService.addEducationFun(education);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/education
	// Method : PUT
	// Payload : EducationRequest
	@PutMapping("/education")
	public ResponseEntity<ApiResponse> updateEducation(@RequestBody @Valid EducationRequest education ) {
	
		ApiResponse apiResponse=educationService.updateEducationFun(education);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/schooling
	// Method : POST
	// Payload : SchoolingRequest
	@PostMapping("/schooling")
	public ResponseEntity<ApiResponse> addSchooling(@RequestBody @Valid SchoolingRequest schooling ) {
	
		ApiResponse apiResponse=schoolingService.addSchoolingFun(schooling);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/schooling
	// Method : PUT
	// Payload : SchoolingRequest
	@PutMapping("/schooling")
	public ResponseEntity<ApiResponse> updateSchooling(@RequestBody @Valid SchoolingRequest schooling ) {
	
		ApiResponse apiResponse=schoolingService.updateSchoolingFun(schooling);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/address
	// Method : POST
	// Payload : AddressRequest
	@PostMapping("/address")
	public ResponseEntity<ApiResponse> addAddress(@RequestBody @Valid AddressRequest address ) {
	
		ApiResponse apiResponse=addressService.addAddressFun(address);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/address
	// Method : PUT
	// DTO : AddressRequest
	@PutMapping("/address")
	public ResponseEntity<ApiResponse> updateAddress(@RequestBody @Valid AddressRequest address ) {
	
		ApiResponse apiResponse=addressService.updateAddressFun(address);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	// Rest API end point
	// URL : http://localhost:7878/applicant/personalDetail
	// Method : PUT
	// Payload : PersonalDetailRequest
	@PutMapping("/personal-detail")
	public ResponseEntity<ApiResponse> updatePersonalDetails(@RequestBody @Valid PersonalDetailRequest personalDetail ) {
	
		ApiResponse apiResponse=userService.updatePersonalDetailFun(personalDetail);
		
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	/**
	 * Rest API end point
	 * URL:https://localhost:7878/applicant/upload-image
	 * Method :POST
	 * Request body->file
	 * */
	@PostMapping("/upload-image")
	public ResponseEntity<ApiResponse> uploadImage(@RequestParam MultipartFile file ) {
		if (!isValidImage(file)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("Invalid file type"),HttpStatus.BAD_REQUEST);
        }
		return new ResponseEntity<ApiResponse>(applicantService.uploadImage(file),HttpStatus.OK);
	}
	/**
	 * Rest API end point
	 * URL:https://localhost:7878/applicant/update-image
	 * Method :PUT
	 * Request body->file
	 * */
//	@PutMapping("/update-image")
//	public ResponseEntity<ApiResponse> updateImage(@RequestParam MultipartFile file ) {
//		if (!isValidImage(file)) {
//            return new ResponseEntity<ApiResponse>(new ApiResponse("Invalid file type"),HttpStatus.BAD_REQUEST);
//        }
//		return new ResponseEntity<ApiResponse>(applicantService.updateImage(file),HttpStatus.OK);
//	}
	/**
	 * Rest API end point
	 * URL:https://localhost:7878/applicant/delete-image
	 * Method :PUT
	 * */
	@DeleteMapping("remove-image")
	public ResponseEntity<ApiResponse> removeImage()
	{
		return new ResponseEntity<ApiResponse>(applicantService.removeImage(),HttpStatus.OK);
	}
	
	/**
	 * Rest API end point
	 * URL:https://localhost:7878/applicant/upload-resume
	 * Method :POST
	 * Request body->file
	 * */
	@PostMapping("/upload-resume")
	public ResponseEntity<ApiResponse> uploadResume(@RequestParam MultipartFile file ) {
		if (!isValidPdf(file)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse("Invalid file type-must be pdf"),HttpStatus.BAD_REQUEST);
        }
		return new ResponseEntity<ApiResponse>(applicantService.uploadResume(file),HttpStatus.OK);
	}
	/**
	 * Rest API end point
	 * URL:https://localhost:7878/applicant/update-resume
	 * Method :PUT
	 * Request body->file
	 * */
//	@PutMapping("/update-resume")
//	public ResponseEntity<ApiResponse> updateResume(@RequestParam MultipartFile file ) {
//		if (!isValidPdf(file)) {
//            return new ResponseEntity<ApiResponse>(new ApiResponse("Invalid file type-must be pdf"),HttpStatus.BAD_REQUEST);
//        }
//		return new ResponseEntity<ApiResponse>(applicantService.updateResume(file),HttpStatus.OK);
//	}
	/**
	 * Rest API end point
	 * URL:https://localhost:7878/applicant/delete-resume
	 * Method :PUT
	 * */
	@DeleteMapping("remove-resume")
	public ResponseEntity<ApiResponse> removeResume()
	{
		return new ResponseEntity<ApiResponse>(applicantService.removeResume(),HttpStatus.OK);
	}
	
	
	/**
	 * Custom validation method to check if the file is an image
	 * */ 
	 private boolean isValidImage(MultipartFile file) {
		      String fileName = file.getOriginalFilename();
		      return fileName != null && (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg"));
	 }
	 
	 /**
	  *Custom validation method to check if the file is a pdf
	  * */ 
	 private boolean isValidPdf(MultipartFile file) {
		 String fileName = file.getOriginalFilename();
		 return fileName != null && (fileName.endsWith(".pdf"));
	}
}
