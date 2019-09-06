package io.votingmachine.model.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.votingmachine.model.City;
import io.votingmachine.model.Electorate;



@Repository
public interface ElectorateRepository extends CrudRepository<Electorate, Long> {
	public Electorate findByCity(City city);
}
