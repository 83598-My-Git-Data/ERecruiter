package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;

import com.app.entities.ApplicantEntity;
import com.app.entities.LanguageEntity;
import com.app.entities.NoticePeriod;
import com.app.entities.SkillEntity;
import com.app.entities.UserEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ApplicantEntityRepositoryTest {
	
	@Autowired
	private UserEntityRepository userRepo;
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private SkillEntityRepository skillRepo;
	
	@Autowired
	private LanguageEntityRepository languageRepo;
	
	@Test
	void testAddApplicants() {
		
		UserEntity user1= userRepo.findById(1l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		UserEntity user2= userRepo.findById(2l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		UserEntity user3= userRepo.findById(3l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		
		
		List<ApplicantEntity> list = List.of(
				new ApplicantEntity(user1, false, false, "Headline1", "Profile Summary1", "Single",NoticePeriod.FIFTEEN_DAYS_OR_LESS),
				new ApplicantEntity(user2, false, false, "Headline1", "Profile Summary1", "Married",NoticePeriod.TWO_MONTHS),
				new ApplicantEntity(user3, false, false, "Headline1", "Profile Summary1", "Devorced",NoticePeriod.THREE_MONTHS)
		
				);
		List<ApplicantEntity> list2 = applicantRepo.saveAll(list);
		assertEquals(3, list2.size());

	}
	
	@Test
	void testAddApplicantSkill() {
		
		SkillEntity skill1= skillRepo.findById(1l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		SkillEntity skill2= skillRepo.findById(2l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		SkillEntity skill3= skillRepo.findById(3l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		SkillEntity skill4= skillRepo.findById(4l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		SkillEntity skill5= skillRepo.findById(12l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		SkillEntity skill6= skillRepo.findById(13l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		SkillEntity skill7= skillRepo.findById(14l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		

		ApplicantEntity applicant1= applicantRepo.findById(4l).orElseThrow();
		ApplicantEntity applicant2= applicantRepo.findById(5l).orElseThrow();
		ApplicantEntity applicant3= applicantRepo.findById(6l).orElseThrow();
		ApplicantEntity applicant4= applicantRepo.findById(7l).orElseThrow();
		
		applicant1.addSkill(skill1);
		applicant1.addSkill(skill2);
		applicant1.addSkill(skill3);
		applicant1.addSkill(skill6);
		applicant1.addSkill(skill7);
		applicant1.addSkill(skill6);
		applicant2.addSkill(skill2);
		applicant3.addSkill(skill3);
		applicant4.addSkill(skill4);
		

	}
	
	@Test
	void testAddApplicantLanguage() {
		
		LanguageEntity lang1= languageRepo.findById(1l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		LanguageEntity lang2= languageRepo.findById(4l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		LanguageEntity lang3= languageRepo.findById(7l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		LanguageEntity lang4= languageRepo.findById(10l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		

		ApplicantEntity applicant1= applicantRepo.findById(4l).orElseThrow();
		ApplicantEntity applicant2= applicantRepo.findById(5l).orElseThrow();
		ApplicantEntity applicant3= applicantRepo.findById(6l).orElseThrow();
		ApplicantEntity applicant4= applicantRepo.findById(7l).orElseThrow();
		
		applicant1.addLanguage(lang1);
		applicant2.addLanguage(lang2);
		applicant3.addLanguage(lang3);
		applicant4.addLanguage(lang4);
		
		

	}
	
	
}

