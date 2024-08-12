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
import com.app.entities.ProjectEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProjectEntityRepositoryTest {

	
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	
	@Autowired
	private ProjectEntityRepository projectRepo;
	
	@Test
	public void addProjects() {
		ApplicantEntity applicant1= applicantRepo.findById(4l).orElseThrow();
		ApplicantEntity applicant2= applicantRepo.findById(5l).orElseThrow();
		ApplicantEntity applicant3= applicantRepo.findById(6l).orElseThrow();
		ApplicantEntity applicant4= applicantRepo.findById(7l).orElseThrow();
		
		List<ProjectEntity> list =List.of(
					new ProjectEntity(applicant1,"Project1", true,LocalDate.of(2018, 8, 18),LocalDate.of(2021, 8, 18) ,"Description1" ),
					new ProjectEntity(applicant2,"Project2", false,LocalDate.of(2018, 8, 18),LocalDate.of(2021, 8, 18) ,"Description2" ),
					new ProjectEntity(applicant3,"Project3", true,LocalDate.of(2018, 8, 18),LocalDate.of(2021, 8, 18) ,"Description3" ),
					new ProjectEntity(applicant4,"Project4", false,LocalDate.of(2018, 8, 18),LocalDate.of(2021, 8, 18) ,"Description4" )
				); 
		List<ProjectEntity> list2 = projectRepo.saveAll(list);
		assertEquals(4, list2.size());
	}
}
