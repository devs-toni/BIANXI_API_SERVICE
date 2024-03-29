package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entities.UserEntity;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserAdapterTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserAdapter userAdapter = new UserAdapter(userRepository);

    @AfterEach
    public void resetMocks() {
        reset(userRepository);
    }

    @Test
    @DisplayName("GIVEN a specific user id WHEN user adapter call find user THEN this is returned")
    public void should_return_user_when_find_one_that_exist() {
        when(userRepository.findById(userEntity.getId())).thenReturn(Optional.ofNullable(userEntity));

        assertEquals(user, userAdapter.findById(userEntity.getId()));
    }

    @Test
    @DisplayName("GIVEN a specific id WHEN user adapter call find user THEN throw exception because user doesn't exist")
    public void should_throw_exception_user_when_find_one_that_does_not_exist() {
        when(userRepository.findById(userEntity.getId())).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> {
            userAdapter.findById(userEntity.getId());
        });
    }

    @Test
    @DisplayName("GIVEN a specific email WHEN user adapter call find user THEN this is returned")
    public void should_return_user_when_find_one_by_email() {
        when(userRepository.findByEmail(userEntity.getEmail())).thenReturn(Optional.ofNullable(userEntity));

        assertEquals(user, userAdapter.findByEmail(userEntity.getEmail()));
    }

    @Test
    @DisplayName("GIVEN a specific email WHEN user adapter call find user THEN throw exception because user doesn't exist")
    public void should_throw_exception_user_when_find_one_by_email_that_does_not_exist() {
        when(userRepository.findByEmail(userEntity.getEmail())).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> {
            userAdapter.findByEmail(userEntity.getEmail());
        });
    }

    @Test
    @DisplayName("GIVEN a new user WHEN user adapter call save THEN new user is created")
    public void should_store_user_when_save() {
        when(userRepository.save(userEntityWithoutId)).thenReturn(userEntity);

        assertEquals(user, userAdapter.save(userWithoutId));
    }

    public static final UserEntity userEntity = new UserEntity(
            1L,
            "doe@doe.com",
            'U',
            "pepe",
            Collections.emptyList(),
            Collections.emptyList()
    );

    private static final UserEntity userEntityWithoutId = new UserEntity(
            "doe@doe.com",
            'U',
            "pepe",
            Collections.emptyList(),
            Collections.emptyList()
    );

    public static final User user = new User(
            1L,
            "doe@doe.com",
            'U',
            "pepe",
            Collections.emptyList(),
            Collections.emptyList()
    );

    private static final User userWithoutId = new User(
            "doe@doe.com",
            'U',
            "pepe",
            Collections.emptyList(),
            Collections.emptyList()
    );
}
