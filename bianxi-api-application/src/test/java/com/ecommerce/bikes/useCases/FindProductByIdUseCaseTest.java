package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.TestDataHelpers;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.ecommerce.bikes.TestDataHelpers.createProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FindProductByIdUseCaseTest {
    private final ProductRepositoryPort productRepositoryPort = mock(ProductRepositoryPort.class);

    private final FindProductByIdUseCase findProductByIdUseCase = new FindProductByIdUseCase(productRepositoryPort);

    @AfterEach
    public void resetMocks() {
        reset(productRepositoryPort);
    }

    @Test
    public void find_correct_product() throws ProductNotFoundException {
        Long productId = 1L;

        when(productRepositoryPort.findById(productId)).thenReturn(product);

        assertEquals(productExpected, findProductByIdUseCase.find(productId));
    }

    @Test
    public void throw_ProductNotFoundException() throws ProductNotFoundException {
        when(productRepositoryPort.findById(1L)).thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class, () -> {
            findProductByIdUseCase.find(1L);
        });
    }


    public static Product product = TestDataHelpers.createProduct();
    public static Product productExpected = TestDataHelpers.createProduct();
}
