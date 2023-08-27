package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.domain.Like;
import com.ecommerce.bikes.http.OrderResponse;
import com.ecommerce.bikes.http.ProductResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assert orders != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(ordersResponses.get(0)), orders);
    }

    @Test
    public void should_return_all_order_by_id_products() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<ProductResponse>> response = this.rest.exchange(
                createUrl() + "api/orders/products/1", HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<ProductResponse> products = response.getBody();

        assert products != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ordersResponses.get(0).getProducts(), products);
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
