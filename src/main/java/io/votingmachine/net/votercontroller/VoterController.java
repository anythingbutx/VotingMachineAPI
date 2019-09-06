package io.votingmachine.net.votercontroller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.votingmachine.model.repository.service.RegisteredVoterService;
import io.votingmachine.model.repository.service.VoteService;
import io.votingmachine.net.dto.BallotDTO;

@RestController
public class VoterController {
	
	@Autowired
	private RegisteredVoterService voterService;
	
	@Autowired
	private VoteService voteService;
	
	@RequestMapping("/api/get/ballots/{voterid}")
	public ResponseEntity<?> getBallots(@PathVariable("voterid") String voterid) {
		if(!voterService.findBy(voterid).isPresent()) {
			return ResponseEntity.badRequest().body("Voter not found with voterid = " + voterid);
		}
		Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<BallotDTO> list = voterService.fetchBallots(voterid, date);
		if (list.isEmpty()) {
			return ResponseEntity.badRequest().body("No Ballots available to voter with voterid = " + voterid);
		}
		return new ResponseEntity <>(list, new HttpHeaders(), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping("/api/post/vote/{voterid}/{candidateid}") 
	public ResponseEntity<?> postVote(@PathVariable("voterid") String voterid, @PathVariable("candidateid") Long candidateid) {
		if (voteService.save(voterid, candidateid) == null) {
			return ResponseEntity.badRequest().body("Bad input.");
		}
		return new ResponseEntity <>(null, new HttpHeaders(), HttpStatus.CREATED);
	}
}
