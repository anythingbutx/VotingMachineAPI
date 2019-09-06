package io.votingmachine.model.repository.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.votingmachine.model.RegisteredVoter;
import io.votingmachine.model.repository.RegisteredVoterRepository;
import io.votingmachine.net.dto.BallotDTO;

@Service
public class RegisteredVoterService {

	@Autowired
	private RegisteredVoterRepository voterRepository;
	
	@Autowired
	private ElectorateService electorateService;
	
	
	@Transactional
	public Optional<RegisteredVoter> findBy(String voterid) {
		return voterRepository.findByVoterId(voterid);
	}
	
	@Transactional 
	public List<BallotDTO> fetchBallots(String voterid) {
		return electorateService.fetchBallots(
				voterRepository.findByVoterId(voterid).get().getElectorate()
				);
	}
	
	@Transactional 
	public List<BallotDTO> fetchBallots(String voterid, Date date) {
		List<BallotDTO> list = this.fetchBallots(voterid);
		for(BallotDTO dto: list) {
			if (!dto.getDateOpen().before(date) || !dto.getDateClosed().after(date)) {
				list.remove(dto);
			}
		}
		return list;
	}
	
	
	@Transactional
	public RegisteredVoter save(RegisteredVoter registeredVoter, String state, String city) {
		registeredVoter.setElectorate(electorateService.findBy(state, city));
		return voterRepository.save(registeredVoter);
	}
	
	
	
}
