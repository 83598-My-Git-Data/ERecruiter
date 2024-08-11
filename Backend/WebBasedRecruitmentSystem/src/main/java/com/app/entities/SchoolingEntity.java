package com.app.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "schooling")
@NoArgsConstructor
@Getter
@Setter
public class SchoolingEntity extends BaseEntity {
	
	
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval =true)
	@JoinColumn(name="applicant_id",nullable = false)
	@MapsId	
	private ApplicantEntity applicant;
	
	@Column(name = "class_10_board", length = 100)
	private String class10Board;
	
	@Column(name = "class_10_passing_year")
	private LocalDate class10PassingYear;
	
	@Column(name = "class_10_marks")
	private int class10Marks;
	
	@Column(name = "class_12_board", length = 100)
	private String class12Board;
	
	@Column(name = "class_12_passing_year")
	private LocalDate class12PassingYear;
	
	@Column(name = "class_12_marks")
	private int class12Marks;

	public SchoolingEntity(ApplicantEntity applicant, String class10Board, LocalDate class10PassingYear,
			int class10Marks, String class12Board, LocalDate class12PassingYear, int class12Marks) {
		
		this.applicant = applicant;
		this.class10Board = class10Board;
		this.class10PassingYear = class10PassingYear;
		this.class10Marks = class10Marks;
		this.class12Board = class12Board;
		this.class12PassingYear = class12PassingYear;
		this.class12Marks = class12Marks;
	}
	
	
}
