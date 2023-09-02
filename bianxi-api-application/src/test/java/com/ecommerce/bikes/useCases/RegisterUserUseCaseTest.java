package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.UserMother;
import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.UserAlreadyExistException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegisterUserUseCaseTest {

    private final UserRepositoryPort userRepositoryPort = mock(UserRepositoryPort.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final RegisterUserUseCase registerUserUseCase = new RegisterUserUseCase(userRepositoryPort, passwordEncoder);

    @AfterEach
    public void resetMocks() {
        reset(userRepositoryPort);
        reset(passwordEncoder);
    }

    @Test
    public void user_is_saved_successfully() {

        User user = UserMother.userToSave;

        when(userRepositoryPort.findByEmail(user.getEmail())).thenThrow(UserNotFoundException.class);
        when(passwordEncoder.encode(user.getPassword())).thenReturn(UserMother.encodedPassword);
        when(
                userRepositoryPort.save(UserMother.userToSaveWithEncodedPassword)
        ).thenReturn(UserMother.savedUser);

        User userSaved = registerUserUseCase.save(user);

        Assertions.assertEquals(UserMother.savedUser, userSaved);
    }

    @Test
    public void throws_UserAlreadyExistsException_when_user_is_present() {
        User user = UserMother.userToSave;

        when(userRepositoryPort.findByEmail(user.getEmail())).thenReturn(UserMother.savedUser);

        assertThrows(UserAlreadyExistException.class, () -> {
            registerUserUseCase.save(user);
        });
    }

}
