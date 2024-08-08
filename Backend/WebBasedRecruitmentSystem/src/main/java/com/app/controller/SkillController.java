package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payload.response.SkillListRequest;
import com.app.service.SkillService;

@RestController
@RequestMapping("/skill")
public class SkillController {
	
	@Autowired
	private SkillService skillService;
	
	// Rest API end point
	// URL : http://localhost:7878/skill/skill-list
	// Method : GET
	// Res : skillListRequest
	@GetMapping("/skill-list")
	public ResponseEntity<?> getAddressDetails() {
			System.out.println("inside skill list endpoint");
			List<SkillListRequest> skillList = skillService.getSkillList();
			return new ResponseEntity<>(skillList, HttpStatus.OK);
		// send response as address details to be displayed on profile section of
		// applicant
	}
	
	
}
