package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.http.ColorResponse;
import com.ecommerce.bikes.repositories.ColorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static com.ecommerce.bikes.controller.ColorControllerTest.colorEntity;
import static com.ecommerce.bikes.controller.ColorControllerTest.colorsResponses;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ColorIT extends DockerConfiguration {

    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private ColorRepository colorRepository;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @BeforeAll
    public void prepareTests() {
        colorRepository.save(colorEntity);
    }

    @Test
    public void should_return_all_colors() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<ColorResponse>> response = rest.exchange(
                createUrl() + "api/colors", HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<ColorResponse> colors = response.getBody();

        assert colors != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(colorsResponses, colors);
    }
}
