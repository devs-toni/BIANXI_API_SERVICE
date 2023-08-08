package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.entity.OrderEntity;
import com.ecommerce.bikes.ports.OrderRepositoryPort;
import com.ecommerce.bikes.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllOrdersByUserUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public FindAllOrdersByUserUseCase(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    public List<Order> find(Long userId) {
        List<OrderEntity> ordersDAO = orderRepositoryPort.findAllByUserId(userId);
        return ordersDAO.stream().map(OrderEntity::toDomain).toList();
    }
}
