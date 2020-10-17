package com.maycon.coursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maycon.coursomc.domain.State;
import com.maycon.coursomc.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository repo;

	public List<State> findAll() {
		return repo.findAllByOrderByName();
	}
}
