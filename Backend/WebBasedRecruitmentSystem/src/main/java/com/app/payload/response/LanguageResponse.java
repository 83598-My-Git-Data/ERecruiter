package com.app.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LanguageResponse {
	
	private Long languageId;
	private String name;
	private String proficiency;
}
