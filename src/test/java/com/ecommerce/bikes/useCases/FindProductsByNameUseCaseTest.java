package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.ecommerce.bikes.ProductMother.productsByType;
import static com.ecommerce.bikes.ProductMother.productsByTypeDomain;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindProductsByNameUseCaseTest {

    private ProductRepository productRepository = mock(ProductRepository.class);
    private FindProductsByNameUseCase findProductsByNameUseCase = new FindProductsByNameUseCase(productRepository);

    @Test
    public void searchProducsByName() {
        String name = "Bike";

        when(productRepository.findByNameContainingIgnoreCase(name)).thenReturn(productsByType);

        assertEquals(productsByTypeDomain, findProductsByNameUseCase.find(name));

    }
}
