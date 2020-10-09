package com.maycon.coursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maycon.coursomc.domain.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Integer> {

}
