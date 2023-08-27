package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.http.OrderResponse;
import com.ecommerce.bikes.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

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
        assertEquals(ordersResponses, orders);
    }

    public static List<OrderResponse> ordersResponses = List.of(
            new OrderResponse(
                    1L,
                    "C/Muro n3",
                    563.25f,
                    emptyList()
            ),
            new OrderResponse(
                    2L,
                    "",
                    15103.1f,
                    emptyList()
            ),
            new OrderResponse(
                    3L,
                    "",
                    1578f,
                    emptyList()
            )
    );

}
