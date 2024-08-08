package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entities.HREntity;
import com.app.entities.UserEntity;
import com.app.exception.ResourceNotFoundException;
import com.app.payload.request.HrRegistrationDetailsRequest;
import com.app.payload.response.AnalysisResponseAdmin;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.HrDetailsResponse;
import com.app.payload.response.JobDetailsWithUsernameResponse;
import com.app.repository.HREntityRepository;
import com.app.repository.JobInfoRepository;
import com.app.repository.UserEntityRepository;

@Service
public class AdminServiceImpl implements AdminService {

	//dep : dao layer i/f
	@Autowired
	private UserEntityRepository userDao;
	//dep
	@Autowired
	private ModelMapper mapper;
	//dep 
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private HREntityRepository hrRepo;
	
	@Autowired
	private JobInfoRepository jobRepo;
	
	@Override
	@Transactional
	public ApiResponse registerHr(HrRegistrationDetailsRequest hr) {
		String message = "something bad happened";
		
		// Create and save the user entity
	    UserEntity user = mapper.map(hr, UserEntity.class);
	    user.setPassword(encoder.encode(user.getPassword()));
	    UserEntity savedUser = userDao.save(user);

	    // Create the HR entity and set the user details
	    HREntity hrEntity = mapper.map(hr, HREntity.class);
	    hrEntity.setImageURL("deleted");
	    hrEntity.setUser(savedUser);

	    // Use merge instead of persist to handle detached entities
	    HREntity savedHr = hrRepo.save(hrEntity);

	    // Check if the HR registration was successful
	    if (savedHr.getUser().getId() > 0) {
	        message = "HR registered successfully";
	    }
	    ApiResponse res=new ApiResponse(message);
	    return res;
	}


	@Override
	public List<HrDetailsResponse> getAllHr() {
		List<HrDetailsResponse> list=userDao.findUserAndHrInfo();
		return list;
	}

	@Override
	public List<JobDetailsWithUsernameResponse> getAllJobs() {
		List<JobDetailsWithUsernameResponse> jobList=jobRepo.findAllJobs();
		return jobList;
	}

	
	@Override
	public ApiResponse deactivateHrById(Long hrId) {
		HREntity hr=hrRepo.findById(hrId).orElseThrow(()->
				new ResourceNotFoundException("HR", "id", hrId));
		hr.setActiveStatus(false);
		hrRepo.save(hr);
		return new ApiResponse("HR with id "+hrId+" deactivated");
	}

	@Override
	public AnalysisResponseAdmin getReport() {
		Long activeHr=hrRepo.countActiveUser();
		Long totalHr=hrRepo.count();
		Long totalJobs=jobRepo.count();
		Long activeJobs=jobRepo.countActiveJobs();
		AnalysisResponseAdmin response=new AnalysisResponseAdmin(totalHr,activeHr,totalJobs,activeJobs);
		return response;
	}

}
