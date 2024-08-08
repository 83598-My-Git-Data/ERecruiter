package com.app.service;

import static com.app.utils.ApplicantHelper.findApplicantByUserId;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.ApplicantEntity;
import com.app.entities.EmploymentEntity;
import com.app.exception.ResourceNotFoundException;
import com.app.payload.request.EmploymentRequest;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.EmploymentResponse;
import com.app.repository.ApplicantRepository;
import com.app.repository.EmploymentEntityRepository;
import com.app.repository.UserEntityRepository;
import com.app.security.FindAuthenticationDetails;

@Service
@Transactional
public class EmploymentServiceImpl implements EmploymentService {
	
	@Autowired
	private EmploymentEntityRepository employmentRepo;
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired //To map entity with the DTO 
	private ModelMapper mapper;
	
	@Autowired
	private UserEntityRepository userRepo;
	
	@Autowired
	private FindAuthenticationDetails findUser;
	
	
	/**
	 * Get applicant Employment
	 * **/
	@Override
	public List<EmploymentResponse> getAllEmployment() {
	
		Long userId=findUser.getUserId();

		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		List<EmploymentEntity> employmentList =employmentRepo.findAllByApplicant(applicant).
				orElseThrow(()-> new ResourceNotFoundException
						("Education in education service", "Applicant ID", applicant.getId()));
		
		
		return employmentList.stream().
					map(employment -> mapper.map(employment, EmploymentResponse.class)).
						collect(Collectors.toList());
	}
	
	/**
	 * Adding Applicant Employment
	 * */
	@Override
	public ApiResponse addEmployementFun(EmploymentRequest employment) {
		Long userId=findUser.getUserId();

		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		EmploymentEntity employmentEntity=mapper.map(employment, EmploymentEntity.class);
		employmentEntity.setApplicant(applicant);
		
		employmentRepo.save(employmentEntity);
		return new ApiResponse("Applicant employment added with id "+applicant.getId());
	}
	
	
	/**
	 * Updating Applicant Employment
	 * */
	@Override
	public ApiResponse UpdateEmployementFun(EmploymentRequest employment) {
		Long userId=findUser.getUserId();

		//statically imported method from ApplicantHelper class
		//to find persistent ApplicantEntity by userId
		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
		
		// Returns the value in case of non empty Optional
		// OR throws supplied exception
		
		EmploymentEntity setEmployment=employmentRepo.findById(employment.getId()) .
					orElseThrow(()-> new ResourceNotFoundException
						("Employment in Employment service", "Employment ID", employment.getId()));
			
		setEmployment.setCurrentCompanyName(employment.getCurrentCompanyName());
		setEmployment.setCurrentDesignation(employment.getCurrentDesignation());
		setEmployment.setCurrentSalary(employment.getCurrentSalary());
		setEmployment.setDepartment(employment.getDepartment());
		setEmployment.setEmployementType(employment.getEmployementType());
		setEmployment.setExperienceMonths(employment.getExperienceMonths());
		setEmployment.setExperienceYears(employment.getExperienceYears());
		setEmployment.setJobProfile(employment.getJobProfile());
		setEmployment.setPreviousCompanyName(employment.getPreviousCompanyName());
		setEmployment.setPreviousDesignation(employment.getPreviousDesignation());
		setEmployment.setCurrentlyEmployed(employment.getCurrentlyEmployed());
		
		employmentRepo.save(setEmployment);
		return new ApiResponse("Applicant Employment updated with applicant id "+applicant.getId()+"and employment id " +employment.getId());
		
	}

	@Override
	public ApiResponse deleteEmployementFun(Long empID) {
		
		employmentRepo.deleteById(empID);
		return new ApiResponse("Applicant Employment deleted with id " +empID);
	}
	
	
	
	
	
	
	
}
