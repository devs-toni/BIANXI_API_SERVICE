package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.UserAlreadyExistException;
import com.ecommerce.bikes.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.ecommerce.bikes.UserMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterUserUseCaseTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final RegisterUserUseCase registerUserUseCase = new RegisterUserUseCase(userRepository, passwordEncoder);

    @Test
    public void user_is_saved_successfully() throws UserAlreadyExistException {

        User user = userToSave;

        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);

        when(
                userRepository.save(User.toEntity(userToSaveWithEncodedPassword))
        ).thenReturn(savedUserDAO);

        User userSaved = registerUserUseCase.save(user);

        assertEquals(savedUser, userSaved);
    }

    @Test
    public void throws_UserAlreadyExistsException_when_user_is_present() {
        User user = userToSave;

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(savedUserDAO));

        assertThrows(UserAlreadyExistException.class, () -> {
            registerUserUseCase.save(user);
        });
    }

}
