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
public class FindProductsByTypeUseCaseTest {

    private final ProductRepositoryPort productRepositoryPort = mock(ProductRepositoryPort.class);
    private final FindAllProductsByTypeUseCase findAllProductsByTypeUseCase = new FindAllProductsByTypeUseCase(productRepositoryPort);

    @Test
    @DisplayName("WHEN user wants all products within a specific type value THEN these are returned successfully")
    public void find_all_products_by_type() {
        String type = "road";

        when(productRepositoryPort.findAllByType(type)).thenReturn(ProductMother.productsByTypeDomain);

        assertEquals(ProductMother.productsByTypeDomain.get(0), findAllProductsByTypeUseCase.find(type).get(0));
    }
}
