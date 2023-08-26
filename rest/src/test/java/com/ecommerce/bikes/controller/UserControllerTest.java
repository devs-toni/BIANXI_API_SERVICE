package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.UserAlreadyExistException;
import com.ecommerce.bikes.exception.UserIsNotValidException;
import com.ecommerce.bikes.http.UserRegisterRequest;
import com.ecommerce.bikes.http.UserRegisterResponse;
import com.ecommerce.bikes.useCases.RegisterUserUseCase;
import com.ecommerce.bikes.useCases.VerifyUserUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    private final RegisterUserUseCase registerUserUseCase = mock(RegisterUserUseCase.class);
    private final VerifyUserUseCase verifyUserUseCase = mock(VerifyUserUseCase.class);

    @Test
    public void should_register_new_user() throws UserAlreadyExistException {
        when(registerUserUseCase.save(userToSave)).thenReturn(savedUser);

        UserRegisterResponse userResponse = userController.save(request).getBody();

        assertEquals(response, userResponse);
    }

    @Test
    public void should_verify_user() throws UserIsNotValidException {
        when(verifyUserUseCase.verify(userToSave.getEmail(), userToSave.getPassword())).thenReturn(savedUser);

        UserRegisterResponse userResponse = userController.verify(request).getBody();

        assertEquals(response, userResponse);
    }

    public static User userToSave = new User(
            "example@john.com",
            'U',
            "pass",
            Collections.emptyList(),
            Collections.emptyList()
    );
    public static UserRegisterResponse response = new UserRegisterResponse(
            1L,
            "example@john.com",
            'U'
    );
    public static UserRegisterRequest request = new UserRegisterRequest("example@john.com", "pass");
    public static User savedUser = new User(
            1L,
            "example@john.com",
            "pass"
    );
}
