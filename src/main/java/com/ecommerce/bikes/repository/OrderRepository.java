package com.ecommerce.bikes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bikes.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
		
	List<Order> findAllByUserId(long userId);
	
	Order save(Order order);
	
	Optional<Order> findById(Long id);
	
}
