package com.maycon.coursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maycon.coursomc.domain.City;
import com.maycon.coursomc.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repo;
	
	public List<City> findByState(Integer stateId){
		return repo.findCities(stateId);
	}
}
