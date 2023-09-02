package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entities.LikeEntity;
import com.ecommerce.bikes.entities.ProductEntity;
import com.ecommerce.bikes.entities.UserEntity;
import com.ecommerce.bikes.exception.LikeDoesNotExistResultException;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.http.ProductResponse;
import com.ecommerce.bikes.useCases.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;
    private final FindProductByIdUseCase findProductByIdUseCase = mock(FindProductByIdUseCase.class);
    private final FindAllProductsUseCase findAllProductsUseCase = mock(FindAllProductsUseCase.class);
    private final FindAllProductsByTypeUseCase findAllProductsByTypeUseCase = mock(FindAllProductsByTypeUseCase.class);
    private final FindFavouritesUseCase findFavouritesUseCase = mock(FindFavouritesUseCase.class);
    private final FindProductsByNameUseCase findProductsByNameUseCase = mock(FindProductsByNameUseCase.class);
    private final InsertLikeUseCase insertLikeUseCase = mock(InsertLikeUseCase.class);
    private final GetLikeUseCase getLikeUseCase = mock(GetLikeUseCase.class);
    private final DeleteLikeUseCase deleteLikeUseCase = mock(DeleteLikeUseCase.class);

    @AfterEach
    public void resetMocks() {
        reset(findProductByIdUseCase);
        reset(findProductByIdUseCase);
        reset(findAllProductsByTypeUseCase);
        reset(findAllProductsUseCase);
        reset(findFavouritesUseCase);
        reset(findProductsByNameUseCase);
        reset(insertLikeUseCase);
        reset(getLikeUseCase);
        reset(deleteLikeUseCase);
    }

    @Test
    @DisplayName("GIVEN a specific id WHEN user find product THEN existent product is returned")
    public void should_return_product_by_id() {
        when(findProductByIdUseCase.find(1L)).thenReturn(product);

        ResponseEntity<ProductResponse> response = productController.findProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productResponse, response.getBody());
    }

    @Test
    @DisplayName("GIVEN a specific id WHEN user find product THEN throw exception because product doesn't exist")
    public void should_throw_ProductNotFoundException_when_get_product_by_id() {
        when(findProductByIdUseCase.find(1L)).thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class, () -> {
            productController.findProductById(1L);
        });
    }

    @Test
    @DisplayName("WHEN user find all products THEN these are returned")
    public void should_return_all_products() {
        when(findAllProductsUseCase.find()).thenReturn(products);

        ResponseEntity<List<ProductResponse>> response = productController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsResponses, response.getBody());
    }

    @Test
    @DisplayName("GIVEN a specific type WHEN user find all products THEN these are returned")
    public void should_return_all_products_by_type() {
        String type = "road";

        when(findAllProductsByTypeUseCase.find(type)).thenReturn(products);

        ResponseEntity<List<ProductResponse>> response = productController.findAllByType(type);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsResponses, response.getBody());
    }

    @Test
    @DisplayName("GIVEN a specific user id WHEN user find all favourite user products THEN these are returned")
    public void should_return_all_favourite_products() {

        when(findFavouritesUseCase.find(1L)).thenReturn(products);

        ResponseEntity<List<ProductResponse>> response = productController.findFavourites(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsResponses, response.getBody());
    }

    @Test
    @DisplayName("GIVEN a specific user id WHEN user find all favourites user products THEN throw exception because user doesn't exist")
    public void should_throw_UserNotFoundException_when_get_all_favourite_products() {

        when(findFavouritesUseCase.find(1L)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> {
            productController.findFavourites(1L);
        });
    }

    @Test
    @DisplayName("GIVEN a specific name chain WHEN user find all products THEN these are returned")
    public void should_return_all_products_by_name() {
        String name = "Meth";

        when(findProductsByNameUseCase.find(name)).thenReturn(products);

        ResponseEntity<List<ProductResponse>> response = productController.findByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productsResponses, response.getBody());
    }

    @Test
    @DisplayName("GIVEN a specific user product WHEN user adds like to a new product THEN this is applied successfully")
    public void should_add_like() {
        when(insertLikeUseCase.add(1L, 1L)).thenReturn(10);

        ResponseEntity<Integer> response = productController.addLike(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("GIVEN a specific user product WHEN user tries to find like THEN this is returned")
    public void should_get_like() {
        when(getLikeUseCase.get(1L, 1L)).thenReturn(1);

        ResponseEntity<Object> response = productController.getLike(1L, 1L);

        Object result = response.getBody();

        assertNotNull(result);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(Integer.parseInt(result.toString())).isGreaterThan(0);
    }

    @Test
    @DisplayName("GIVEN a specific user product WHEN user tries to find like THEN throw exception because like doesn't exist")
    public void should_throw_LikeDoesNotExistResultException_when_get_like() {
        when(getLikeUseCase.get(1L, 1L)).thenThrow(LikeDoesNotExistResultException.class);

        assertThrows(LikeDoesNotExistResultException.class, () -> {
            productController.getLike(1L, 1L);
        });
    }

    @Test
    @DisplayName("GIVEN a specific user product WHEN user deletes like to a new product THEN this is removed successfully")
    public void should_delete_like() {
        when(deleteLikeUseCase.delete(1L, 1L)).thenReturn(10);

        ResponseEntity<Integer> response = productController.deleteLike(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    protected static final ProductEntity productEntity =
            new ProductEntity(
                    2L,
                    "Methanol XH",
                    "road",
                    34f,
                    34,
                    "dsf",
                    "fds",
                    emptySet(),
                    emptyList(),
                    emptyList()
            );

    protected static final ProductEntity productEntityWithLikes =
            new ProductEntity(
                    1L,
                    "Methanol XH",
                    "road",
                    34f,
                    34,
                    "dsf",
                    "fds",
                    emptySet(),
                    emptyList(),
                    List.of(new LikeEntity(
                            1L,
                            new UserEntity(
                                    1L,
                                    "johndoe@doe.com",
                                    'U',
                                    "pepe",
                                    Collections.emptyList(),
                                    Collections.emptyList()
                            ),
                            new ProductEntity(
                                    1L,
                                    "Methanol XH",
                                    "road",
                                    34f,
                                    34,
                                    "dsf",
                                    "fds",
                                    emptySet(),
                                    emptyList(),
                                    Collections.emptyList()
                            )
                    ))
            );
    protected static final Product product = ProductEntity.toDomain(productEntity);
    protected static final Product productWithLikes = ProductEntity.toDomain(productEntityWithLikes);
    protected static final List<Product> products = List.of(product);
    protected static final ProductResponse productResponse = ProductResponse.toProductResponse(product);
    protected static final ProductResponse productResponseWithLikes = ProductResponse.toProductResponse(productWithLikes);
    protected static final List<ProductResponse> productsResponses = List.of(productResponse);
    protected static final List<ProductResponse> productsResponsesWithLikes = List.of(productResponseWithLikes);
}
