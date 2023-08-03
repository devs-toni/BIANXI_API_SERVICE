package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.entity.UserDAO;
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
    public void userIsVerifiedSuccessfully() {
        String expectedEmail = savedUserDAO.getEmail();
        String expectedPass = userToSave.getPassword();

        when(userRepository.findByEmail(expectedEmail)).thenReturn(Optional.ofNullable(savedUserDAO));
        when(passwordEncoder.matches(expectedPass, savedUserDAO.getPassword())).thenReturn(true);


        assertEquals(verifyUserUseCase.verify(expectedEmail, expectedPass), UserDAO.toDomain(savedUserDAO));
    }

    @Test
    public void throwNoSuchElementExceptionWhenUserDoesNotExist() {
        String expectedEmail = savedUserDAO.getEmail();
        String expectedPass = userToSave.getPassword();

        when(userRepository.findByEmail(expectedEmail)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            assertEquals(verifyUserUseCase.verify(expectedEmail, expectedPass), UserDAO.toDomain(savedUserDAO));
        });
    }

    @Test
    public void throwNoSuchElementExceptionWhenPasswordIsNotCorrect() {
        String expectedEmail = savedUserDAO.getEmail();
        String expectedPass = userToSave.getPassword();

        when(userRepository.findByEmail(expectedEmail)).thenReturn(Optional.ofNullable(savedUserDAO));
        when(passwordEncoder.matches(expectedPass, savedUserDAO.getPassword())).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> {
            assertEquals(verifyUserUseCase.verify(expectedEmail, expectedPass), UserDAO.toDomain(savedUserDAO));
        });
    }
}
