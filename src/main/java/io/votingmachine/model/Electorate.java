package io.votingmachine.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;





@Entity
@Table(name = "Electorate")
public class Electorate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "electorate", cascade = CascadeType.ALL)
	private Set<RegisteredVoter> registeredVoter;
	
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "electorate_ballot", joinColumns = @JoinColumn(name = "electorate_id"), inverseJoinColumns = @JoinColumn(name = "ballot_id"))
    private Set<Ballot> ballots;
 
    public Electorate() {
    	this.registeredVoter = new HashSet<>();
    	this.ballots = new HashSet<>();
    }
    
	public Electorate(City city) {
		this();
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Set<RegisteredVoter> getRegisteredVoter() {
		return registeredVoter;
	}

	public void setRegisteredVoter(Set<RegisteredVoter> registeredVoter) {
		this.registeredVoter = registeredVoter;
	}

	public Set<Ballot> getBallots() {
		return ballots;
	}

	public void setBallots(Set<Ballot> ballots) {
		this.ballots = ballots;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Electorate other = (Electorate) obj;
		return Objects.equals(id, other.id);
	}

}
