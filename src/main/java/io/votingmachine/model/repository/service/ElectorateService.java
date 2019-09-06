package io.votingmachine.model.repository.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.votingmachine.model.Ballot;
import io.votingmachine.model.Candidate;
import io.votingmachine.model.Electorate;
import io.votingmachine.model.repository.ElectorateRepository;
import io.votingmachine.net.dto.BallotDTO;
import io.votingmachine.net.dto.CandidateDTO;



@Service
public class ElectorateService {
	
	@Autowired
	private ElectorateRepository electorateRepository;
	
	@Autowired
	private CityService cityService;
	
	@Transactional
	public boolean existsBy(String state, String city) {
		return (cityService.findBy(state, city).getElectorate() != null);
	}
	
	@Transactional 
	public Electorate findBy(String state, String city) {
		return cityService.findBy(state, city).getElectorate();
	}	
	
	@Transactional 
	public List<Electorate> findBy(String state) {
		List<Electorate> list = new ArrayList<>();
		cityService.findBy(state).forEach(city->list.add(city.getElectorate()));
		return list;
	}
	
	@Transactional 
	public List<Electorate> findAll() {
		List<Electorate> list = new ArrayList<>();
		this.electorateRepository.findAll().forEach(list::add);
		return list;
	}
	
	@Transactional
	public Iterable<Electorate> saveAll(List<Electorate> list) {
		return electorateRepository.saveAll(list);
	}
	
	@Transactional
	public Electorate save(Electorate electorate) {
		return electorateRepository.save(electorate);
	}	
	
	@Transactional
	public List<BallotDTO> fetchBallots(Electorate e) {
		List<BallotDTO> list = new ArrayList<>();
		e.getBallots().forEach(b -> list.add(this.entityToDto(b)));
		return list;
	}
	
	@Transactional
	public List<BallotDTO> fetchBallots(String state, String city) {
		Electorate e = this.findBy(state, city);
		List<BallotDTO> list = new ArrayList<>();
		e.getBallots().forEach(b -> list.add(this.entityToDto(b)));
		return list;
	}
	
	private BallotDTO entityToDto(Ballot ent) {
		BallotDTO dto = new BallotDTO();
		dto.setTitle(ent.getTitle());
		dto.setDateOpen(ent.getDateOpen());
		dto.setDateClosed(ent.getDateClosed());
		for(Candidate c: ent.getCandidates()) {
			CandidateDTO cand = new CandidateDTO();
			cand.setId(c.getId());
			cand.setFirstName(c.getFirstName());
			cand.setMiddleName(c.getMiddleName());
			cand.setLastName(c.getLastName());
			cand.setPhotoURL(c.getPhotoURL());
			dto.getCandidates().add(cand);
		}
		return dto;
	}
	
}
