package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.exception.UserIsNotValidException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.ecommerce.bikes.UserMother.savedUser;
import static com.ecommerce.bikes.UserMother.userToSave;
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
    public void user_is_verified_successfully() throws UserIsNotValidException, UserNotFoundException {
        String expectedEmail = savedUser.getEmail();
        String expectedPass = userToSave.getPassword();

        when(userRepositoryPort.findByEmail(expectedEmail)).thenReturn(savedUser);
        when(passwordEncoder.matches(expectedPass, savedUser.getPassword())).thenReturn(true);


        assertEquals(verifyUserUseCase.verify(expectedEmail, expectedPass), savedUser);
    }

    @Test
    public void throw_UserNotFoundException_when_user_does_not_exist() throws UserNotFoundException {
        String expectedEmail = savedUser.getEmail();
        String expectedPass = userToSave.getPassword();

        when(userRepositoryPort.findByEmail(expectedEmail)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> {
            assertEquals(verifyUserUseCase.verify(expectedEmail, expectedPass), savedUser);
        });
    }

    @Test
    public void throw_UserIsNotValidException_when_password_is_not_correct() throws UserNotFoundException {
        String expectedEmail = savedUser.getEmail();
        String expectedPass = userToSave.getPassword();

        when(userRepositoryPort.findByEmail(expectedEmail)).thenReturn(savedUser);
        when(passwordEncoder.matches(expectedPass, savedUser.getPassword())).thenReturn(false);

        assertThrows(UserIsNotValidException.class, () -> {
            assertEquals(verifyUserUseCase.verify(expectedEmail, expectedPass), savedUser);
        });
    }
}
