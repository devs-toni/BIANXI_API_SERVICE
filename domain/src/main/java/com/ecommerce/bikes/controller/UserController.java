package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.UserAlreadyExistException;
import com.ecommerce.bikes.exception.UserIsNotValidException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.http.UserRegisterRequest;
import com.ecommerce.bikes.http.UserRegisterResponse;
import com.ecommerce.bikes.useCases.RegisterUserUseCase;
import com.ecommerce.bikes.useCases.VerifyUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final VerifyUserUseCase verifyUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase, VerifyUserUseCase verifyUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.verifyUserUseCase = verifyUserUseCase;
    }

    @PostMapping(value = "/verify", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserRegisterResponse> verify(@RequestBody UserRegisterRequest userRegisterRequest) {

        try {
            User user = verifyUserUseCase.verify(userRegisterRequest.getEmail(), userRegisterRequest.getPassword());
            UserRegisterResponse response = User.toUserRegisterResponse(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserIsNotValidException | UserNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<UserRegisterResponse> save(@RequestBody UserRegisterRequest userRegisterRequest) {

        try {
            UserRegisterResponse userSaved = User.toUserRegisterResponse(
                    registerUserUseCase.save(UserRegisterRequest.toDomain(userRegisterRequest))
            );
            return new ResponseEntity<>(userSaved, HttpStatus.OK);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
