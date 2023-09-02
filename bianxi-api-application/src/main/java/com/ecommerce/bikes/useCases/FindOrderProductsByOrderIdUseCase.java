package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.exception.OrderNotFoundException;
import com.ecommerce.bikes.ports.OrderRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class FindOrderProductsByOrderIdUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public FindOrderProductsByOrderIdUseCase(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    public Order find(Long id) throws OrderNotFoundException {
        return orderRepositoryPort.findById(id);
    }
}
