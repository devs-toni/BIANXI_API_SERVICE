package com.ecommerce.bikes.ports;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.exception.OrderNotFoundException;

import java.util.List;

public interface OrderRepositoryPort {

    List<Order> findAllByUserId(Long userId);

    Order save(Order order);

    Order findById(Long id) throws OrderNotFoundException;
}
