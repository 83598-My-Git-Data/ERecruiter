package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.ApplicantEntity;
import com.app.entities.EmploymentEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class EmploymentEntityRepositoryTest {

	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private EmploymentEntityRepository employmentRepo;
	
	@Test
	void testAddEducation() {
		
		ApplicantEntity applicant1= applicantRepo.findById(4l).orElseThrow();
		ApplicantEntity applicant2= applicantRepo.findById(5l).orElseThrow();
		ApplicantEntity applicant3= applicantRepo.findById(6l).orElseThrow();
		ApplicantEntity applicant4= applicantRepo.findById(7l).orElseThrow();
		
		List<EmploymentEntity> list = List.of(
				new EmploymentEntity(applicant1,true, "fulltime",4,4,"infosys","tcs","senior1","junior1","JobProfile1","Department1",20000),
				new EmploymentEntity(applicant2,false, "parttime",2,2,"Beeta","Digit","senior2","junior2","JobProfile2","Department2",60000),
				new EmploymentEntity(applicant3,false, "parttime",1,5,"Gama","Boom","senior3","junior3","JobProfile3","Department3",210000),
				new EmploymentEntity(applicant4,true, "fulltime",6,1,"Alpha","Zeta","senior4","junior4","JobProfile4","Department4",30000)
				);
		List<EmploymentEntity> list2 = employmentRepo.saveAll(list);
		assertEquals(4, list2.size());

	}
	
}
