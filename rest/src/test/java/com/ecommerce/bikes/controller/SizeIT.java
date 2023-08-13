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

import static com.ecommerce.bikes.controller.SizeControllerTest.sizeEntity;
import static com.ecommerce.bikes.controller.SizeControllerTest.sizesResponses;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SizeIT extends DockerConfiguration {

    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private SizeRepository sizeRepository;

    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @BeforeAll
    public void prepareTests() {
        sizeRepository.save(sizeEntity);
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

}
