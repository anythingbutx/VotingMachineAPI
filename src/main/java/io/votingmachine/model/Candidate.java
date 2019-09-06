package io.votingmachine.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Candidate")
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 200)
	@Column(name = "firstName")
	private String firstName;
	
	@Size(min = 0, max = 200)
	@Column(name = "middleName")
	private String middleName;
	
	@NotNull
	@Size(min = 3, max = 200)
	@Column(name = "lastName")
	private String lastName;

	@NotEmpty
	@Column(name = "photoURL")
	private String photoURL;
	
	@ManyToOne
	@JoinColumn(name = "ballot_id")
	private Ballot ballot;
	
	@OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
	private Set<Vote> votes;

	public Candidate() {
		this.votes = new HashSet<>();
	}

	public Candidate(@NotNull @Size(min = 3, max = 200) String firstName,
			@NotNull @Size(min = 0, max = 200) String middleName, @NotNull @Size(min = 3, max = 200) String lastName,
			@NotEmpty String photoURL) {
		this();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.photoURL = photoURL;
	}

	public Candidate(@NotNull @Size(min = 3, max = 200) String firstName,
			@NotNull @Size(min = 0, max = 200) String middleName, @NotNull @Size(min = 3, max = 200) String lastName,
			@NotEmpty String photoURL, Ballot ballot) {
		this( firstName, middleName, lastName, photoURL);
		this.ballot = ballot;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public Ballot getBallot() {
		return ballot;
	}

	public void setBallot(Ballot ballot) {
		this.ballot = ballot;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName, middleName, photoURL);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candidate other = (Candidate) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(middleName, other.middleName)
				&& Objects.equals(photoURL, other.photoURL);
	}
	
}
