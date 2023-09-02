package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.UserAlreadyExistException;
import com.ecommerce.bikes.exception.UserIsNotValidException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.http.UserRegisterRequest;
import com.ecommerce.bikes.http.UserRegisterResponse;
import com.ecommerce.bikes.useCases.RegisterUserUseCase;
import com.ecommerce.bikes.useCases.VerifyUserUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    private final RegisterUserUseCase registerUserUseCase = mock(RegisterUserUseCase.class);
    private final VerifyUserUseCase verifyUserUseCase = mock(VerifyUserUseCase.class);

    @AfterEach
    public void resetMocks() {
        reset(registerUserUseCase);
        reset(verifyUserUseCase);
    }

    @Test
    @DisplayName("GIVEN a new user WHEN user saves THEN new user is saved")
    public void should_register_new_user() {
        when(registerUserUseCase.save(userToSave)).thenReturn(savedUser);

        UserRegisterResponse userResponse = userController.save(request).getBody();

        assertEquals(response, userResponse);
    }

    @Test
    @DisplayName("GIVEN a new user WHEN user saves THEN throw exception because the user already exist in system")
    public void should_throw_UserAlreadyExistException_when_register_new_user() {
        when(registerUserUseCase.save(userToSave)).thenThrow(UserAlreadyExistException.class);

        assertThrows(UserAlreadyExistException.class, () -> {
            userController.save(request);
        });
    }

    @Test
    @DisplayName("GIVEN user data WHEN user login THEN user is verified successfully")
    public void should_verify_user() {
        when(verifyUserUseCase.verify(userToSave.getEmail(), userToSave.getPassword())).thenReturn(savedUser);

        UserRegisterResponse userResponse = userController.verify(request).getBody();

        assertEquals(response, userResponse);
    }

    @Test
    @DisplayName("GIVEN user data WHEN user login THEN throw exception because password is not correct")
    public void should_throw_UserIsNotValidException_when_verify_user() {
        when(verifyUserUseCase.verify(userToSave.getEmail(), userToSave.getPassword())).thenThrow(UserIsNotValidException.class);

        assertThrows(UserIsNotValidException.class, () -> {
            userController.verify(request);
        });
    }

    @Test
    @DisplayName("GIVEN user data WHEN user login THEN throw exception because user doesn't exist")
    public void should_throw_UserNotFoundException_when_verify_user() {
        when(verifyUserUseCase.verify(userToSave.getEmail(), userToSave.getPassword())).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> {
            userController.verify(request);
        });
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
