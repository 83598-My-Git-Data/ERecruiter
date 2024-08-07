package com.app.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Table(name="question")
@Entity
public class QuestionEntity extends BaseEntity{
	
	@Column(columnDefinition = "TEXT")
	private String questionText;
	
	@Column(name = "correct_option", 
			columnDefinition = "CHAR(1) CHECK (correct_option IN ('A', 'B', 'C', 'D'))",
			nullable = false)
	private char correctOption;
	
	@Column(columnDefinition = "TEXT",name = "option_A")
	private String optionA;
	@Column(columnDefinition = "TEXT",name="option_B")
	private String optionB;
	@Column(columnDefinition = "TEXT",name="option_C")
	private String optionC;
	@Column(columnDefinition = "TEXT",name="options_D")
	private String optionD;
	
	
	public QuestionEntity(String questionText,char correctOption,String optionA,String optionB,String optionC,String optionD)
	{
		this.questionText=questionText;
		this.correctOption=correctOption;
		this.optionA=optionA;
		this.optionB=optionB;
		this.optionC=optionC;
		this.optionD=optionD;
	}
	
	
	@ManyToMany(mappedBy = "question")
	Set<ExamEntity> exam=new HashSet<ExamEntity>();

	@Override
	public int hashCode() {
		return Objects.hash(questionText);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionEntity other = (QuestionEntity) obj;
		return Objects.equals(questionText, other.questionText);
	}
	
}
