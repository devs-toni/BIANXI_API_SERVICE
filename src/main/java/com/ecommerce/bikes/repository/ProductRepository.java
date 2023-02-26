package com.ecommerce.bikes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.bikes.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findById(Long id);
	Optional<List<Product>> findAllByType(String c);
	}
