package io.votingmachine.net.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class VoterDTO {

	@NotNull
	@Size(min = 3, max = 200)
	private String firstName;
	
	@NotNull
	@Size(min = 0, max = 200)
	private String middleName;
	
	@NotNull
	@Size(min = 3, max = 200)
	private String lastName;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String state;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String city;
	
	@NotNull
	@Size(min = 4, max = 20)
	private String voterId;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	public VoterDTO() {}
	
	public void setBirthday(String s) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate d = LocalDate.parse(s, formatter);
		this.birthday = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
