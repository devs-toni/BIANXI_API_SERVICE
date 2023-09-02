package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.TestDataHelpers;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindProductByIdUseCaseTest {
    private final ProductRepositoryPort productRepositoryPort = mock(ProductRepositoryPort.class);

    private final FindProductByIdUseCase findProductByIdUseCase = new FindProductByIdUseCase(productRepositoryPort);

    @AfterEach
    public void resetMocks() {
        reset(productRepositoryPort);
    }

    @Test
    @DisplayName("WHEN user wants specific product THEN this is returned successfully")
    public void find_correct_product() {
        Long productId = 1L;

        when(productRepositoryPort.findById(productId)).thenReturn(product);

        assertEquals(productExpected, findProductByIdUseCase.find(productId));
    }

    @Test
    @DisplayName("WHEN user wants specific order THEN throw exception because product doesn't exist")
    public void throw_ProductNotFoundException() {
        when(productRepositoryPort.findById(1L)).thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class, () -> {
            findProductByIdUseCase.find(1L);
        });
    }


    public static Product product = TestDataHelpers.createProduct();
    public static Product productExpected = TestDataHelpers.createProduct();
}
