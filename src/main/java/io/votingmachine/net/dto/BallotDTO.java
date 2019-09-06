package io.votingmachine.net.dto;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


public class BallotDTO {
	
	@NotNull
	@Size(min = 3, max = 200)
	private String title;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String state;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String city;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOpen;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateClosed;
	
	private List<CandidateDTO> candidates;

	public BallotDTO() {
		this.candidates = new ArrayList<>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<CandidateDTO> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<CandidateDTO> candidates) {
		this.candidates = candidates;
	}
	
}
