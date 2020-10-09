package com.maycon.coursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maycon.coursomc.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
