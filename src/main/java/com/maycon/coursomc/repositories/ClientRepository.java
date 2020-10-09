package com.maycon.coursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maycon.coursomc.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
