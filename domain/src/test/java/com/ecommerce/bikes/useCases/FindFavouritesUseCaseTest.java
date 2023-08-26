package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static com.ecommerce.bikes.ProductMother.favouritesProductsDomain;
import static com.ecommerce.bikes.UserMother.savedUserWithLikes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindFavouritesUseCaseTest {

    private final UserRepositoryPort userRepositoryPort = mock(UserRepositoryPort.class);
    private final FindProductByIdUseCase findProductByIdUseCase = mock(FindProductByIdUseCase.class);
    private final FindFavouritesUseCase findFavouritesUseCase = new FindFavouritesUseCase(
            userRepositoryPort,
            findProductByIdUseCase
    );

    @AfterEach
    public void resetMocks() {
        reset(userRepositoryPort);
    }

    @Test
    public void find_favourites() throws UserNotFoundException {

        Long userId = 1L;
        Long productId = 1L;

        when(userRepositoryPort.findById(userId)).thenReturn(savedUserWithLikes);
        when(findProductByIdUseCase.find(productId)).thenReturn(favouritesProductsDomain.get(0));

        List<Product> products = findFavouritesUseCase.find(userId);
        assertEquals(favouritesProductsDomain, products);
    }

    @Test
    public void throw_UserNotFoundException_if_user_does_not_exist() throws UserNotFoundException {
        when(userRepositoryPort.findById(1L)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> {
            findFavouritesUseCase.find(1L);
        });
    }
}
