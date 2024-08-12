package com.app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HrServiceTest {

	@Autowired
	private HrService hrService;
	
	@Test
	void getHrTest()
	{
//		HrResponse hr = hrService.getHrDetails("hina@gmail.com");
//		System.out.println(hr);
	}
}
