package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.UserMother;
import com.ecommerce.bikes.exception.UserIsNotValidException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VerifyUserUseCaseTest {

    private final UserRepositoryPort userRepositoryPort = mock(UserRepositoryPort.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final VerifyUserUseCase verifyUserUseCase = new VerifyUserUseCase(userRepositoryPort, passwordEncoder);

    @AfterEach
    public void resetMocks() {
        reset(userRepositoryPort);
        reset(passwordEncoder);
    }

    @Test
    public void user_is_verified_successfully() {
        String expectedEmail = UserMother.savedUser.getEmail();
        String expectedPass = UserMother.userToSave.getPassword();

        when(userRepositoryPort.findByEmail(expectedEmail)).thenReturn(UserMother.savedUser);
        when(passwordEncoder.matches(expectedPass, UserMother.savedUser.getPassword())).thenReturn(true);


        assertEquals(verifyUserUseCase.verify(expectedEmail, expectedPass), UserMother.savedUser);
    }

    @Test
    public void throw_UserNotFoundException_when_user_does_not_exist() {
        String expectedEmail = UserMother.savedUser.getEmail();
        String expectedPass = UserMother.userToSave.getPassword();

        when(userRepositoryPort.findByEmail(expectedEmail)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> {
            assertEquals(verifyUserUseCase.verify(expectedEmail, expectedPass), UserMother.savedUser);
        });
    }

    @Test
    public void throw_UserIsNotValidException_when_password_is_not_correct() {
        String expectedEmail = UserMother.savedUser.getEmail();
        String expectedPass = UserMother.userToSave.getPassword();

        when(userRepositoryPort.findByEmail(expectedEmail)).thenReturn(UserMother.savedUser);
        when(passwordEncoder.matches(expectedPass, UserMother.savedUser.getPassword())).thenReturn(false);

        assertThrows(UserIsNotValidException.class, () -> {
            assertEquals(verifyUserUseCase.verify(expectedEmail, expectedPass), UserMother.savedUser);
        });
    }
}
