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

	public List<OrderDAO> findAllByUser(long userId) throws NoSuchElementException {
		return orderRepository.findAllByUserId(userId);
	}

	public Long createOrder(List<ProductDAO> productDAOS, UserDAO userDAO, String address, float amount) {
		productDAOS.forEach(p -> System.out.println(p.getId()));
		OrderDAO orderDAO = orderRepository.save(new OrderDAO(address, amount, userDAO, productDAOS));
		return orderDAO.getId();
	}

	public OrderDAO findById(Long id) throws NoSuchElementException {
		return orderRepository.findById(id).get();
	}
}
