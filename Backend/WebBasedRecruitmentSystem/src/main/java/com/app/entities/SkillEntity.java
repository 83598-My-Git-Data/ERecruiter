package com.app.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "skills")
@NoArgsConstructor
@Getter
@Setter
public class SkillEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long skillId;
	
	@Column(length = 20)
	private String name;
	
	@ManyToMany(mappedBy = "skills")
    private Set<ApplicantEntity> applicants = new HashSet<>();

	//jobInfoEntity
	
	
	public SkillEntity(String name) {
		
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkillEntity other = (SkillEntity) obj;
		return Objects.equals(name, other.name);
	}
	
	
	
	
	
}
