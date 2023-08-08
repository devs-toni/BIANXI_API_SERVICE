package com.ecommerce.bikes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.bikes.repository.OrderRepository;

@Service
public class OrdersService {

	@Autowired
	OrderRepository orderRepository;


}
