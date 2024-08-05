package com.app.entities;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter
@NoArgsConstructor 
@Entity
@Table(name="scheduled_exam")
public class ScheduledExamEntity extends BaseEntity{
	
	
	@Column(name="scheduled_date")
	private LocalDate scheduledDate;
	
	@ManyToOne
	@JoinColumn(name="applicant_id")
	private ApplicantEntity applicant;
	
	@ManyToOne
	@JoinColumn(name="exam_id")
	private ExamEntity exam;

	public ScheduledExamEntity(LocalDate scheduledDate, ApplicantEntity applicant, ExamEntity exam) {
		super();
		this.scheduledDate = scheduledDate;
		this.applicant = applicant;
		this.exam = exam;
	}
	
}

