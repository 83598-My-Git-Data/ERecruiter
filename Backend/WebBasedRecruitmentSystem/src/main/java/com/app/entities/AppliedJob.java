package com.app.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing the relationship between an applicant and a job.
 * The composite primary key is composed of the applicant's ID and the job's ID.
 */

@Setter @Getter @NoArgsConstructor
@Entity
@Table(name="applied_job")
public class AppliedJob {

	@EmbeddedId
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ApplicantJobId id;

	// Many-to-One association with the ApplicantEntity using the applicant_id
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("applicant_id")
	@JoinColumn(name="applicant_id")
    private ApplicantEntity applicant;
 
	// Many-to-One association with the JobInfoEntity using the job_id
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("job_id")
    @JoinColumn(name="job_id")
    private JobInfoEntity job;
    
    
    @Column(name="job_status",length = 20)
    @Enumerated(EnumType.STRING)
    private JobStatus status;

	@Override
	public int hashCode() {
		return Objects.hash(applicant, job);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppliedJob other = (AppliedJob) obj;
		return Objects.equals(applicant, other.applicant) && Objects.equals(job, other.job);
	}

	public AppliedJob(ApplicantJobId applicantJobId, JobStatus status) {
	
		this.id = applicantJobId;
		
		this.status = status;
	}
  
	
	
}
