package io.votingmachine.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "RegisteredVoter")
public class RegisteredVoter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "firstName")
	@Size(min = 3, max = 200)
	private String firstName;
	
	@Column(name = "middleName")
	@Size(min = 0, max = 200)
	private String middleName;
	
	@Column(name = "lastName")
	@Size(min = 3, max = 200)
	private String lastName;
	
	@Column(name = "voterId", unique = true)
	@Size(min = 8, max = 20)
	private String voterId;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthday")
	private Date birthday;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "submitedOn")
	private Date submitedOn;
	
	@ManyToOne
	@JoinColumn(name = "electorate_id")
	private Electorate electorate;
	
	@OneToMany(mappedBy = "registeredVoter", cascade = CascadeType.ALL)
	private Set<Vote> votes;
	
	public RegisteredVoter() {
		this.votes = new HashSet<>();
		LocalDate d = LocalDate.now();
		this.submitedOn = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public RegisteredVoter(@Size(min = 3, max = 200) String firstName, @Size(min = 0, max = 200) String middleName,
			@Size(min = 3, max = 200) String lastName, @Size(min = 4, max = 20) String voterId, Date birthday) {
		this();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.voterId = voterId;
		this.birthday = birthday;
	}

	public RegisteredVoter(@Size(min = 3, max = 200) String firstName, @Size(min = 0, max = 200) String middleName,
			@Size(min = 3, max = 200) String lastName, @Size(min = 4, max = 20) String voterId, Date birthday,
			Electorate electorate) {
		this(firstName, middleName, lastName, voterId, birthday);
		this.electorate = electorate;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public Electorate getElectorate() {
		return electorate;
	}

	public void setElectorate(Electorate electorate) {
		this.electorate = electorate;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, firstName, id, lastName, middleName, submitedOn, voterId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisteredVoter other = (RegisteredVoter) obj;
		return Objects.equals(birthday, other.birthday) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(middleName, other.middleName) && Objects.equals(submitedOn, other.submitedOn)
				&& Objects.equals(voterId, other.voterId);
	}
	
}
