package com.ecommerce.bikes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bikes.entity.ColorEntity;

public interface ColorRepository extends JpaRepository<ColorEntity, Long>{

	List<ColorEntity> findAll();
}
