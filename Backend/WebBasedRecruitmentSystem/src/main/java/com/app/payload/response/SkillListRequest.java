package com.app.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SkillListRequest {
	
	
	private Long skillId;
	
	
	private String name;
}
