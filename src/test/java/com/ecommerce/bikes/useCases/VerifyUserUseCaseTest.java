package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.entity.UserDAO;
import com.ecommerce.bikes.exception.UserIsNotValidException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.ecommerce.bikes.UserMother.savedUserDAO;
import static com.ecommerce.bikes.UserMother.userToSave;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VerifyUserUseCaseTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final VerifyUserUseCase verifyUserUseCase = new VerifyUserUseCase(userRepository, passwordEncoder);

    @AfterEach
    public void resetMocks() {
        reset(userRepository);
        reset(passwordEncoder);
    }

    @Test
    public void user_is_verified_successfully() throws UserIsNotValidException, UserNotFoundException {
        String expectedEmail = savedUserDAO.getEmail();
        String expectedPass = userToSave.getPassword();

        when(userRepository.findByEmail(expectedEmail)).thenReturn(Optional.ofNullable(savedUserDAO));
        when(passwordEncoder.matches(expectedPass, savedUserDAO.getPassword())).thenReturn(true);


        assertEquals(verifyUserUseCase.verify(expectedEmail, expectedPass), UserDAO.toDomain(savedUserDAO));
    }

    @Test
    public void throw_UserNotFoundException_when_user_does_not_exist() {
        String expectedEmail = savedUserDAO.getEmail();
        String expectedPass = userToSave.getPassword();

        when(userRepository.findByEmail(expectedEmail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            assertEquals(verifyUserUseCase.verify(expectedEmail, expectedPass), UserDAO.toDomain(savedUserDAO));
        });
    }

    @Test
    public void throw_UserIsNotValidException_when_password_is_not_correct() {
        String expectedEmail = savedUserDAO.getEmail();
        String expectedPass = userToSave.getPassword();

        when(userRepository.findByEmail(expectedEmail)).thenReturn(Optional.ofNullable(savedUserDAO));
        when(passwordEncoder.matches(expectedPass, savedUserDAO.getPassword())).thenReturn(false);

        assertThrows(UserIsNotValidException.class, () -> {
            assertEquals(verifyUserUseCase.verify(expectedEmail, expectedPass), UserDAO.toDomain(savedUserDAO));
        });
    }
}
