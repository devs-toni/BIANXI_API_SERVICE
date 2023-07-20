package com.ecommerce.bikes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bikes.entity.ColorDAO;

public interface ColorRepository extends JpaRepository<ColorDAO, Long>{

	List<ColorDAO> findAll();
}
