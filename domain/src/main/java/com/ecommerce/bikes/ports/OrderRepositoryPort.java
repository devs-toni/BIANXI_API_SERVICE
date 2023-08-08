package com.ecommerce.bikes.ports;

import com.ecommerce.bikes.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {

    List<Order> findAllByUserId(long userId);

    Order save(Order orderEntity);

    Optional<Order> findById(Long id);
}
