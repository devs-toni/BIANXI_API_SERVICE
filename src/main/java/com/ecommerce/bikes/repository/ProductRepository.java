package com.ecommerce.bikes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.bikes.entity.ProductDAO;

@Repository
public interface ProductRepository extends JpaRepository<ProductDAO, Long> {

	Optional<ProductDAO> findById(Long id);

	List<ProductDAO> findAllByType(String c);

	List<ProductDAO> findByNameContainingIgnoreCase(String name);
	
	List<ProductDAO> findAll();

}
