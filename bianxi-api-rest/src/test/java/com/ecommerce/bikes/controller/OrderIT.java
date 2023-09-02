package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.domain.Like;
import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.ErrorResponse;
import com.ecommerce.bikes.http.OrderRequest;
import com.ecommerce.bikes.http.OrderResponse;
import com.ecommerce.bikes.http.ProductResponse;
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
import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class OrderIT extends DockerConfiguration {

    @Autowired
    private TestRestTemplate rest;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void should_return_all_user_orders() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<OrderResponse>> response = this.rest.exchange(
                createUrl() + "api/orders/1", HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<OrderResponse> orders = response.getBody();

        assertNotNull(orders);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(ordersResponses.get(0)), orders);
    }

    @Test
    public void should_return_all_order_products() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<ProductResponse>> response = this.rest.exchange(
                createUrl() + "api/orders/products/1", HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<ProductResponse> products = response.getBody();

        assertNotNull(products);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersResponses.get(0).getProducts(), products);
    }

    @Test
    public void should_throw_OrderNotFoundException_when_get_all_order_products() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ErrorResponse expectedResponse = new ErrorResponse(404, "The order does not exist");

        ResponseEntity<ErrorResponse> response = this.rest.exchange(
                createUrl() + "api/orders/products/567", HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        ErrorResponse errorResponse = response.getBody();

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedResponse, errorResponse);
    }

    @Test
    @Disabled
    public void should_create_new_order() {
        List<Long> productIds = List.of(1L, 2L, 3L);
        long userId = 1L;
        String address = "address";
        float amount = 24.5f;
        OrderRequest orderRequest = new OrderRequest(productIds, userId, address, amount);

        HttpEntity<OrderRequest> request = new HttpEntity<>(orderRequest, headers);

        ResponseEntity<Long> response = this.rest.exchange(
                createUrl() + "api/orders", HttpMethod.POST, request, new ParameterizedTypeReference<>() {
                });

        Long orderId = response.getBody();

        assertNotNull(orderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(orderId).isGreaterThan(3);
    }

    @Test
    public void should_throw_ProductNotFoundException_when_create_new_order() {
        List<Long> productIds = List.of(1L, 2L, 365L);
        long userId = 1L;
        String address = "address";
        float amount = 24.5f;
        OrderRequest orderRequest = new OrderRequest(productIds, userId, address, amount);

        HttpEntity<OrderRequest> request = new HttpEntity<>(orderRequest, headers);
        ErrorResponse expectedResponse = new ErrorResponse(404, "The product does not exist");

        ResponseEntity<ErrorResponse> response = this.rest.exchange(
                createUrl() + "api/orders", HttpMethod.POST, request, new ParameterizedTypeReference<>() {
                });

        ErrorResponse errorResponse = response.getBody();

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedResponse, errorResponse);
    }

    @Test
    public void should_throw_UserNotFoundException_when_create_new_order() {
        List<Long> productIds = List.of(1L, 2L, 3L);
        long userId = 37L;
        String address = "address";
        float amount = 24.5f;
        OrderRequest orderRequest = new OrderRequest(productIds, userId, address, amount);

        HttpEntity<OrderRequest> request = new HttpEntity<>(orderRequest, headers);
        ErrorResponse expectedResponse = new ErrorResponse(404, "The user does not exist");

        ResponseEntity<ErrorResponse> response = this.rest.exchange(
                createUrl() + "api/orders", HttpMethod.POST, request, new ParameterizedTypeReference<>() {
                });

        ErrorResponse errorResponse = response.getBody();

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedResponse, errorResponse);
    }

    public static User user = new User(1L, "name@example.com", 'U', "$2a$12$tQQVAKT8ELlfD7hKYa8zIOfa7CVvxkFwJT27/cpumVjRFAnHGiRy6", Collections.emptyList(), List.of(new Like(1L, 1L, 1L)));

    public static List<OrderResponse> ordersResponses = List.of(
            new OrderResponse(
                    1L,
                    "C/Muro n3",
                    563.25f,
                    List.of(
                            new ProductResponse(
                                    1L,
                                    "Methanol CV FS 9.3 XT",
                                    "road",
                                    4707,
                                    0,
                                    "ULTIMATE CROSS-COUNTRY RACE BIKE",
                                    "Bianchi Methanol FS es la joya de doble suspensión de Bianchi. Una btt que te permitirá subir como un cohete y bajar como un rayo, gracias a su geometría renovada y su carbono CV que absorve el 80% de las vibraciones.",
                                    emptySet(),
                                    emptyList(),
                                    List.of(new Like(1L, 1L, 1L))
                            )
                    )
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
                    Collections.emptyList(),
                    Collections.emptyList()
            )
    );

    public static Order orderTest = new Order(
            "direction",
            5f,
            user.getId(),
            products
    );
    public static Order createdOrder = new Order(
            2L,
            "direction",
            5f,
            user.getId(),
            products
    );
}
