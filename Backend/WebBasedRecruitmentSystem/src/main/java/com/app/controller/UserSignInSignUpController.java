package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.payload.request.ResetPassword;
import com.app.payload.request.SigninRequest;
import com.app.payload.request.Signup;
import com.app.payload.request.UserAndOtp;
import com.app.payload.response.ApiResponse;
import com.app.payload.response.OtpAndEmailResponse;
import com.app.payload.response.SigninResponse;
//import com.app.security.JwtUtils;
import com.app.service.UserService;
//import com.sunbeam.dto.AuthRequest;

@RestController
@RequestMapping("/users")
public class UserSignInSignUpController {
	@Autowired
	private UserService userService;
//	@Autowired
//	private JwtUtils utils;
//
//	@Autowired
//	private AuthenticationManager mgr;

	// sign up
	@PostMapping("/signup")
	public ResponseEntity<?> userSignup(@RequestBody @Valid Signup dto) {
		System.out.println("in sign up " + dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.userRegistration(dto));
	}

	/*
	 * request payload : Auth req DTO : email n password resp payload : In case of
	 * success : Auth Resp DTO : mesg + JWT token + SC 200 IN case of failure : SC
	 * 401
	 */
//	@PostMapping("/signin")
//	public ResponseEntity<?> signinUser(@RequestBody @Valid SigninRequest reqDTO) {
//		System.out.println("in signin " + reqDTO);
//		// simply invoke authentucate(...) on AuthMgr
//		// i/p : Authentication => un verifed credentials
//		// i/f --> Authentication --> imple by UsernamePasswordAuthToken
//		// throws exc OR rets : verified credentials (UserDetails i.pl class: custom
//		// user details)
//
//		Authentication verifiedAuth = mgr
//				.authenticate(new UsernamePasswordAuthenticationToken
//						(reqDTO.getEmail(), reqDTO.getPassword()));
//		System.out.println(verifiedAuth.getClass());// Custom user details
//		// => auth success
//		return ResponseEntity
//				.ok(new SigninResponse(utils.generateJwtToken(verifiedAuth), "Successful Authentication!!!"));
//
//	}
	@PostMapping("/signin")
	public ResponseEntity<?> signInUser(@RequestBody @Valid SigninRequest request)
	{//@RequestBody-> json->java unmarshalling / deserialization.
		System.out.println("in signin " + request);
		return ResponseEntity.status(HttpStatus.OK).body(userService.authenticateUser(request));
//		try {
//			return ResponseEntity.ok(userService.authenticateUser(request));
//		} catch (RuntimeException e) {
//			System.out.println(e);
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
//		}
	}
	
//	@PutMapping("/send-otp")
//	public ResponseEntity<ApiResponse> forgotPassword(@Valid @RequestParam String email)
//	{
//		return new ResponseEntity<ApiResponse>(userService.forgotPassword(email),HttpStatus.OK);
//	}
//	
//	@PostMapping("/verify-otp")
//	public ResponseEntity<OtpAndEmailResponse> verifyOtp(@Valid @RequestBody UserAndOtp userAndOtp)
//	{
//		return new ResponseEntity<OtpAndEmailResponse>(userService.verifyOtp(userAndOtp),HttpStatus.OK);
//	}
//
//	@PutMapping("/reset-password")
//	public	ResponseEntity<ApiResponse> resetPassword(	@Valid @RequestParam String email,
//														@Valid @RequestParam String otp,
//														@Valid @RequestBody ResetPassword password) 
//	{
//		return new ResponseEntity<ApiResponse>(userService.resetPassword(email,otp,password),HttpStatus.OK);
//	}
}
