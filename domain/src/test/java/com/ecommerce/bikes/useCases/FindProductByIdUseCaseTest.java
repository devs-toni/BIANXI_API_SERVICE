package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.ProductEntity;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.ecommerce.bikes.TestDataHelpers.createProduct;
import static com.ecommerce.bikes.TestDataHelpers.createProductDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FindProductByIdUseCaseTest {
    private final ProductRepository productRepository = mock(ProductRepository.class);

    private final FindProductByIdUseCase findProductByIdUseCase = new FindProductByIdUseCase(productRepository);

    @AfterEach
    public void resetMocks() {
        reset(productRepository);
    }

    @Test
    public void find_correct_product() throws ProductNotFoundException {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));

        assertEquals(productExpected, findProductByIdUseCase.find(productId));
    }

    @Test
    public void throw_ProductNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> {
            findProductByIdUseCase.find(1L);
        });
    }



    public static ProductEntity productEntity = createProductDAO();
    public static Product productExpected = createProduct();
}
