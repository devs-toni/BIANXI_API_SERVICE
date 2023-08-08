package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.OrderEntity;
import com.ecommerce.bikes.entity.ProductEntity;
import com.ecommerce.bikes.entity.UserEntity;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.repository.OrderRepository;
import com.ecommerce.bikes.repository.ProductRepository;
import com.ecommerce.bikes.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CreateOrderUseCase(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Long create(List<Long> productsIds, Long userId, String address, Float amount) throws UserNotFoundException {

        List<Product> productsInOrder = new ArrayList<>();
        productsIds.forEach(productId -> {
            Product product = ProductEntity.toDomain(Objects.requireNonNull(productRepository.findById(productId).orElse(null)));
            productsInOrder.add(product);
        });

        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("The user does not exist"));

        OrderEntity orderToSave = new OrderEntity(
                address,
                amount,
                user,
                productsInOrder.stream().map(Product::toEntity).toList()
        );

        OrderEntity orderEntity = orderRepository.save(orderToSave);
        return orderEntity.getId();
    }
}
