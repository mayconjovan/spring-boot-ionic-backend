package com.maycon.coursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maycon.coursomc.domain.OrderItem;

@Repository
public interface OrderItensRepository extends JpaRepository<OrderItem, Integer> {

}
