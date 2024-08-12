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

import com.app.entities.ExamEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ExamRepositoryTest {

	@Autowired
	ExamRepository examRepo;
	
	@Test
	void addExam()
	{
		List<ExamEntity> list=List.of(
				new ExamEntity("Java",LocalDate.of(2024,2,6),60),
				new ExamEntity("DevOps",LocalDate.of(2024,2,2),60),
				new ExamEntity("C++",LocalDate.of(2024,1,28),60)
				);
		List<ExamEntity> list2=examRepo.saveAll(list);
		assertEquals(3, list2.size());
	}
}
