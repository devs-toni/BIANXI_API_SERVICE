package com.ecommerce.bikes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.bikes.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	Optional<ProductEntity> findById(Long id);

	List<ProductEntity> findAllByType(String c);

	List<ProductEntity> findByNameContainingIgnoreCase(String name);
	
	List<ProductEntity> findAll();

}
