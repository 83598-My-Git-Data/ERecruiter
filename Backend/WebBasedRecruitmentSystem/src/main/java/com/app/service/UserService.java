package com.app.service;



import com.app.payload.request.BasicDetailRequest;
import com.app.payload.request.PersonalDetailRequest;
import com.app.payload.request.ResetPassword;
import com.app.payload.request.SigninRequest;
import com.app.payload.request.Signup;
import com.app.payload.request.UserAndOtp;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.OtpAndEmailResponse;
import com.app.payload.response.UserDetailsResp;


public interface UserService {
	
	//sign up
	Signup userRegistration(Signup reqDTO);
	
	SigninRequest authenticateUser(SigninRequest dto);

//	UserDetailsResp getBasicDetail();
	 UserDetailsResp getBasicDetail(Long userId);
	
//	ApiResponse updateBasicDetails(BasicDetailRequest basicDetails);
	 ApiResponse updateBasicDetails(Long userId, BasicDetailRequest basicDetails);

//	ApiResponse updatePersonalDetailFun(PersonalDetailRequest personalDetail);
//
//	ApiResponse forgotPassword(String email);
//
//	OtpAndEmailResponse verifyOtp(UserAndOtp userAndOtp);
//
//	ApiResponse resetPassword(String email,String otp,ResetPassword password);
}
