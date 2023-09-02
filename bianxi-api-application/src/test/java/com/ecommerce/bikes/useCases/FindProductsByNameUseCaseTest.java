package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.ProductMother;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindProductsByNameUseCaseTest {

    private final ProductRepositoryPort productRepositoryPort = mock(ProductRepositoryPort.class);
    private final FindProductsByNameUseCase findProductsByNameUseCase = new FindProductsByNameUseCase(productRepositoryPort);

    @Test
    @DisplayName("WHEN user wants all products within a specific name value THEN these are returned successfully")
    public void search_products_by_name() {

        String name = "Bike";

        when(productRepositoryPort.findByNameContainingIgnoreCase(name)).thenReturn(ProductMother.productsByTypeDomain);

        assertEquals(ProductMother.productsByTypeDomain, findProductsByNameUseCase.find(name));

    }
}
