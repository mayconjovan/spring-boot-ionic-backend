package com.maycon.coursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maycon.coursomc.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
