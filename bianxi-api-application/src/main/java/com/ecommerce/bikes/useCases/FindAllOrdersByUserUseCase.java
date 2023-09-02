package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.ports.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllOrdersByUserUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public FindAllOrdersByUserUseCase(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    public List<Order> find(Long userId) {
        return orderRepositoryPort.findAllByUserId(userId);
    }
}
