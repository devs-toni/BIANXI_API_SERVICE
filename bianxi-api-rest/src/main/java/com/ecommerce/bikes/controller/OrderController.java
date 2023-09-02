package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.exception.ErrorResponse;
import com.ecommerce.bikes.exception.OrderNotFoundException;
import com.ecommerce.bikes.exception.ProductNotFoundException;
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
    ) {
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

            Order order = findOrderProductsByOrderId.find(orderId);
            return new ResponseEntity<>(order.getProducts().stream().map(ProductResponse::toProductResponse).toList(), HttpStatus.OK);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFound(
            OrderNotFoundException onfe
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), onfe.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFoundException unfe
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), unfe.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            ProductNotFoundException pnfe
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), pnfe.getMessage()));
    }
}
