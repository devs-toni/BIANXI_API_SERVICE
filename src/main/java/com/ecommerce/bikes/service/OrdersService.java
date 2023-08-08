package com.ecommerce.bikes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.bikes.entity.OrderDAO;
import com.ecommerce.bikes.entity.ProductDAO;
import com.ecommerce.bikes.entity.UserDAO;
import com.ecommerce.bikes.repository.OrderRepository;

@Service
public class OrdersService {

	@Autowired
	OrderRepository orderRepository;


}
