package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.ecommerce.bikes.ProductMother.productsByType;
import static com.ecommerce.bikes.ProductMother.productsByTypeDomain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindAllProductsUseCaseTest {

    private ProductRepository productRepository = mock(ProductRepository.class);

    private FindAllProductsUseCase findAllProductsUseCase = new FindAllProductsUseCase(productRepository);

    @Test
    public void findAllProducts() {
        when(productRepository.findAll()).thenReturn(productsByType);

        assertEquals(productsByTypeDomain.get(0), findAllProductsUseCase.find().get(0));
    }
}
