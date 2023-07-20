package com.ecommerce.bikes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bikes.entity.SizeDAO;

public interface SizeRepository extends JpaRepository<SizeDAO, Long> {
	
	List<SizeDAO> findAll();
}
