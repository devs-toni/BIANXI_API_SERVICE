package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.exception.ErrorResponse;
import com.ecommerce.bikes.http.UserRegisterRequest;
import com.ecommerce.bikes.http.UserRegisterResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @Disabled
    public void should_throw_UserNotFoundException_when_verify_user() {
        UserRegisterRequest userToVerify = new UserRegisterRequest("admin", "adm");
        ErrorResponse expectedResponse = new ErrorResponse(404, "The user is not valid");

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToVerify, headers);

        ResponseEntity<ErrorResponse> response = this.rest.postForEntity(createUrl() + "api/users/verify", request, ErrorResponse.class);

        ErrorResponse errorResponse = response.getBody();

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedResponse, errorResponse);
    }

    @Test
    @Order(2)
    public void should_register_and_return_created_user() {
        UserRegisterRequest userToCreate = new UserRegisterRequest("devs@devs.es", "$2a$12$sNsZMHtAarUIvVkQEEXsi.3bQ0sJlhV09X3SlOJ1Egx1JGrCOdK0e");
        UserRegisterResponse userCreated = new UserRegisterResponse(15L, "devs@devs.es", 'U');

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToCreate, headers);

        ResponseEntity<UserRegisterResponse> response = this.rest.postForEntity(createUrl() + "api/users", request, UserRegisterResponse.class);

        UserRegisterResponse user = response.getBody();

        assertNotNull(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userCreated, user);
    }

    @Test
    @Order(4)
    public void should_throw_UserAlreadyExistException_when_register_new_user() {
        UserRegisterRequest userToCreate = new UserRegisterRequest("admin", "admin");
        ErrorResponse expectedResponse = new ErrorResponse(400, "This user already exists");

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToCreate, headers);

        ResponseEntity<ErrorResponse> response = this.rest.postForEntity(createUrl() + "api/users", request, ErrorResponse.class);

        ErrorResponse errorResponse = response.getBody();

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedResponse, errorResponse);
    }

    @Test
    @Order(3)
    public void should_verify_and_return_verified_user() {
        UserRegisterRequest userToVerify = new UserRegisterRequest("admin", "admin");
        UserRegisterResponse verifiedUser = new UserRegisterResponse(2L, "admin", 'A');

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToVerify, headers);

        ResponseEntity<UserRegisterResponse> response = this.rest.postForEntity(createUrl() + "api/users/verify", request, UserRegisterResponse.class);

        UserRegisterResponse user = response.getBody();

        assertNotNull(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(verifiedUser, user);
    }

    @Test
    @Order(5)
    @Disabled
    public void should_throw_UserIsNotValidException_when_verify_user() {
        UserRegisterRequest userToVerify = new UserRegisterRequest("admin", "adm");
        ErrorResponse expectedResponse = new ErrorResponse(401, "The user is not valid");

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToVerify, headers);

        ResponseEntity<ErrorResponse> response = this.rest.postForEntity(createUrl() + "api/users/verify", request, ErrorResponse.class);

        ErrorResponse errorResponse = response.getBody();

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(expectedResponse, errorResponse);
    }
}
