package com.ecommerce.bikes.repositories;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.entities.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryIT extends DockerConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("GIVEN a specific id WHEN user repository call find user THEN existent user is returned")
    public void should_return_user_by_id() {
        UserEntity user = userRepository.findById(2L).get();

        assertEquals(userSaved, user);
    }

    @Test
    @DisplayName("GIVEN a specific email WHEN user repository call find user THEN existent user is returned")
    public void should_return_user_by_email() {
        UserEntity user = userRepository.findByEmail("admin").get();

        assertEquals(userSaved, user);
    }

    private static final UserEntity userSaved = new UserEntity(2L, "admin", 'A', "$2a$12$1X5edHiITLLSZZTXYeVjievcICKecHVJOsPcKjJpWLs2vCh5ipVRu", emptyList(), emptyList());
}
