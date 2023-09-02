package com.ecommerce.bikes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bikes.entities.SizeEntity;

public interface SizeRepository extends JpaRepository<SizeEntity, Long> {
	
	List<SizeEntity> findAll();
}
