package io.votingmachine.model.repository.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.votingmachine.model.State;
import io.votingmachine.model.repository.StateRepository;

@Service
public class StateService {

	
	@Autowired
	StateRepository stateRepository;
	
	@Transactional
	public State findBy(String name) {
		return stateRepository.findByName(name);
	}
	
	public boolean existsBy(String name) {
		return stateRepository.existsByName(name);
	}
	
	public List<State> findAll() {
		List<State> list = new ArrayList<>();
		stateRepository.findAll().forEach(list::add);
		return list;
	}
}
