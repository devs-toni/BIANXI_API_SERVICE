package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.UserAlreadyExistException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.ecommerce.bikes.UserMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterUserUseCaseTest {

    private final UserRepositoryPort userRepositoryPort = mock(UserRepositoryPort.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final RegisterUserUseCase registerUserUseCase = new RegisterUserUseCase(userRepositoryPort, passwordEncoder);

    @Test
    public void user_is_saved_successfully() throws UserAlreadyExistException, UserNotFoundException {

        User user = userToSave;

        when(userRepositoryPort.findByEmail(user.getEmail())).thenThrow(UserNotFoundException.class);
        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
        when(
                userRepositoryPort.save(userToSaveWithEncodedPassword)
        ).thenReturn(savedUser);

        User userSaved = registerUserUseCase.save(user);

        assertEquals(savedUser, userSaved);
    }

    @Test
    public void throws_UserAlreadyExistsException_when_user_is_present() throws UserNotFoundException {
        User user = userToSave;

        when(userRepositoryPort.findByEmail(user.getEmail())).thenReturn(savedUser);

        assertThrows(UserAlreadyExistException.class, () -> {
            registerUserUseCase.save(user);
        });
    }

}
