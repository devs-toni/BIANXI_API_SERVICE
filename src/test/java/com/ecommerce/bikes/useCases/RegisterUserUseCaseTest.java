package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.ecommerce.bikes.UserMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterUserUseCaseTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final RegisterUserUseCase registerUserUseCase = new RegisterUserUseCase(userRepository, passwordEncoder);

    @Test
    public void user_is_saved_successfully() {

        User user = userToSave;

        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);

        when(
                userRepository.save(User.toEntity(userToSaveWithEncodedPassword))
        ).thenReturn(savedUserDAO);

        User userSaved = registerUserUseCase.save(user);

        assertEquals(savedUser, userSaved);
    }

}
