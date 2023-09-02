package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.ProductMother;
import com.ecommerce.bikes.UserMother;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import org.junit.jupiter.api.*;

import java.util.List;

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
    @DisplayName("WHEN user wants all user favourite products THEN these are returned successfully")
    public void find_favourites() {

        Long userId = 1L;
        Long productId = 1L;

        when(userRepositoryPort.findById(userId)).thenReturn(UserMother.savedUserWithLikes);
        when(findProductByIdUseCase.find(productId)).thenReturn(ProductMother.favouritesProductsDomain.get(0));

        List<Product> products = findFavouritesUseCase.find(userId);
        Assertions.assertEquals(ProductMother.favouritesProductsDomain, products);
    }

    @Test
    @DisplayName("WHEN user wants all user orders THEN throw exception because user doesn't exist")
    public void throw_UserNotFoundException_if_user_does_not_exist() {
        when(userRepositoryPort.findById(1L)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> {
            findFavouritesUseCase.find(1L);
        });
    }
}
