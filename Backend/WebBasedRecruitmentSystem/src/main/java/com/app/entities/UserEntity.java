package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_credentials")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "password") // toString excluding password
public class UserEntity extends BaseEntity {
	
	@Column(length = 20,nullable=false)
	private String firstName;
	
	@Column(length = 20,nullable=false)
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private Gender gender;
	
	@Column(length = 30, unique = true,nullable=false)
	private String email;
	
	@Column(length=13,name="phone_number")
	private String phoneNumber;
	
	@Column(length = 300, nullable = false)
	private String password;
	
	@Column(nullable = true)
	private LocalDate dob;
	
	
	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private UserRole role;
	
	@Column(length = 6)
	private String otp;
	
}
