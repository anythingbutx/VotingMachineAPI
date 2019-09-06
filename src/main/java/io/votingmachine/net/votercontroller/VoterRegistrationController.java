package io.votingmachine.net.votercontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.votingmachine.model.RegisteredVoter;
import io.votingmachine.model.repository.service.RegisteredVoterService;
import io.votingmachine.net.dto.VoterDTO;


@RestController
public class VoterRegistrationController {
	
	@Autowired
	private RegisteredVoterService voterService;
	
	@RequestMapping("/api/post/registeredvoter")
	public ResponseEntity<?> postVoterRegistration(@Valid @RequestBody VoterDTO dto, Errors err) {
		if (err.hasErrors()) {
			return ResponseEntity.badRequest().body(err.getAllErrors());
		}
		voterService.save(dtoToEntity(dto), dto.getState(), dto.getCity());
		return new ResponseEntity <>(dto, new HttpHeaders(), HttpStatus.CREATED);
	}
	
	private RegisteredVoter dtoToEntity(VoterDTO dto) {
		RegisteredVoter ent = new RegisteredVoter();
		ent.setFirstName(dto.getFirstName());
		ent.setMiddleName(dto.getMiddleName());
		ent.setLastName(dto.getLastName());
		ent.setVoterId(dto.getVoterId());
		ent.setBirthday(dto.getBirthday());
		return ent;
	}
	
	
}
