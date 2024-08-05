package com.app.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="job_info")
@Setter @Getter @NoArgsConstructor

public class JobInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="job_id")
	private Long jobId;
	
	@Column(name="job_title",length=30)
	private String jobTitle;
	
	@Column(name="experience_required")
	private int experienceRequired;
	
	@Column(name="work_schedule",length = 30)
	@Enumerated(EnumType.STRING)
	private WorkSchedule workSchedule;
	
	@Column(name="salary")
	private int salary;
	
	@Column(name="application_deadline")
	private LocalDate applicationDeadline;
	
	@Column(name="location",length=30)
	private String location;
	
	@Column(name="job_created_date")
	private LocalDate jobCreatedDate;
	
	private String qualification;
	
	@ManyToOne(optional = false)
	private HREntity hr;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DepartmentEntity department;

	@Column(name="vacancies")
	private int vacancies;
	
	@Column(name="status",columnDefinition = "Boolean")
	private boolean status;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@ManyToMany
	@JoinTable(
	  name = "saved_job", 
	  joinColumns = @JoinColumn(name="job_id"), 
	  inverseJoinColumns = @JoinColumn(name="applicant_id"))
	private Set<ApplicantEntity> applicant=new HashSet<ApplicantEntity>();
	
	

	@Override
	public int hashCode() {
		return Objects.hash(jobId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobInfoEntity other = (JobInfoEntity) obj;
		return Objects.equals(jobId, other.jobId);
	}
	
	
	public void addApplicant(ApplicantEntity applicant)
	{
		this.applicant.add(applicant);
		applicant.getJob().add(this);
	}

	public void removeApplicant(ApplicantEntity applicant)
	{
		this.applicant.remove(applicant);
		applicant.getJob().remove(this);
	}

	public JobInfoEntity(String jobTitle, int experienceRequired, WorkSchedule workSchedule, int salary,
			LocalDate applicationDeadline, String location, LocalDate jobCreatedDate, String qualification, HREntity hr,
			DepartmentEntity department,boolean status,String description) {
		super();
		this.jobTitle = jobTitle;
		this.experienceRequired = experienceRequired;
		this.workSchedule = workSchedule;
		this.salary = salary;
		this.applicationDeadline = applicationDeadline;
		this.location = location;
		this.jobCreatedDate = jobCreatedDate;
		this.qualification = qualification;
		this.hr = hr;
		this.department = department;
		this.status = status;
		this.description=description;
	}


	// mapping a many to many relation between applicant and jobs using third
	// table applied job
	@OneToMany(
	        mappedBy = "job",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	    private Set<AppliedJob> appliedJobs = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "job_skill",
               joinColumns = @JoinColumn(name = "job_id"),
               inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<SkillEntity> JobSkills = new HashSet<>();
	
	public void addSkill(SkillEntity jobSkill)
	{
		this.JobSkills.add(jobSkill);
		jobSkill.getJobInfo().add(this);
	}
	
	public void removeSkill(SkillEntity jobSkill)
	{
		this.JobSkills.remove(jobSkill);
		jobSkill.getJobInfo().remove(this);
	}

	@Override
	public String toString() {
		return "JobInfoEntity [jobId=" + jobId + ", jobTitle=" + jobTitle + ", experienceRequired=" + experienceRequired
				+ ", workSchedule=" + workSchedule + ", salary=" + salary + ", applicationDeadline="
				+ applicationDeadline + ", location=" + location + ", jobCreatedDate=" + jobCreatedDate
				+ ", qualification=" + qualification + ", vacancies=" + vacancies + ", status=" + status + "]";
	}
	
	
}
