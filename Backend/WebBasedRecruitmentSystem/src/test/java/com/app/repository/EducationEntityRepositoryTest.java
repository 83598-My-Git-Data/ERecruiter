package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.ApplicantEntity;
import com.app.entities.EducationEntity;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class EducationEntityRepositoryTest {
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private EducationEntityRepository educationRepo;
	
	@Test
	void testAddEducation() {
		
		ApplicantEntity applicant1= applicantRepo.findById(4l).orElseThrow();
		ApplicantEntity applicant2= applicantRepo.findById(5l).orElseThrow();
		ApplicantEntity applicant3= applicantRepo.findById(6l).orElseThrow();
		ApplicantEntity applicant4= applicantRepo.findById(7l).orElseThrow();
		
		List<EducationEntity> list = List.of(
				new EducationEntity(applicant1,"Fulltime", "B.Tech","MDU","Electronics",LocalDate.of(2018, 8, 18),LocalDate.of(2021, 8, 18)),
				new EducationEntity(applicant2,"Correspondence", "B.Tech","MDU","Electrical",LocalDate.of(2018, 8, 18),LocalDate.of(2021, 8, 18)),
				new EducationEntity(applicant3,"PartTime", "M.Tech","MDU","Mechanical",LocalDate.of(2018, 8, 18),LocalDate.of(2021, 8, 18)),
				new EducationEntity(applicant4,"Fulltime", "B.Tech","MDU","CSE",LocalDate.of(2018, 8, 18),LocalDate.of(2021, 8, 18))
				);
		List<EducationEntity> list2 = educationRepo.saveAll(list);
		assertEquals(4, list2.size());

	}
	
}
