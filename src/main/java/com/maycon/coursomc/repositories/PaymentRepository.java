package com.maycon.coursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maycon.coursomc.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
