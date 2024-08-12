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
import com.app.entities.SchoolingEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SchoolingEntityRepositoryTest {
	

	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private SchoolingEntityRepository schoolingRepo;
	
	@Test
	void testAddSChooling() {
		
		ApplicantEntity applicant1= applicantRepo.findById(4l).orElseThrow();
		ApplicantEntity applicant2= applicantRepo.findById(5l).orElseThrow();
		ApplicantEntity applicant3= applicantRepo.findById(6l).orElseThrow();
		ApplicantEntity applicant4= applicantRepo.findById(7l).orElseThrow();
	
		List<SchoolingEntity> list = List.of(
				new SchoolingEntity(applicant1,"CBSE",LocalDate.of(2014, 8, 18),62,"CBSE",LocalDate.of(2016, 8, 18),68),
				new SchoolingEntity(applicant2,"DBS",LocalDate.of(2011, 8, 18),92,"DBS",LocalDate.of(2013, 8, 18),82),
				new SchoolingEntity(applicant3,"MDU",LocalDate.of(2016, 8, 18),72,"MDU",LocalDate.of(2018, 8, 18),70),
				new SchoolingEntity(applicant4,"ICSE",LocalDate.of(2010, 8, 18),52,"ICSE",LocalDate.of(2012, 8, 18),58)
				);
		List<SchoolingEntity> list2 = schoolingRepo.saveAll(list);
		assertEquals(4, list2.size());

	}
	
}
