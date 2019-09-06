package io.votingmachine.net.ballotcontroller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.votingmachine.model.Ballot;
import io.votingmachine.model.Candidate;
import io.votingmachine.model.repository.service.BallotService;
import io.votingmachine.model.repository.service.ElectorateService;
import io.votingmachine.model.repository.service.StateService;
import io.votingmachine.net.dto.BallotDTO;
import io.votingmachine.net.dto.CandidateDTO;



@RestController
public class BallotController {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private ElectorateService electorateService;
	
	@Autowired
	private BallotService ballotService;
	
	@RequestMapping("/api/get/electorateballots/{state}/{city}")
	public ResponseEntity<?> getBallots(@PathVariable("state") String state, @PathVariable("city") String city) {
		return new ResponseEntity <>(electorateService.fetchBallots(state, city), new HttpHeaders(), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping("/api/post/ballot")
	public ResponseEntity<?> postBallot(@Valid @RequestBody BallotDTO dto, Errors err) {
		if (err.hasErrors() || validateBallot(dto, err).hasErrors()) {
			return ResponseEntity.badRequest().body(err.getAllErrors());
		}
		
		ballotService.saveHelper(dtoToEntity(dto), dto.getState(), dto.getCity());
		return new ResponseEntity <>(dto, new HttpHeaders(), HttpStatus.CREATED);
	}
	
	private Errors validateBallot(BallotDTO dto, Errors errors) {
		if (ballotService.findBy(dto.getTitle(), dto.getDateOpen(), dto.getDateClosed()) != null) {
			errors.reject("title", "Ballot is not unique");
			errors.reject("dateOpen", "Ballot is not unique");
			errors.reject("dateClosed", "Ballot is not unique");
		}
		return validateElectorate(dto.getState(), dto.getCity(), errors);
	}

	private Errors validateElectorate(String state, String city, Errors errors) {
		state = state.toLowerCase();
		city = city.toLowerCase();
		if (!state.equals("all") && !city.equals("all") && !electorateService.existsBy(state, city)) {
			errors.reject("state", "Electorate not found");
			errors.reject("city", "Electorate not found");
		} else if (!state.equals("all") && city.equals("all") && !stateService.existsBy(state)) {
			errors.reject("state", "Electorate not found");
		} else if (state.equals("all") && !city.equals("all")) {
			errors.reject("city", "Invalid input");
		}
		return errors;
	}
	
	private Ballot dtoToEntity(BallotDTO dto) {
		Ballot ballot = new Ballot(dto.getTitle(), dto.getDateOpen(), dto.getDateClosed());
		for(CandidateDTO c: dto.getCandidates()) {
			Candidate cand = new Candidate();
			cand.setFirstName(c.getFirstName());
			cand.setMiddleName(c.getMiddleName());
			cand.setLastName(c.getLastName());
			cand.setPhotoURL(c.getPhotoURL());
			ballot.addCandidate(cand);
		}
		return ballot;
	}
	
	private BallotDTO entityToDto(Ballot ent) {
		BallotDTO dto = new BallotDTO();
		dto.setTitle(ent.getTitle());
		dto.setDateOpen(ent.getDateOpen());
		dto.setDateClosed(ent.getDateClosed());
		for(Candidate c: ent.getCandidates()) {
			CandidateDTO cand = new CandidateDTO();
			cand.setFirstName(c.getFirstName());
			cand.setMiddleName(c.getMiddleName());
			cand.setLastName(c.getLastName());
			dto.getCandidates().add(cand);
		}
		return dto;
	}
	
}
