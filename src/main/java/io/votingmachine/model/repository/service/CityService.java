package io.votingmachine.model.repository.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.votingmachine.model.City;
import io.votingmachine.model.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	StateService stateService;
	
	
	public boolean existsBy(String state, String city) {
		return cityRepository.existsByNameAndState(city, stateService.findBy(state)); 
	}
	
	@Transactional 
	public City findBy(String state, String city) {
		return cityRepository.findByNameAndState(city, stateService.findBy(state));
	}
	
	public List<City> findBy(String state) {
		return new ArrayList<City>(stateService.findBy(state).getCities());
	}
	
	public List<City> findAll() {
		List<City> list = new ArrayList<>();
		cityRepository.findAll().forEach(list::add);
		return list;
	}
}
