package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.exception.OrderNotFoundException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.http.OrderRequest;
import com.ecommerce.bikes.http.OrderResponse;
import com.ecommerce.bikes.http.ProductResponse;
import com.ecommerce.bikes.useCases.CreateOrderUseCase;
import com.ecommerce.bikes.useCases.FindAllOrdersByUserUseCase;
import com.ecommerce.bikes.useCases.FindOrderProductsByOrderIdUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final FindOrderProductsByOrderIdUseCase findOrderProductsByOrderId;
    private final FindAllOrdersByUserUseCase findAllOrdersByUserUseCase;
    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(FindOrderProductsByOrderIdUseCase findOrderProductsByOrderId, FindAllOrdersByUserUseCase findAllOrdersByUserUseCase, CreateOrderUseCase createOrderUseCase) {
        this.findOrderProductsByOrderId = findOrderProductsByOrderId;
        this.findAllOrdersByUserUseCase = findAllOrdersByUserUseCase;
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Long> create(
            @RequestBody OrderRequest orderRequest
    ) throws UserNotFoundException {
        Long orderId = createOrderUseCase.create(
                orderRequest.getProductsIds(),
                orderRequest.getUserId(),
                orderRequest.getOrderAddress(),
                orderRequest.getOrderAmount()
        );
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderResponse>> findAllUserOrders(@PathVariable Long userId) {

        List<Order> orders = findAllOrdersByUserUseCase.find(userId);
        return new ResponseEntity<>(orders.stream().map(OrderResponse::toOrderResponse).toList(), HttpStatus.OK);
    }

    @GetMapping("/products/{orderId}")
    public ResponseEntity<List<ProductResponse>> findAllOrderProducts(@PathVariable Long orderId) {

        try {
            Order order = findOrderProductsByOrderId.find(orderId);
            return new ResponseEntity<>(order.getProducts().stream().map(ProductResponse::toProductResponse).toList(), HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
