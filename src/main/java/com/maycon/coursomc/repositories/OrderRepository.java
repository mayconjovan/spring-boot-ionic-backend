package com.maycon.coursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maycon.coursomc.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
