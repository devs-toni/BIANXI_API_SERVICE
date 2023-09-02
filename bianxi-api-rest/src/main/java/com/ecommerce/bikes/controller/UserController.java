package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.*;
import com.ecommerce.bikes.http.UserRegisterRequest;
import com.ecommerce.bikes.http.UserRegisterResponse;
import com.ecommerce.bikes.useCases.RegisterUserUseCase;
import com.ecommerce.bikes.useCases.VerifyUserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

            User user = verifyUserUseCase.verify(userRegisterRequest.getEmail(), userRegisterRequest.getPassword());
            UserRegisterResponse response = UserRegisterResponse.toUserRegisterResponse(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserRegisterResponse> save(@RequestBody UserRegisterRequest userRegisterRequest) {
        UserRegisterResponse userSaved = UserRegisterResponse.toUserRegisterResponse(
                registerUserUseCase.save(UserRegisterRequest.toDomain(userRegisterRequest))
        );
        return new ResponseEntity<>(userSaved, HttpStatus.OK);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExist(
            UserAlreadyExistException uae
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), uae.getMessage()));
    }

    @ExceptionHandler(UserIsNotValidException.class)
    public ResponseEntity<ErrorResponse> handleUserIsNotValid(
            UserIsNotValidException uinve
    ) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), uinve.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFoundException unfe
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), unfe.getMessage()));
    }
}
