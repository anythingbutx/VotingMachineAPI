package io.votingmachine.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.votingmachine.model.Candidate;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

}
