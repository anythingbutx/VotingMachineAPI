package io.votingmachine.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.votingmachine.model.Vote;


@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

}
