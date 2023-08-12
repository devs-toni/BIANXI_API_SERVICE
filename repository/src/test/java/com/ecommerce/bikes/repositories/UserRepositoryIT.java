package com.ecommerce.bikes.repositories;

import com.ecommerce.bikes.entities.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryIT extends DockerConfiguration {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public void prepareTests() {
        userRepository.save(userToSave);
    }

    @Test
    @Disabled
    public void should_return_user_by_id() {
        UserEntity user = userRepository.findById(1L).get();

        assertEquals(userToSave, user);

    }

    @Test
    public void should_return_user_by_email() {
        UserEntity user = userRepository.findByEmail("dummy").get();

        assertEquals(userToSave, user);
    }

    private static final UserEntity userToSave = new UserEntity("dummy", 'U', "pepe", Collections.emptyList(), Collections.emptyList());
}
