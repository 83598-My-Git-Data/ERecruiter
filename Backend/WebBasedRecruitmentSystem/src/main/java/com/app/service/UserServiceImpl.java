package com.app.service;
import static com.app.utils.ApplicantHelper.findApplicantByUserId;
import static com.app.utils.UserHelper.findUserById;

//import javax.mail.MessagingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.ApplicantEntity;
import com.app.entities.NoticePeriod;
import com.app.entities.UserEntity;
import com.app.payload.request.BasicDetailRequest;
import com.app.payload.request.PersonalDetailRequest;
import com.app.payload.request.ResetPassword;
import com.app.payload.request.SigninRequest;
import com.app.payload.request.Signup;
import com.app.payload.request.UserAndOtp;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.OtpAndEmailResponse;
import com.app.payload.response.UserDetailsResp;
import com.app.repository.ApplicantRepository;
import com.app.repository.UserEntityRepository;
//import com.app.security.FindAuthenticationDetails;
//import com.app.utils.EmailUtil;
//import com.app.utils.OtpUtil;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	//dep : dao layer i/f
	@Autowired
	private UserEntityRepository userRepo;
	//dep
	@Autowired
	private ModelMapper mapper;
	//dep 
//	@Autowired
//	private PasswordEncoder encoder;
//	
	//dep
	@Autowired
	private ApplicantRepository applicantRepo;
	
	
//	//dep
//	@Autowired
//	private FindAuthenticationDetails findUser;
//	
//	@Autowired
//	private EmailUtil emailUtil;
//	
//	@Autowired
//	private OtpUtil optUtils;
	

	@Override
	public Signup userRegistration(Signup reqDTO) {
		//dto --> entity
		UserEntity user=mapper.map(reqDTO, UserEntity.class);
		ApplicantEntity applicant=new ApplicantEntity(user, false, false, "0", "0", "0",NoticePeriod.FIFTEEN_DAYS_OR_LESS,"deleted","deleted");
		applicantRepo.save(applicant);
		user.setPassword("123");//pwd : encrypted using SHA
//		encoder.encode(user.getPassword())
		return mapper.map(userRepo.save(user), Signup.class);
	}
	
	
	/**
	 * Get applicant Basic Details
	 * **/
//	@Override
//	public UserDetailsResp getBasicDetail() {
//		
//		Long userId=findUser.getUserId();
//		
//		//statically imported method from UserHelper class
//		//to find persistent UserEntity by User Id
//				
//		UserEntity user=findUserById(userId, userRepo);
//		
//		//dto <-- entity
//		return mapper.map(user, UserDetailsResp.class);
//	}
	 @Override
	    public UserDetailsResp getBasicDetail(Long userId) 
	 {
	        // Find the UserEntity by userId
	        UserEntity user = userRepo.findById(userId)
	                                  .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

	        // Map UserEntity to UserDetailsResp DTO
	        return mapper.map(user, UserDetailsResp.class);
	  }
	
	/**
	 * Update applicant Basic Details
	 * **/
//	@Override
//	public ApiResponse updateBasicDetails(BasicDetailRequest basicDetails) {
//		
//		Long userId=findUser.getUserId();
//		//statically imported method from UserHelper class
//		//to find persistent UserEntity by id		
//		UserEntity user=findUserById(userId, userRepo);
//		
//		user.setFirstName(basicDetails.getFirstName());
//		user.setLastName(basicDetails.getLastName());
//		user.setPhoneNumber(basicDetails.getPhoneNumber());
//		user.setEmail(basicDetails.getEmail());
//
//		//statically imported method from ApplicantHelper class
//		//to find persistent ApplicantEntity by userId
//		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
//		
//		
//		// Returns the value in case of non empty Optional
//		// OR throws supplied exception
//		
//		applicant.setNoticePeriod(basicDetails.getNoticePeriod());
//		
//		userRepo.save(user);
//		applicantRepo.save(applicant);
//		return new ApiResponse("User with id "+userId+" Updated");
//	}
	 @Override
	    public ApiResponse updateBasicDetails(Long userId, BasicDetailRequest basicDetails) 
	 {
	        // Find the UserEntity by userId
	        UserEntity user = userRepo.findById(userId)
	                                  .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

	        user.setFirstName(basicDetails.getFirstName());
	        user.setLastName(basicDetails.getLastName());
	        user.setPhoneNumber(basicDetails.getPhoneNumber());
	        user.setEmail(basicDetails.getEmail());

	        // Find the ApplicantEntity by userId
	        ApplicantEntity applicant = applicantRepo.findById(userId)
	                                  .orElseThrow(() -> new RuntimeException("Applicant not found for user id: " + userId));
	        
	        applicant.setNoticePeriod(basicDetails.getNoticePeriod());

	        userRepo.save(user);
	        applicantRepo.save(applicant);
	        
	        return new ApiResponse("User with id " + userId + " Updated");
	  }


	@Override
	public SigninRequest authenticateUser(SigninRequest dto) 
	{
		UserEntity user = userRepo.findByEmailAndPassword(
			dto.getEmail(), dto.getPassword())
			.orElseThrow(() -> //if orElseThrow is not given 
			new com.app.exception.AuthenticationException("Invalid Email or Password !!!!!!"));
	//valid login -user : persistent -> entity -> dto
	return mapper.map(user, SigninRequest.class);
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * Update applicant Personal Details
	 * **/
//	@Override
//	public ApiResponse updatePersonalDetailFun(PersonalDetailRequest personalDetail) {
//			
//		Long userId=findUser.getUserId();
//		//statically imported method from UserHelper class
//		//to find persistent UserEntity by id		
//		UserEntity user=findUserById(userId, userRepo);
//		
//		user.setDob(personalDetail.getDob());
//		user.setGender(personalDetail.getGender());
//		userRepo.save(user);
//
//		//statically imported method from ApplicantHelper class
//		//to find persistent ApplicantEntity by userId
//		ApplicantEntity applicant=findApplicantByUserId(userId, applicantRepo);
//		
//		
//		// Returns the value in case of non empty Optional
//		// OR throws supplied exception
//		
//		applicant.setMaritalStatus(personalDetail.getMaritalStatus());
//		applicantRepo.save(applicant);
//		return new ApiResponse("Personal details update with id "+userId);
//	}
//	
	
//	@Override
//	public ApiResponse forgotPassword(String email) {
////		String otp=optUtils.generateOtp();
//		UserEntity user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));
//		if(user!=null)
//		{
//			try {
//				emailUtil.sendOtpEmail(email,otp);
//				user.setOtp(otp);
//			} catch (MessagingException e) {
//				// TODO Auto-generated catch block
//				throw new RuntimeException("Unable to send otp,please try again");
//			}
//		}
//		return new ApiResponse("email sent successfully,check you email");
//	}
//	
	/**
	 * Otp will be verified and store in the DB
	 * */
//	@Override
//	public OtpAndEmailResponse verifyOtp(UserAndOtp userRequest) {
//		String email=userRequest.getEmail();
//		UserEntity user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));
//		String otp=user.getOtp();
//		if(user!=null)
//		{
//			if(userRequest.getOtp().equals(otp))
//			{
//				return new OtpAndEmailResponse(email,otp);
//			}
//		}
//		return new OtpAndEmailResponse("invalid email","or otp");
//	}
	
	/**
	 * We will fetch the OTP from the DB and the url and
	 * then the password will be updated
	 * */
//	@Override
//	public ApiResponse resetPassword(String email, String otp, ResetPassword password) 
//	{
//		UserEntity user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));
//		String dbSavedOtp=user.getOtp();
//		if(otp.equals(dbSavedOtp))
//		{
//			user.setPassword("123");
////			encoder.encode(password.getPassword())
//			return new ApiResponse("password updated successfully");
//		}
//		return new ApiResponse("password updation failed");
//	}
}
