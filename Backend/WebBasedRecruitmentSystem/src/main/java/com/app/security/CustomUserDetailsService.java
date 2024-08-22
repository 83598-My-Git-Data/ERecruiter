package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.UserEntity;
import com.app.repository.UserEntityRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	// dep : dao layer
	@Autowired
	private UserEntityRepository userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userDao.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		return new CustomUserDetails(user);
	}

}
