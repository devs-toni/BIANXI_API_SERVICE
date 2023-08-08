package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.OrderDAO;
import com.ecommerce.bikes.entity.ProductDAO;
import com.ecommerce.bikes.entity.UserDAO;
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
            Product product = ProductDAO.toDomain(Objects.requireNonNull(productRepository.findById(productId).orElse(null)));
            productsInOrder.add(product);
        });

        UserDAO user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("The user does not exist"));

        OrderDAO orderToSave = new OrderDAO(
                address,
                amount,
                user,
                productsInOrder.stream().map(Product::toEntity).toList()
        );

        OrderDAO orderDAO = orderRepository.save(orderToSave);
        return orderDAO.getId();
    }
}
