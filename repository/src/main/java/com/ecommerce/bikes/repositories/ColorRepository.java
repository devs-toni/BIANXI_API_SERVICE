package com.ecommerce.bikes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bikes.entities.ColorEntity;

public interface ColorRepository extends JpaRepository<ColorEntity, Long>{

	List<ColorEntity> findAll();
}
