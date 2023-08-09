package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.entities.OrderEntity;
import com.ecommerce.bikes.exception.OrderNotFoundException;
import com.ecommerce.bikes.ports.OrderRepositoryPort;
import com.ecommerce.bikes.repositories.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderAdapter implements OrderRepositoryPort {

    private final OrderRepository orderRepository;

    public OrderAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        List<OrderEntity> ordersEntity = orderRepository.findAllByUserId(userId);
        return ordersEntity.stream().map(OrderEntity::toDomain).toList();
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntityToSave = OrderEntity.toEntity(order);
        OrderEntity savedOrder = orderRepository.save(orderEntityToSave);
        return OrderEntity.toDomain(savedOrder);
    }

    @Override
    public Order findById(Long id) throws OrderNotFoundException {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("The order does not exist"));
        return OrderEntity.toDomain(orderEntity);
    }
}
