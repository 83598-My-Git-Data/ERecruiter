package com.app.payload.response;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddressResp {
	
	private String permanentAddress;
	private String pincode;
	private String state;
	private String country;
}
