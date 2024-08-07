package com.app.entities;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="exam")
@Setter @Getter
@NoArgsConstructor
public class ExamEntity extends BaseEntity{


	@Column(length = 30,nullable = false)
	private String examName;
	private LocalDate createdDate;
	private int duration;
	
	 // Many-to-Many Relationship with QuestionEntity
	@ManyToMany
	@JoinTable(
	  name = "exam_question", 
	  joinColumns = @JoinColumn(name="exam_id"), 
	  inverseJoinColumns = @JoinColumn(name="question_id"))
	Set<QuestionEntity> question=new HashSet<QuestionEntity>();

	
	public ExamEntity(String exameName,LocalDate createdDate,int duration)
	{
		this.duration=duration;
		this.examName=exameName;
		this.createdDate=createdDate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(createdDate, examName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamEntity other = (ExamEntity) obj;
		return Objects.equals(createdDate, other.createdDate) && Objects.equals(examName, other.examName);
	}
	
}
