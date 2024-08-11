package com.app.entities;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "applicants")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor()
public class ApplicantEntity extends BaseEntity{

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="applicant_id",nullable = false)
	@MapsId	
	private UserEntity user;
	
	@Column(name = "email_id_verify_status",columnDefinition = "BOOLEAN")
	private boolean emailIdVerifyStatus; 
	
	@Column(name = "mobile_num_verify_status",columnDefinition = "BOOLEAN")
	private boolean mobileNumVerifyStatus;
	
	@Column(columnDefinition = "TEXT")
	private String resumeLink;
	
	@Column(columnDefinition = "TEXT")
	private String resumeHeadLine;
	
	@Column(columnDefinition = "TEXT")
	private String profileSummary;
	
	@Column(columnDefinition = "TEXT")
	private String profilePictureLink;
	
	@Column(length = 10)
	private String maritalStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 30)//enum
	private NoticePeriod noticePeriod;
	
	//list of skills that applicant has
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "applicant_skill",
               joinColumns = @JoinColumn(name = "applicant_id"),
               inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<SkillEntity> skills = new HashSet<>();
	
	//list of languages applicant can speak
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "applicant_language",
               joinColumns = @JoinColumn(name = "applicant_id"),
               inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<LanguageEntity> languages = new HashSet<>();


	public ApplicantEntity(UserEntity user, boolean emailIdVerifyStatus, boolean mobileNumVerifyStatus,
			String resumeHeadLine, String profileSummary, String maritalStatus, NoticePeriod noticePeriod,String profilePicture,String resumeLink) {
		
		this.user = user;
		this.emailIdVerifyStatus = emailIdVerifyStatus;
		this.mobileNumVerifyStatus = mobileNumVerifyStatus;
		this.resumeHeadLine = resumeHeadLine;
		this.profileSummary = profileSummary;
		this.maritalStatus = maritalStatus;
		this.noticePeriod = noticePeriod;
		this.profilePictureLink=profilePicture;
		this.resumeLink=resumeLink;
	}
	
	public ApplicantEntity(UserEntity user, boolean emailIdVerifyStatus, boolean mobileNumVerifyStatus,
			String resumeHeadLine, String profileSummary, String maritalStatus, NoticePeriod noticePeriod) {
		
		this.user = user;
		this.emailIdVerifyStatus = emailIdVerifyStatus;
		this.mobileNumVerifyStatus = mobileNumVerifyStatus;
		this.resumeHeadLine = resumeHeadLine;
		this.profileSummary = profileSummary;
		this.maritalStatus = maritalStatus;
		this.noticePeriod = noticePeriod;
		
	}
	
	
	
	//adds skills to the skills list of applicant
	public void addSkill(SkillEntity skill) {
		
		skills.add(skill);
		skill.getApplicants().add(this);
	}
	
	
	//removes skills to the skills list of applicant
	public void removeSkill(SkillEntity skill) {
		
		skills.remove(skill);
		skill.getApplicants().remove(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(user.getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicantEntity other = (ApplicantEntity) obj;
		return Objects.equals(user.getId(), other.user.getId());
	}
	
	// Many to many relation with job
	// one applicant can have multiple jobs saved as well as applied
	@ManyToMany(mappedBy = "applicant")
	private Set<JobInfoEntity> job=new HashSet<JobInfoEntity>();
	
	
	// Mapping a many to many relation between applicant and jobs using third
	// table applied job
    @OneToMany(
        mappedBy = "applicant",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<AppliedJob> appliedJobs = new HashSet<>();
    
    
    
    
    //adds languages in the applicant profile
    public void addLanguage(LanguageEntity language) {
		
		languages.add(language);
		language.getApplicants().add(this);
	}
    
    
    
    //removes languages in the applicant profile
    public void removeLanguage(LanguageEntity language) {
		
		languages.remove(language);
		language.getApplicants().remove(this);
	}


	
    
    

}
