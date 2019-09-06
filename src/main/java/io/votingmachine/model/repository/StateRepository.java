package io.votingmachine.model.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.votingmachine.model.State;


@Repository
public interface StateRepository extends CrudRepository<State, Long> {
	public State findByName(String name);
	public boolean existsByName(String name);
}
