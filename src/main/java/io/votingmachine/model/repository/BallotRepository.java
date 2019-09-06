package io.votingmachine.model.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.votingmachine.model.Ballot;


@Repository
public interface BallotRepository extends CrudRepository<Ballot, Long> {
	
	public List<Ballot> findByTitle(String title);
	public List<Ballot> findByDateOpen(Date dateOpen);
	public List<Ballot> findByDateOpenAndDateClosed(Date dateOpen, Date dateClosed);
	public Ballot findByTitleAndDateOpenAndDateClosed(String title, Date DateOpen, Date DateClosed);
}
