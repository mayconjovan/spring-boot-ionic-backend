package com.maycon.coursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maycon.coursomc.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
