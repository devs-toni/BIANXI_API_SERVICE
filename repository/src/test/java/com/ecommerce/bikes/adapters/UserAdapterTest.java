package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entities.UserEntity;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserAdapterTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserAdapter userAdapter = new UserAdapter(userRepository);

    @Test
    public void find_by_id() throws UserNotFoundException {
        when(userRepository.findById(userEntity.getId())).thenReturn(Optional.ofNullable(userEntity));

        assertEquals(user, userAdapter.findById(userEntity.getId()));
    }

    @Test
    public void find_by_email() throws UserNotFoundException {
        when(userRepository.findByEmail(userEntity.getEmail())).thenReturn(Optional.ofNullable(userEntity));

        assertEquals(user, userAdapter.findByEmail(userEntity.getEmail()));
    }

    @Test
    public void save() {
        when(userRepository.save(userEntityWithoutId)).thenReturn(userEntity);

        assertEquals(user, userAdapter.save(userWithoutId));
    }

    public static UserEntity userEntity = new UserEntity(
            1L,
            "doe@doe.com",
            'U',
            "pepe",
            Collections.emptyList(),
            Collections.emptyList()
    );

    public static UserEntity userEntityWithoutId = new UserEntity(
            "doe@doe.com",
            'U',
            "pepe",
            Collections.emptyList(),
            Collections.emptyList()
    );

    public static User user = new User(
            1L,
            "doe@doe.com",
            'U',
            "pepe",
            Collections.emptyList(),
            Collections.emptyList()
    );

    public static User userWithoutId = new User(
            "doe@doe.com",
            'U',
            "pepe",
            Collections.emptyList(),
            Collections.emptyList()
    );
}
