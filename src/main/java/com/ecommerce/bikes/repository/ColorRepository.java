package com.ecommerce.bikes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bikes.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Long>{

	List<Color> findAll();
}
