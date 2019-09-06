package io.votingmachine.model.repository.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.votingmachine.model.Candidate;
import io.votingmachine.model.RegisteredVoter;
import io.votingmachine.model.Vote;
import io.votingmachine.model.repository.VoteRepository;

@Service
public class VoteService {
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private RegisteredVoterService voterService;
	
	@Transactional
	public Vote save(String voterid, Long candidateid) {
		Optional<RegisteredVoter> opVoter = voterService.findBy(voterid);
		Optional<Candidate> opCand = candidateService.findBy(candidateid);
		if (opVoter.isPresent() && opCand.isPresent()) {
			Vote vote = new Vote();
			vote.setRegisteredVoter(opVoter.get());
			vote.setCandidate(opCand.get());
			return voteRepository.save(vote);
		}
		return null;
	}
}
