package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.LanguageEntity;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class LanguageEntityRepositoryTest {

	@Autowired
	private LanguageEntityRepository languageRepo;
	
	@Test
	void testAddSkills() {
		
		
		List<LanguageEntity> list = List.of(
				new LanguageEntity("Hindi","Beginner"),
				new LanguageEntity("Hindi","Proficient"),
				new LanguageEntity("Hindi","Expert"),
				new LanguageEntity("English","Beginner"),
				new LanguageEntity("English","Proficient"),
				new LanguageEntity("English","Expert"),
				new LanguageEntity("Punjabi","Beginner"),
				new LanguageEntity("Punjabi","Proficient"),
				new LanguageEntity("Punjabi","Expert"),
				new LanguageEntity("Spanish","Beginner"),
				new LanguageEntity("Spanish","Proficient"),
				new LanguageEntity("Spanish","Expert"),
				new LanguageEntity("French","Beginner"),
				new LanguageEntity("French","Proficient"),
				new LanguageEntity("French","Expert")
				
				
				
				
				
				);
		List<LanguageEntity> list2 = languageRepo.saveAll(list);
		assertEquals(15, list2.size());

	}
}
