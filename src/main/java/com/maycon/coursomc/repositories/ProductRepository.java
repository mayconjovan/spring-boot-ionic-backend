package com.maycon.coursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maycon.coursomc.domain.Category;
import com.maycon.coursomc.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Transactional(readOnly=true)
	Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);
}
