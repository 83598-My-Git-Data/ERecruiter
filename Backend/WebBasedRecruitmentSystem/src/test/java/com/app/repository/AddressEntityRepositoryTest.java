package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;

import com.app.entities.AddressEntity;
import com.app.entities.UserEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AddressEntityRepositoryTest {
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private UserEntityRepository userRepo;
	
	@Test
	public void addAddress() {
		UserEntity user1= userRepo.findById(1l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		UserEntity user2= userRepo.findById(2l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		UserEntity user3= userRepo.findById(3l).orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		List<AddressEntity> list=List.of(
				new AddressEntity("D-35, Mahavirvihar","110081","Delhi","India",user1),
				new AddressEntity("m-01, Andheru","452301","goa","India",user2),
				new AddressEntity("n-23, Colaba","324312","Mumbai","India",user3)
				);
		List<AddressEntity> list2 = addressRepo.saveAll(list);
		assertEquals(3, list2.size());
	}
}
