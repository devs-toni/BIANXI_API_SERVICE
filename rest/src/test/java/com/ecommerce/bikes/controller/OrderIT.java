package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entities.OrderEntity;
import com.ecommerce.bikes.http.OrderResponse;
import com.ecommerce.bikes.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderIT extends DockerConfiguration {

    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private OrderRepository orderRepository;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @BeforeAll
    public void prepareTests() {
        orderRepository.save(OrderEntity.toEntity(orderTest));
    }

    @Test
    @Disabled
    public void should_return_all_user_orders() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<OrderResponse>> response = rest.exchange(
                createUrl() + "api/orders/1", HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<OrderResponse> orders = response.getBody();

        assert orders != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(OrderResponse.toOrderResponse(orderTest)), orders);
    }

    public static User user = new User("name@example.com", 'U', "$2a$12$tQQVAKT8ELlfD7hKYa8zIOfa7CVvxkFwJT27/cpumVjRFAnHGiRy6", emptyList(), emptyList());
    public static List<Product> products = List.of(
            new Product(
                    1L,
                    "dummy",
                    "road",
                    32.0f,
                    0,
                    "sentence",
                    "description",
                    Collections.emptySet(),
                    emptyList(),
                    emptyList()
            )
    );
    public static Order orderTest = new Order(
            "direction",
            5f,
            user,
            products
    );


}
