package com.app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;

import com.app.entities.JobInfoEntity;
import com.app.entities.SkillEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class JobInfoEntityRepositorySkillTest {

	@Autowired
	private SkillEntityRepository skillRepo;
	
	@Autowired
	private JobInfoRepository jobInfoRepo;
	
	@Test
	public void addJobSkill() {
		
		SkillEntity skill1= skillRepo.findById(1l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		SkillEntity skill2= skillRepo.findById(2l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		SkillEntity skill3= skillRepo.findById(3l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		SkillEntity skill4= skillRepo.findById(4l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		
		JobInfoEntity jobInfo1= jobInfoRepo.findById(1l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		JobInfoEntity jobInfo2= jobInfoRepo.findById(2l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		
		jobInfo1.getJobSkills().add(skill1);
		jobInfo1.getJobSkills().add(skill2);
		jobInfo1.getJobSkills().add(skill3);
		jobInfo1.getJobSkills().add(skill4);
		
		jobInfo2.getJobSkills().add(skill1);
		jobInfo2.getJobSkills().add(skill2);
		jobInfo2.getJobSkills().add(skill3);
		jobInfo2.getJobSkills().add(skill4);
		
		
	} 
	
}
