package com.ecommerce.bikes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bikes.entity.SizeEntity;

public interface SizeRepository extends JpaRepository<SizeEntity, Long> {
	
	List<SizeEntity> findAll();
}
