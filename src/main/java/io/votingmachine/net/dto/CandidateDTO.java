package io.votingmachine.net.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class CandidateDTO {
	
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 200)
	private String firstName;
	
	@Size(min = 0, max = 200)
	private String middleName;
	
	@NotNull
	@Size(min = 3, max = 200)
	private String lastName;
	
	@NotEmpty
	private String photoURL;

	public CandidateDTO() {}

	public CandidateDTO(String firstName, String middleName, String lastName, String photoURL) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.photoURL = photoURL;
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

	@Override
	public String toString() {
		return "CandidateRegistration [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", photoURL=" + photoURL + "]";
	}
	
	
}
