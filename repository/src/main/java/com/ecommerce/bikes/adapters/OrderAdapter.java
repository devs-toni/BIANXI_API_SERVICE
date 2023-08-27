package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entities.OrderEntity;
import com.ecommerce.bikes.entities.UserEntity;
import com.ecommerce.bikes.exception.OrderNotFoundException;
import com.ecommerce.bikes.ports.OrderRepositoryPort;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import com.ecommerce.bikes.repositories.OrderRepository;
import com.ecommerce.bikes.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderAdapter implements OrderRepositoryPort {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderAdapter(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        List<OrderEntity> ordersEntity = orderRepository.findAllByUserId(userId);
        return ordersEntity.stream().map(OrderEntity::toDomain).toList();
    }

    @Override
    public Order save(Order order) {
        User user = UserEntity.toDomain(userRepository.findById(order.getUser()).get());
        OrderEntity orderEntityToSave = OrderEntity.toEntity(order, user);
        OrderEntity savedOrder = orderRepository.save(orderEntityToSave);
        return OrderEntity.toDomain(savedOrder);
    }

    @Override
    public Order findById(Long id) throws OrderNotFoundException {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("The order does not exist"));
        return OrderEntity.toDomain(orderEntity);
    }
}
