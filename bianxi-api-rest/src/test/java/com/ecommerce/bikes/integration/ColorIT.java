package com.ecommerce.bikes.integration;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.http.ColorResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ColorIT extends DockerConfiguration {

    @Autowired
    private TestRestTemplate rest;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @DisplayName("WHEN user tries to get all colors THEN these are returned")
    public void should_return_all_colors() {
        HttpEntity<String> request = new HttpEntity<>(null, headers);

        ResponseEntity<List<ColorResponse>> response = this.rest.exchange(
                createUrl() + "api/colors", HttpMethod.GET, request, new ParameterizedTypeReference<>() {
                });

        List<ColorResponse> colors = response.getBody();

        assertNotNull(colors);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(colorsResponses, colors);
    }

    private static List<ColorResponse> colorsResponses = List.of(
            new ColorResponse(1L, "#FFh6h6"),
            new ColorResponse(2L, "#h7h7h7"),
            new ColorResponse(3L, "#g5gg5g")
    );
}
