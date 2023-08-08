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
public class FindProductsByNameUseCaseTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final FindProductsByNameUseCase findProductsByNameUseCase = new FindProductsByNameUseCase(productRepository);

    @Test
    public void search_products_by_name() {

        String name = "Bike";

        when(productRepository.findByNameContainingIgnoreCase(name)).thenReturn(productsByType);

        assertEquals(productsByTypeDomain, findProductsByNameUseCase.find(name));

    }
}
