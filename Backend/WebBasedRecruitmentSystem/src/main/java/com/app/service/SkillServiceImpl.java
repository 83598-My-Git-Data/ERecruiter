package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.SkillEntity;
import com.app.payload.response.SkillListRequest;
import com.app.repository.SkillEntityRepository;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {
	
	@Autowired //To map entity with the DTO 
	private ModelMapper mapper;
	
	@Autowired
	private SkillEntityRepository skillRepo;
	
	

	@Override
	public List<SkillListRequest> getSkillList() {
		List<SkillEntity> skillList=skillRepo.findAll();
		
		return skillList.stream().
				map(skill -> mapper.map(skill, SkillListRequest.class)).
				collect(Collectors.toList());
	}
	
	
}
