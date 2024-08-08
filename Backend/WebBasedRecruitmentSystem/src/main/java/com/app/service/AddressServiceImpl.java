package com.app.service;

import static com.app.utils.UserHelper.findUserById;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.AddressEntity;
import com.app.entities.UserEntity;
import com.app.exception.ResourceNotFoundException;
import com.app.payload.request.AddressRequest;
import com.app.payload.response.AddressResp;
import com.app.payload.response.ApiResponse;
import com.app.repository.AddressRepository;
import com.app.repository.UserEntityRepository;
import com.app.security.FindAuthenticationDetails;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {
	//dep : dao layer i/f
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired //To map entity with the DTO 
	private ModelMapper mapper;
		
	
	@Autowired
	private UserEntityRepository userRepo;
	
	@Autowired
	private FindAuthenticationDetails findUser;
	
	
	/**
	 * Get applicant address
	 * **/
	@Override 
	public AddressResp getAddress() {
		
		Long userId=findUser.getUserId();
		
		//statically imported method from UserHelper class
		//to find persistent UserEntity by email
		//extracted from authentication object
		
		UserEntity user=findUserById(userId, userRepo);
		
		AddressEntity address= addressRepo.findByUser(user)
									.orElseThrow(()-> new ResourceNotFoundException
											("Address", "Applicant ID", userId));
				// Returns the value in case of non empty Optional
				// OR throws supplied exception
				
		
		return mapper.map(address, AddressResp.class);
	}
	
	
	/**
	 * Add applicant address
	 * **/
	@Override
	public ApiResponse addAddressFun(AddressRequest address) {
		
		Long userId=findUser.getUserId();
		
		//statically imported method from UserHelper class
		//to find persistent UserEntity userId
		
		UserEntity user=findUserById(userId, userRepo);
		
		AddressEntity addressEntity=mapper.map(address, AddressEntity.class);
		
		addressEntity.setUser(user);
		
		addressRepo.save(addressEntity);
		
		return new ApiResponse("Address added  with applicant id :"+user.getId());
	}

	
	/**
	 * Update applicant address
	 * **/
	@Override
	public ApiResponse updateAddressFun(AddressRequest address) {
		
		Long userId=findUser.getUserId();
		
		UserEntity user=findUserById(userId, userRepo);
		
		AddressEntity addressEntity=addressRepo.findByUser(user).
				orElseThrow(()-> new ResourceNotFoundException
						("Address", "Applicant ID", userId));
		
		addressEntity.setCountry(address.getCountry());
		addressEntity.setPermanentAddress(address.getPermanentAddress());
		addressEntity.setPincode(address.getPincode());
		addressEntity.setState(address.getState());
		
		addressRepo.save(addressEntity);
		
		return new ApiResponse("Address updated  with applicant id :"+user.getId());
	}
	
	

}
