package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.entity.OrderDAO;
import com.ecommerce.bikes.exception.OrderNotFoundException;
import com.ecommerce.bikes.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class FindOrderByIdUseCase {

    private final OrderRepository orderRepository;

    public FindOrderByIdUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order find(Long id) throws OrderNotFoundException {
        OrderDAO orderDAO = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("The order does not exist"));
        return OrderDAO.toDomain(orderDAO);
    }
}
