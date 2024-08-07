package com.app.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
@Table(name = "languages")
@NoArgsConstructor
@Getter
@Setter
public class LanguageEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long languageId;
	
	private String name;
	
	private String proficiency;
	
	@ManyToMany(mappedBy = "languages")
    private Set<ApplicantEntity> applicants = new HashSet<>();

	public LanguageEntity(String name, String proficiency) {
		
		this.name = name;
		this.proficiency = proficiency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(languageId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LanguageEntity other = (LanguageEntity) obj;
		return Objects.equals(languageId, other.languageId);
	}
	
}
