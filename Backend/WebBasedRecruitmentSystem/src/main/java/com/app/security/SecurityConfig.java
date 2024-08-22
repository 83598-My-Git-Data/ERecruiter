package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity//to enable spring sec frmwork support
@Configuration //to tell SC , this is config class containing @Bean methods
@EnableGlobalMethodSecurity(prePostEnabled = true)
//To enable method level authorization support : pre n post authorization
public class SecurityConfig {
	//dependency : password encoder
	@Autowired
	private PasswordEncoder enc;
	
	//dependency : custom jwt auth filter
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	//dependency : custom auth entry point
	@Autowired
	private CustomAuthenticationEntryPoint authEntry;
	
	
	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception
	{
		//URL based authorization rules
		http.cors()
		//added for allowing cross origin access
		.configurationSource(request ->
		{
			CorsConfiguration corsConfig = new CorsConfiguration();
		    corsConfig.addAllowedOrigin("*");
		    corsConfig.addAllowedMethod("*");
		    corsConfig.addAllowedHeader("*");
		    return corsConfig;
		})
		.and()
		//disable CSRF token generation n verification
		.csrf().disable()
		.exceptionHandling().authenticationEntryPoint(authEntry).
		and().
		authorizeRequests()
		.antMatchers("/users/signup","/users/signin",
				"/v*/api-doc*/**","/swagger-ui/**",
				"/users/send-otp","/users/reset-password",
				"/users/verify-otp").permitAll()
		// only required for JS clnts (react / angular) : for the pre flight requests
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/hr/**").hasRole("HR")
		.antMatchers("/applicant/**","/job/**,/skill/**").hasRole("APPLICANT")
		.anyRequest().authenticated()
		.and()
		//to tell spring security : not to use HttpSession to store user's auth details
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and()
		//inserting jwt filter before security filter
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	
		return http.build();
	}
	//configure AuthMgr as a spring bean
	@Bean
	public AuthenticationManager authenticationManager
	(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
}
