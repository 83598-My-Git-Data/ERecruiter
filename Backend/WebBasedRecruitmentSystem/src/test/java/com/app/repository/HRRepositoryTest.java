package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.HREntity;
import com.app.entities.UserEntity;
import com.app.exception.ResourceNotFoundException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class HRRepositoryTest {

	@Autowired
	private HREntityRepository hrRepo;
	
	@Autowired
	private UserEntityRepository userRepo;
	
	@Test
	void addHrTest()
	{
		UserEntity user=userRepo.findById(3L).orElseThrow(
				()->new ResourceNotFoundException("User", "id", 6L));
		HREntity hr=new HREntity("BBA","Bengaluru",false,"Finance",null,user);
		
		HREntity getHr=hrRepo.save(hr);
		assertEquals(3, getHr.getId());
	}
}
