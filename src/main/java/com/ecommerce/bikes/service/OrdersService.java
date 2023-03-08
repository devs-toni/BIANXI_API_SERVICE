package com.ecommerce.bikes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.aspectj.util.PartialOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.bikes.entity.Order;
import com.ecommerce.bikes.entity.Product;
import com.ecommerce.bikes.entity.User;
import com.ecommerce.bikes.repository.OrderRepository;

@Service
public class OrdersService {

	@Autowired
	OrderRepository orderRepository;

	public List<Order> findAllByUser(long userId) throws NoSuchElementException {
		return orderRepository.findAllByUserId(userId);
	}

	public Long createOrder(List<Product> products, User user, String address, float amount) {
		products.forEach(p -> System.out.println(p.getId()));
		Order order = orderRepository.save(new Order(address, amount, user, products));
		return order.getId();
	}

	public Order findById(Long id) throws NoSuchElementException {
		return orderRepository.findById(id).get();
	}
}
