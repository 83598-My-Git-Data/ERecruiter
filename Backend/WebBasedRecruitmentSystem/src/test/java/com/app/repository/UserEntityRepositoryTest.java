package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Gender;
import com.app.entities.UserEntity;
import com.app.entities.UserRole;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserEntityRepositoryTest {
	// dep
	@Autowired
	private UserEntityRepository userRepo;

	@Autowired
	private PasswordEncoder enc;

	
	
	@Test
	void testAddUsers1() {
		List<UserEntity> list = List.of(
				new UserEntity("Rohit", "Jadhav",Gender.MALE,"Rohit@gmail.com","9876543210",enc.encode("12345"),LocalDate.of(2000, 10, 5) ,UserRole.ROLE_ADMIN,"878787"),
				new UserEntity("Vedant", "Bhosale",Gender.MALE,"Vedant@gmail.com","9898747465",enc.encode("2345"),LocalDate.of(1998, 7,8) ,UserRole.ROLE_APPLICANT,"878787"),
				new UserEntity("Safin", "Tamboli",Gender.MALE,"safin@gmail.com","9876543210",enc.encode("1345"), LocalDate.of(1999, 8, 25),UserRole.ROLE_HR,"878787"),
				new UserEntity("Altaf", "Shaikh",Gender.MALE,"Altaf@gmail.com","9555414192",enc.encode("9555"),LocalDate.of(1997, 10, 2) ,UserRole.ROLE_APPLICANT,"878787"),
				new UserEntity("Mira", "Deshmukh",Gender.FEMALE,"mira@gmail.com","9555414193",enc.encode("8555"), LocalDate.of(2000, 5, 12),UserRole.ROLE_APPLICANT,"878787"),
				new UserEntity("Prashant", "Kumar",Gender.MALE,"prashant@gmail.com","9555414194",enc.encode("7555"),LocalDate.of(1999, 12, 30) ,UserRole.ROLE_APPLICANT,"878787"),
				new UserEntity("Himanshu", "shinde",Gender.MALE,"himashu@gmail.com","9555414195",enc.encode("6555"), LocalDate.of(1998, 5, 21),UserRole.ROLE_APPLICANT,"878787")
				);
		List<UserEntity> list2 = userRepo.saveAll(list);
		assertEquals(7, list2.size());

	}
	
}
