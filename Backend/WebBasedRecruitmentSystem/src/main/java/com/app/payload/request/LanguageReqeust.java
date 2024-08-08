package com.app.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LanguageReqeust {
	
	@NotBlank(message = "Language Name cannot be blank")
	private String name;
	
	@NotBlank(message = "Language proficiency cannot be blank")
	private String proficiency;
}
