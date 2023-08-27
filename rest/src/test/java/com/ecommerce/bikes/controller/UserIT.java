package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.http.UserRegisterRequest;
import com.ecommerce.bikes.http.UserRegisterResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserIT extends DockerConfiguration {

    @Autowired
    private TestRestTemplate rest;
    private static HttpHeaders headers;

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Order(1)
    public void should_register_and_return_created_user() {
        UserRegisterRequest userToCreate = new UserRegisterRequest("devs@devs.es", "$2a$12$sNsZMHtAarUIvVkQEEXsi.3bQ0sJlhV09X3SlOJ1Egx1JGrCOdK0e");
        UserRegisterResponse userCreated = new UserRegisterResponse(15L, "devs@devs.es", 'U');

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToCreate, headers);

        ResponseEntity<UserRegisterResponse> response = this.rest.exchange(createUrl() + "api/users", HttpMethod.POST, request, new ParameterizedTypeReference<>() {
        });

        UserRegisterResponse user = response.getBody();

        assertNotNull(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userCreated, user);
    }

    @Test
    @Order(2)
    public void should_return_null_if_user_already_exists() {
        UserRegisterRequest userToCreate = new UserRegisterRequest("admin", "admin");

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToCreate, headers);

        ResponseEntity<UserRegisterResponse> response = this.rest.exchange(createUrl() + "api/users", HttpMethod.POST, request, new ParameterizedTypeReference<>() {
        });

        UserRegisterResponse user = response.getBody();

        assertNull(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(3)
    public void should_verify_and_return_verified_user() {
        UserRegisterRequest userToVerify = new UserRegisterRequest("admin", "admin");
        UserRegisterResponse verifiedUser = new UserRegisterResponse(2L, "admin", 'A');

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToVerify, headers);

        ResponseEntity<UserRegisterResponse> response = this.rest.exchange(createUrl() + "api/users/verify", HttpMethod.POST, request, new ParameterizedTypeReference<>() {
        });

        UserRegisterResponse user = response.getBody();

        assertNotNull(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(verifiedUser, user);
    }

    @Test
    @Order(4)
    public void should_return_null_if_user_does_not_exist() {
        UserRegisterRequest userToVerify = new UserRegisterRequest("admin", "adm");

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToVerify, headers);

        ResponseEntity<UserRegisterResponse> response = this.rest.exchange(createUrl() + "api/users/verify", HttpMethod.POST, request, new ParameterizedTypeReference<>() {
        });

        UserRegisterResponse user = response.getBody();

        assertNull(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
