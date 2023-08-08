package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.ProductDAO;
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
    private ProductRepository productRepository = mock(ProductRepository.class);

    private FindProductByIdUseCase findProductByIdUseCase = new FindProductByIdUseCase(productRepository);

    @AfterEach
    public void resetMocks() {
        reset(productRepository);
    }

    @Test
    public void findCorrectProduct() throws ProductNotFoundException {
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.of(productDAO));

        assertEquals(productExpected, findProductByIdUseCase.find(productId));
    }

    @Test
    public void throwProductNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> {
            findProductByIdUseCase.find(1L);
        });
    }



    public static ProductDAO productDAO = createProductDAO();
    public static Product productExpected = createProduct();
}
