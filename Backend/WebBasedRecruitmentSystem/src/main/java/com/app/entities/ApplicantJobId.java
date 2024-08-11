package com.app.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ApplicantJobId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="job_id")
	private Long jobId;
	
	@Column(name="applicant_id")
	private Long applicantId;

	@Override
	public int hashCode() {
		return Objects.hash(applicantId, jobId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicantJobId other = (ApplicantJobId) obj;
		return Objects.equals(applicantId, other.applicantId) && Objects.equals(jobId, other.jobId);
	}
	
	
	
}
