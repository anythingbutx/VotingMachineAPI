package io.votingmachine.model.repository.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.votingmachine.model.Candidate;
import io.votingmachine.model.repository.CandidateRepository;

@Service
public class CandidateService {
	
	@Autowired
	CandidateRepository candidateRepository;
	
	public Optional<Candidate> findBy(Long id) {
		return candidateRepository.findById(id);
	}
	
	public Candidate save(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
}
