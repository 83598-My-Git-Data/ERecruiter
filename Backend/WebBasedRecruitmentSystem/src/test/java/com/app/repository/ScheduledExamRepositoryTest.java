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
import com.app.entities.ExamEntity;
import com.app.entities.ScheduledExamEntity;

@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ScheduledExamRepositoryTest {

	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private ExamRepository examRepo;
	
	@Autowired
	private ScheduledExamRepository schRepo;
	
	@Test
	void scheduledTest()
	{
		ApplicantEntity applicant1=applicantRepo.findById(4L).orElseThrow();
		ApplicantEntity applicant2=applicantRepo.findById(5L).orElseThrow();
		ApplicantEntity applicant3=applicantRepo.findById(6L).orElseThrow();
		ExamEntity exam=examRepo.findById(1L).orElseThrow();
		
		List<ScheduledExamEntity> schList=List.of(
				new ScheduledExamEntity(LocalDate.of(2024, 2, 25),applicant1,exam),
				new ScheduledExamEntity(LocalDate.of(2024, 2, 25),applicant2,exam),
				new ScheduledExamEntity(LocalDate.of(2024, 2, 25),applicant3,exam)
				);
		List<ScheduledExamEntity> schListTest=schRepo.saveAll(schList);
		assertEquals(3, schListTest.size());
	}
}
