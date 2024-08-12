package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.DepartmentEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class DepartmentEntityRepositoryTest {

	@Autowired
	private DepartmentEntityRepository depRepo;
	
	@Test
	void addDepartmentTest()
	{
		List<DepartmentEntity> departList = List.of(
			    new DepartmentEntity("IT"),
			    new DepartmentEntity("HR"),
			    new DepartmentEntity("Finance"),
			    new DepartmentEntity("Marketing"),
			    new DepartmentEntity("Sales"),
			    new DepartmentEntity("Engineering"),
			    new DepartmentEntity("Operations"),
			    new DepartmentEntity("Customer Service"),
			    new DepartmentEntity("Research and Development"),
			    new DepartmentEntity("Legal")
			);
		List<DepartmentEntity> departListTest=depRepo.saveAll(departList);
		assertEquals(10, departListTest.size());
	}
}
