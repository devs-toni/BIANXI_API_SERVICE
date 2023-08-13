package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.http.ProductResponse;
import com.ecommerce.bikes.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static com.ecommerce.bikes.controller.ProductControllerTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductIT extends DockerConfiguration {
    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private ProductRepository productRepository;

    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @BeforeAll
    public void prepareTests() {
        productRepository.save(productEntity);
    }

    @Test
    public void should_return_product_by_id() {
        HttpEntity<Product> request = new HttpEntity<>(null, headers);

        ResponseEntity<ProductResponse> result = this.rest.getForEntity(createUrl() + "api/products/1", ProductResponse.class, request);

        assert result.getBody() != null;
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(productResponse, result.getBody());
    }

    @Test
    public void should_return_all_products() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<ProductResponse>> response = rest.exchange(
                createUrl() + "api/products", HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<ProductResponse> products = response.getBody();

        assert products != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsResponses, products);
    }

    @Test
    public void should_return_all_products_by_type() {
        String expectedType = "road";

        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<ProductResponse>> response = rest.exchange(
                createUrl() + "api/products/type/" + expectedType, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<ProductResponse> products = response.getBody();

        assert products != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsResponses, products);
    }

    @Test
    public void should_return_all_favourites_products() {
        Long expectedUserId = 1L;

        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<ProductResponse>> response = rest.exchange(
                createUrl() + "api/products/favourites/" + expectedUserId, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<ProductResponse> products = response.getBody();

        assert products != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsResponses, products);
    }

    @Test
    @Disabled
    public void should_return_all_products_by_name() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String expectedSearch = "Meth";

        ResponseEntity<List<ProductResponse>> response = rest.exchange(
                createUrl() + "api/products/search/" + expectedSearch, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<ProductResponse> products = response.getBody();

        assert products != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsResponses, products);
    }

    @Test
    public void should_add_like() {

    }

    @Test
    public void should_get_like() {

    }

    @Test
    public void should_delete_like() {

    }
}
