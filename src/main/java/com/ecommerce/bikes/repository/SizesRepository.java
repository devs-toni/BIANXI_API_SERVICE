package com.ecommerce.bikes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bikes.entity.Size;

public interface SizesRepository extends JpaRepository<Size, Long> {
	
	List<Size> findAll();
}
