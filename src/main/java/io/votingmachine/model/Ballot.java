package io.votingmachine.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Ballot", uniqueConstraints={@UniqueConstraint(columnNames = {"title","dateOpen","dateClosed"})})
public class Ballot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 200)
	@Column(name = "title")
	private String title;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dateOpen")
	private Date dateOpen;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dateClosed")
	private Date dateClosed;
	
	@OneToMany(mappedBy = "ballot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Candidate> candidates;
	
	@ManyToMany(mappedBy = "ballots", fetch = FetchType.LAZY)
	private Set<Electorate> electorates;

	public Ballot() {
		this.candidates = new HashSet<>();
		this.electorates = new HashSet<>();
	}

	public Ballot(@Size(min = 3, max = 200) String title, Date dateOpen, Date dateClosed) {
		this();
		this.title = title;
		this.dateOpen = dateOpen;
		this.dateClosed = dateClosed;
	}

	public void addElectorate(Electorate e) {
		this.electorates.add(e);
		e.getBallots().add(this);
	}
	
	public void addCandidate(Candidate c) {
		this.candidates.add(c);
		c.setBallot(this);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateOpen() {
		return dateOpen;
	}

	public void setDateOpen(Date dateOpen) {
		this.dateOpen = dateOpen;
	}

	public Date getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}

	public Set<Electorate> getElectorates() {
		return electorates;
	}

	public void setElectorates(Set<Electorate> electorates) {
		this.electorates = electorates;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateClosed, dateOpen, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ballot other = (Ballot) obj;
		return Objects.equals(dateClosed, other.dateClosed) && Objects.equals(dateOpen, other.dateOpen)
				&& Objects.equals(title, other.title);
	}

}
