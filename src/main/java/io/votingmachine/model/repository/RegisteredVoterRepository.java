package io.votingmachine.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.votingmachine.model.RegisteredVoter;

@Repository
public interface RegisteredVoterRepository extends CrudRepository<RegisteredVoter, Long> {
	
	//public RegisteredVoter findByVoterId(String voterId);
	public Optional<RegisteredVoter> findByVoterId(String voterId);
}
