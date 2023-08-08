package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.entity.OrderEntity;
import com.ecommerce.bikes.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllOrdersByUserUseCase {

    private final OrderRepository orderRepository;

    public FindAllOrdersByUserUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> find(Long userId) {
        List<OrderEntity> ordersDAO = orderRepository.findAllByUserId(userId);
        return ordersDAO.stream().map(OrderEntity::toDomain).toList();
    }
}
