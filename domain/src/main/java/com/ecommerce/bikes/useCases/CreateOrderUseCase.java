package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.OrderRepositoryPort;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateOrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;

    public CreateOrderUseCase(OrderRepositoryPort orderRepositoryPort, UserRepositoryPort userRepositoryPort, ProductRepositoryPort productRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
    }

    public Long create(List<Long> productsIds, Long userId, String address, Float amount) throws UserNotFoundException {

        List<Product> productsInOrder = new ArrayList<>();
        productsIds.forEach(productId -> {
            Product product = null;
            try {
                product = productRepositoryPort.findById(productId);
            } catch (ProductNotFoundException e) {
                throw new RuntimeException(e);
            }
            productsInOrder.add(product);
        });

        User user = userRepositoryPort.findById(userId);

        Order orderToSave = new Order(
                address,
                amount,
                user.getId(),
                productsInOrder
        );

        Order order = orderRepositoryPort.save(orderToSave);
        return order.getId();
    }
}
