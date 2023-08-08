package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.OrderEntity;
import com.ecommerce.bikes.entity.ProductEntity;
import com.ecommerce.bikes.entity.UserEntity;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.OrderRepositoryPort;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import com.ecommerce.bikes.repository.OrderRepository;
import com.ecommerce.bikes.repository.ProductRepository;
import com.ecommerce.bikes.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            Product product = ProductEntity.toDomain(Objects.requireNonNull(productRepositoryPort.findById(productId).orElse(null)));
            productsInOrder.add(product);
        });

        UserEntity user = userRepositoryPort.findById(userId).orElseThrow(() -> new UserNotFoundException("The user does not exist"));

        OrderEntity orderToSave = new OrderEntity(
                address,
                amount,
                user,
                productsInOrder.stream().map(Product::toEntity).toList()
        );

        OrderEntity orderEntity = orderRepositoryPort.save(orderToSave);
        return orderEntity.getId();
    }
}
