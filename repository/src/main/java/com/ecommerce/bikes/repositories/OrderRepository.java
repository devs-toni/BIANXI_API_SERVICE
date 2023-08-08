package com.ecommerce.bikes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bikes.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
		
	List<OrderEntity> findAllByUserId(long userId);
	
	OrderEntity save(OrderEntity orderEntity);
	
	Optional<OrderEntity> findById(Long id);
	
}
