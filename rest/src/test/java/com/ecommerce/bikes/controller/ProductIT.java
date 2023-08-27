package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entities.LikeEntity;
import com.ecommerce.bikes.http.ProductResponse;
import com.ecommerce.bikes.repositories.LikeRepository;
import com.ecommerce.bikes.repositories.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static com.ecommerce.bikes.controller.ProductControllerTest.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductIT extends DockerConfiguration {

    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LikeRepository likeRepository;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(1)
    public void should_return_product_by_id() {
        HttpEntity<Product> request = new HttpEntity<>(null, headers);

        ResponseEntity<ProductResponse> result = this.rest.getForEntity(createUrl() + "api/products/1", ProductResponse.class, request);

        assert result.getBody() != null;
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(null, result.getBody());
    }

    @Test
    @Order(2)
    public void should_return_all_products() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<ProductResponse>> response = rest.exchange(
                createUrl() + "api/products", HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<ProductResponse> products = response.getBody();

        assert products != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, products);
    }

    @Test
    @Order(3)
    public void should_return_all_products_by_type() {
        String expectedType = "road";

        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<ProductResponse>> response = rest.exchange(
                createUrl() + "api/products/type/" + expectedType, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<ProductResponse> products = response.getBody();

        assert products != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, products);
    }

    @Test
    @Order(4)
    public void should_return_all_favourites_products() {
        long expectedUserId = 1L;

        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<ProductResponse>> response = rest.exchange(
                createUrl() + "api/products/favourites/" + expectedUserId, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<ProductResponse> products = response.getBody();

        assert products != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null,products);
    }

    @Test
    @Order(5)
    public void should_return_all_products_by_name() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        String expectedSearch = "Meth";

        ResponseEntity<List<ProductResponse>> response = rest.exchange(
                createUrl() + "api/products/search/" + expectedSearch, HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<ProductResponse> products = response.getBody();

        assert products != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, products);
    }

    @Test
    @Order(6)
    public void should_add_like() {
        productRepository.save(productEntity);
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<ProductResponse>> response = rest.exchange(
                createUrl() + "api/products/likes/2/1", HttpMethod.POST, request, new ParameterizedTypeReference<>() {
                });

        assertNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(7)
    public void should_get_like() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<Object> response = rest.exchange(
                createUrl() + "api/products/likes/2/1", HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        Object result = response.getBody();

        assertNotNull(result);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(9)
    public void return_null_when_get_not_existent_like() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<Object> response = rest.exchange(
                createUrl() + "api/products/likes/2/1", HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        Object result = response.getBody();

        assertNull(result);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(8)
    public void should_delete_like() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<ProductResponse>> response = rest.exchange(
                createUrl() + "api/products/likes/2/1", HttpMethod.DELETE, request, new ParameterizedTypeReference<>() {
                });

        assertNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
