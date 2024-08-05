package com.app.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="hr_table")
public class HREntity extends BaseEntity{

	@Column(length = 60,nullable = false)
	private String qualification;
	
	@Column(length = 60,nullable = false)
	private String officeLocation;
	
	@Column(nullable = false,columnDefinition = "boolean")
	private boolean activeStatus;
	
	@Column(length = 60,nullable = false)
	private String department;
	
	private String imageURL;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hr_id",nullable = false)
	@MapsId	
	private UserEntity user;
	
	
}
