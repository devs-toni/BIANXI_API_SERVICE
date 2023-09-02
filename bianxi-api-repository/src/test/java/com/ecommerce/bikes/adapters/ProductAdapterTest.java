package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entities.ProductEntity;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductAdapterTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);

    private final ProductAdapter productAdapter = new ProductAdapter(productRepository);

    @AfterEach
    public void resetMocks() {
        reset(productRepository);
    }

    @Test
    @DisplayName("GIVEN a specific id WHEN product adapter call find product THEN existent product is returned")
    public void should_return_product_when_find_one_by_id() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(productsEntities.get(0)));

        Product product = productAdapter.findById(1L);

        assertEquals(productsDomain.get(0), product);
    }

    @Test
    @DisplayName("GIVEN a specific id WHEN product adapter call find product THEN throw exception because product doesn't exist")
    public void should_throw_exception_when_find_one_that_does_not_exist() {
        when(productRepository.findById(1L)).thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class, () -> {
            productAdapter.findById(1L);
        });
    }

    @Test
    @DisplayName("WHEN product adapter call find all products THEN these are returned")
    public void should_return_all_products_when_find_all() {
        when(productRepository.findAll()).thenReturn(productsEntities);

        List<Product> products = productAdapter.findAll();

        assertEquals(productsDomain, products);
    }

    @Test
    @DisplayName("GIVEN a specific product type WHEN product adapter call find all products THEN these are returned")
    public void should_return_list_products_when_find_all_by_type() {
        when(productRepository.findAllByType(type)).thenReturn(productsEntities);

        List<Product> products = productAdapter.findAllByType(type);

        assertEquals(productsDomain, products);
    }

    @Test
    @DisplayName("GIVEN a specific product name WHEN product adapter call find all products THEN these are returned")
    public void should_return_list_products_when_find_all_by_name() {
        when(productRepository.findByNameContainingIgnoreCase(name)).thenReturn(productsEntities);

        List<Product> products = productAdapter.findByNameContainingIgnoreCase(name);

        assertEquals(productsDomain, products);
    }


    private static final List<ProductEntity> productsEntities = List.of(
            new ProductEntity(
                    1L,
                    "Dummy Product",
                    "Dummy Type",
                    5f,
                    10,
                    "sentence",
                    "description",
                    Collections.emptySet(),
                    Collections.emptyList(),
                    Collections.emptyList()
            )
    );

    private static final List<Product> productsDomain = List.of(
            new Product(
                    1L,
                    "Dummy Product",
                    "Dummy Type",
                    5f,
                    10,
                    "sentence",
                    "description",
                    Collections.emptySet(),
                    Collections.emptyList(),
                    Collections.emptyList()
            )
    );

    private static final String type = "road";
    private static final String name = "bike";
}
