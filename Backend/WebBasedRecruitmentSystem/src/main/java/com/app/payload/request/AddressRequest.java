package com.app.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddressRequest {
	
	@NotBlank(message = "permanent Address should not be null")
	@Size(min=3,max = 255)
	private String permanentAddress;
	
	
	@NotBlank(message = "pincode should not be null")
	@Size(min=6,max = 6)
	private String pincode;
	
	@NotBlank(message = "state should not be null")
	@Size(min=3,max = 60)
	private String state;
	
	@NotBlank(message = "country should not be null")
	@Size(min=3,max = 70)
	private String country;
}
