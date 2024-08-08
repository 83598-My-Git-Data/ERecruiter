package com.app.payload.request;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor
@AllArgsConstructor
@ToString
public class HrRequest {

	@NotBlank
	private Long id;
	@NotBlank(message="qualification should not be empty")
	private String qualification;
	@NotNull
	private boolean status;
	// TODO Add upload image functionality
	private String imageURL;
	@NotNull
	private Signup user;
}
