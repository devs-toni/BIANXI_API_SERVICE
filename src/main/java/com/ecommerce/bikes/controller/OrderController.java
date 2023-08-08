package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.exception.OrderNotFoundException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.http.OrderResponse;
import com.ecommerce.bikes.http.ProductResponse;
import com.ecommerce.bikes.useCases.CreateOrderUseCase;
import com.ecommerce.bikes.useCases.FindAllOrdersByUserUseCase;
import com.ecommerce.bikes.useCases.FindOrderByIdUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final FindOrderByIdUseCase findOrderByIdUseCase;
    private final FindAllOrdersByUserUseCase findAllOrdersByUserUseCase;
    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(FindOrderByIdUseCase findOrderByIdUseCase, FindAllOrdersByUserUseCase findAllOrdersByUserUseCase, CreateOrderUseCase createOrderUseCase) {
        this.findOrderByIdUseCase = findOrderByIdUseCase;
        this.findAllOrdersByUserUseCase = findAllOrdersByUserUseCase;
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Long> create(
            @RequestBody List<Long> productsIds,
            @RequestBody Long userId,
            @RequestBody String orderAddress,
            @RequestBody Float orderAmount
    ) throws UserNotFoundException {
        Long orderId = createOrderUseCase.create(productsIds, userId, orderAddress, orderAmount);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderResponse>> findAllByUser(@PathVariable Long userId) {

        List<Order> orders = findAllOrdersByUserUseCase.find(userId);
        return new ResponseEntity<>(orders.stream().map(Order::toResponse).toList(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<List<ProductResponse>> findAll(@PathVariable Long id) {

        try {
            Order order = findOrderByIdUseCase.find(id);
            return new ResponseEntity<>(order.getProducts().stream().map(Product::toResponse).toList(), HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
