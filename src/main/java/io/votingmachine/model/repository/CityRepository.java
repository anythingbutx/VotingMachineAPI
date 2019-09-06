package io.votingmachine.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import io.votingmachine.model.City;
import io.votingmachine.model.State;

public interface CityRepository extends CrudRepository <City, Long> {
	public City findByNameAndState(String name, State state);
	public List<City> findByState(State state);
	public boolean existsByNameAndState(String name, State state);
}
