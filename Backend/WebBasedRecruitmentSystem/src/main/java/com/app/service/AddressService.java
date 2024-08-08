package com.app.service;



import com.app.payload.request.AddressRequest;
import com.app.payload.response.AddressResp;
import com.app.payload.response.ApiResponse;

public interface AddressService {

	AddressResp getAddress();

	ApiResponse addAddressFun( AddressRequest address);

	ApiResponse updateAddressFun( AddressRequest address);

}
