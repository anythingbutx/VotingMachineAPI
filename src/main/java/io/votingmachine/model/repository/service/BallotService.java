package io.votingmachine.model.repository.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.votingmachine.model.Ballot;
import io.votingmachine.model.repository.BallotRepository;


@Service
public class BallotService {
	
	@Autowired
	BallotRepository ballotRepository;
	
	@Autowired
	ElectorateService electorateService;
	
	@Transactional
	public Ballot save(Ballot ballot) {
		electorateService.findAll().forEach(e-> ballot.addElectorate(e));
		return ballotRepository.save(ballot);
	}
	
	@Transactional
	public Ballot save(Ballot ballot, String state) {
		electorateService.findBy(state).forEach(e-> ballot.addElectorate(e));
		return ballotRepository.save(ballot);
	}
	
	@Transactional
	public Ballot save(Ballot ballot, String state, String city) {
		ballot.addElectorate(electorateService.findBy(state, city));
		return ballotRepository.save(ballot);
	}
	
	@Transactional
	public Ballot saveHelper(Ballot ballot, String state, String city) {
		if (state.equals("all") && city.equals("all")) {
			return this.save(ballot);
		}
		if (!state.equals("all") && city.equals("all")) {
			return this.save(ballot, state);
		}
		return this.save(ballot, state, city);
	}
	
	public List<Ballot> findAll() {
		List<Ballot> list = new ArrayList<>();
		ballotRepository.findAll().forEach(list::add);
		return list;
	}
	
	public List<Ballot> findBy(String title) {
		return ballotRepository.findByTitle(title);
	}
	
	public List<Ballot> findBy(Date dateOpen) {
		return ballotRepository.findByDateOpen(dateOpen);
	}
	
	public List<Ballot> findBy(Date dateOpen, Date dateClosed) { 
		return ballotRepository.findByDateOpenAndDateClosed(dateOpen, dateClosed);
	}
	
	public Ballot findBy(String title, Date dateOpen, Date dateClosed) {
		return ballotRepository.findByTitleAndDateOpenAndDateClosed(title, dateOpen, dateClosed);
	}
}
