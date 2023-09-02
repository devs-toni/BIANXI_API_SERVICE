package com.ecommerce.bikes.integration;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.domain.Like;
import com.ecommerce.bikes.exception.ErrorResponse;
import com.ecommerce.bikes.http.OrderRequest;
import com.ecommerce.bikes.http.OrderResponse;
import com.ecommerce.bikes.http.ProductResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @DisplayName("GIVEN a specific user id WHEN user tries to get user orders THEN these are returned")
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
    @DisplayName("GIVEN a specific id WHEN user tries to get all products THEN these are returned")
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
    @DisplayName("GIVEN a specific id WHEN user tries to get all products THEN throw exception because order doesn't exist")
    public void should_throw_OrderNotFoundException_when_get_all_order_products() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);
        ErrorResponse expectedResponse = new ErrorResponse(404, "The order does not exist");

        ResponseEntity<ErrorResponse> response = this.rest.exchange(
                createUrl() + "api/orders/products/567", HttpMethod.GET, request, ErrorResponse.class);

        ErrorResponse errorResponse = response.getBody();

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedResponse, errorResponse);
    }

    @Test
    @DisplayName("GIVEN a new order WHEN user saves THEN new order is created")
    public void should_create_new_order() {
        List<Long> productIds = List.of(1L, 2L, 3L);
        long userId = 1L;
        String address = "address";
        float amount = 24.5f;
        OrderRequest orderRequest = new OrderRequest(productIds, userId, address, amount);

        HttpEntity<OrderRequest> request = new HttpEntity<>(orderRequest, headers);

        ResponseEntity<Long> response = this.rest.exchange(
                createUrl() + "api/orders", HttpMethod.POST, request, Long.class);

        Long orderId = response.getBody();

        assertNotNull(orderId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(orderId).isGreaterThan(3);
    }

    @Test
    @DisplayName("GIVEN a new order WHEN user saves THEN throw exception because at least one product in the order doesn't exist")
    public void should_throw_ProductNotFoundException_when_create_new_order() {
        List<Long> productIds = List.of(1L, 2L, 365L);
        long userId = 1L;
        String address = "address";
        float amount = 24.5f;
        OrderRequest orderRequest = new OrderRequest(productIds, userId, address, amount);

        HttpEntity<OrderRequest> request = new HttpEntity<>(orderRequest, headers);
        ErrorResponse expectedResponse = new ErrorResponse(404, "The product does not exist");

        ResponseEntity<ErrorResponse> response = this.rest.exchange(createUrl() + "api/orders", HttpMethod.POST, request, ErrorResponse.class);

        ErrorResponse errorResponse = response.getBody();

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedResponse, errorResponse);
    }

    @Test
    @DisplayName("GIVEN a new order WHEN user saves THEN throw exception because user doesn't exist")
    public void should_throw_UserNotFoundException_when_create_new_order() {
        List<Long> productIds = List.of(1L, 2L, 3L);
        long userId = 37L;
        String address = "address";
        float amount = 24.5f;
        OrderRequest orderRequest = new OrderRequest(productIds, userId, address, amount);

        HttpEntity<OrderRequest> request = new HttpEntity<>(orderRequest, headers);
        ErrorResponse expectedResponse = new ErrorResponse(404, "The user does not exist");

        ResponseEntity<ErrorResponse> response = this.rest.exchange(
                createUrl() + "api/orders", HttpMethod.POST, request, ErrorResponse.class);

        ErrorResponse errorResponse = response.getBody();

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedResponse, errorResponse);
    }

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
}
