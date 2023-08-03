package com.ecommerce.bikes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bikes.entity.OrderDAO;

public interface OrderRepository extends JpaRepository<OrderDAO, Long>{
		
	List<OrderDAO> findAllByUserId(long userId);
	
	OrderDAO save(OrderDAO orderDAO);
	
	Optional<OrderDAO> findById(Long id);
	
}
