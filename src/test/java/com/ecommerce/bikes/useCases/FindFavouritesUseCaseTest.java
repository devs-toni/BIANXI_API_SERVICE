package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Optional;

import static com.ecommerce.bikes.UserMother.savedUserEntityWithLikes;
import static com.ecommerce.bikes.UserMother.savedUserWithLikes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindFavouritesUseCaseTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final FindFavouritesUseCase findFavouritesUseCase = new FindFavouritesUseCase(userRepository);

    @AfterEach
    public void resetMocks() {
        reset(userRepository);
    }

    @Test
    public void find_favourites() throws UserNotFoundException {

        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(savedUserEntityWithLikes));

        assertEquals(savedUserWithLikes.getLikes().get(0).getProduct(), findFavouritesUseCase.find(userId).get(0));
    }

    @Test
    public void throw_UserNotFoundException_if_user_does_not_exist () {
        assertThrows(UserNotFoundException.class, () -> {
            findFavouritesUseCase.find(1L);
        });
    }
}
