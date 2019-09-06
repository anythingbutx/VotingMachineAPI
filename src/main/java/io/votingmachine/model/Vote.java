package io.votingmachine.model;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Vote")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "date")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	@ManyToOne
	@JoinColumn(name = "registeredVoter_id")
	private RegisteredVoter registeredVoter;
	
	public Vote() {
		LocalDate d = LocalDate.now();
		this.date = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public RegisteredVoter getRegisteredVoter() {
		return registeredVoter;
	}

	public void setRegisteredVoter(RegisteredVoter registeredVoter) {
		this.registeredVoter = registeredVoter;
	}

	@Override
	public int hashCode() {
		return Objects.hash(candidate, id, registeredVoter);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		return Objects.equals(candidate, other.candidate) && Objects.equals(id, other.id)
				&& Objects.equals(registeredVoter, other.registeredVoter);
	}
	
}
