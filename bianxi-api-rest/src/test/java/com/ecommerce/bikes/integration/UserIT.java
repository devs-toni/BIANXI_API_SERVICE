package com.ecommerce.bikes.integration;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.ErrorResponse;
import com.ecommerce.bikes.http.UserRegisterRequest;
import com.ecommerce.bikes.http.UserRegisterResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @DisplayName("GIVEN a new user WHEN user saves THEN new user is saved")
    public void should_return_saved_user() {
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
    @DisplayName("GIVEN a new user WHEN user saves THEN throw exception because the user already exist in system")
    public void should_return_saved_user_when_user_already_exist() {
        UserRegisterRequest userToCreate = new UserRegisterRequest("admin", "admin");
        UserRegisterResponse expectedUser = new UserRegisterResponse(2L, "admin", 'A');

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToCreate, headers);

        ResponseEntity<UserRegisterResponse> response = this.rest.postForEntity(createUrl() + "api/users", request, UserRegisterResponse.class);

        UserRegisterResponse user = response.getBody();

        assertNotNull(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, user);
    }

    @Test
    @DisplayName("GIVEN user data WHEN user login THEN user is verified successfully")
    public void should_return_verified_user() {
        UserRegisterRequest userToVerify = new UserRegisterRequest("admin", "admin");
        UserRegisterResponse verifiedUser = new UserRegisterResponse(2L,"admin", 'A');

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToVerify, headers);

        ResponseEntity<UserRegisterResponse> response = this.rest.postForEntity(createUrl() + "api/users/verify", request, UserRegisterResponse.class);

        UserRegisterResponse user = response.getBody();

        assertNotNull(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(verifiedUser, user);
    }

    @Test
    @DisplayName("GIVEN user data WHEN user login THEN throw exception because user doesn't exist")
    public void should_throw_exception_when_user_does_not_exist() {
        UserRegisterRequest userToVerify = new UserRegisterRequest("sdaf", "adm");
        ErrorResponse expectedResponse = new ErrorResponse(200, "The user does not exist", "CREDENTIALS_ARE_NOT_VALID");

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToVerify, headers);

        ResponseEntity<ErrorResponse> response = this.rest.postForEntity(createUrl() + "api/users/verify", request, ErrorResponse.class);

        ErrorResponse errorResponse = response.getBody();

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, errorResponse);
    }

    @Test
    @DisplayName("GIVEN user data WHEN user login THEN throw exception because password is not correct")
    public void should_throw_exception_for_bad_credentiales() {
        UserRegisterRequest userToVerify = new UserRegisterRequest("admin", "adm");
        ErrorResponse expectedResponse = new ErrorResponse(200, "The credentials are not valid", "CREDENTIALS_ARE_NOT_VALID");

        HttpEntity<UserRegisterRequest> request = new HttpEntity<>(userToVerify, headers);

        ResponseEntity<ErrorResponse> response = this.rest.postForEntity(createUrl() + "api/users/verify", request, ErrorResponse.class);

        ErrorResponse errorResponse = response.getBody();

        assertNotNull(errorResponse);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, errorResponse);
    }
}
