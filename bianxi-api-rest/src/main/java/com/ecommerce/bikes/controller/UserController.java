package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.ErrorResponse;
import com.ecommerce.bikes.exception.UserAlreadyExistException;
import com.ecommerce.bikes.exception.UserIsNotValidException;
import com.ecommerce.bikes.exception.UserNotFoundException;
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

    @ExceptionHandler({UserNotFoundException.class, UserIsNotValidException.class})
    public ResponseEntity<ErrorResponse> handleUserErrors(
            RuntimeException re
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ErrorResponse(HttpStatus.OK.value(), re.getMessage(), "CREDENTIALS_ARE_NOT_VALID"));
    }
}
