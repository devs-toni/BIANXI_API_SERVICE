package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.http.SizeResponse;
import com.ecommerce.bikes.repositories.SizeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SizeIT extends DockerConfiguration {

    @Autowired
    private TestRestTemplate rest;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void should_return_all_sizes_when_find_all() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<SizeResponse>> response = rest.exchange(createUrl() + "api/sizes", HttpMethod.GET, request, new ParameterizedTypeReference<>() {
        });

        List<SizeResponse> sizes = response.getBody();

        assert sizes != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sizesResponses, sizes);
    }

    private static List<SizeResponse> sizesResponses = List.of(
            new SizeResponse(1L, "47"),
            new SizeResponse(2L, "48"),
            new SizeResponse(3L, "49")
    );

}
