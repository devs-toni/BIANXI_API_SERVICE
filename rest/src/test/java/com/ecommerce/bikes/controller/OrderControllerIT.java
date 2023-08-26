package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.repositories.ColorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class OrderControllerIT extends DockerConfiguration {

    @Autowired
    private TestRestTemplate rest;
    @Autowired
    private ColorRepository orderRepository;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }



}
