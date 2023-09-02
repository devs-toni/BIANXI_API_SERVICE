package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.ProductMother;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.ecommerce.bikes.ProductMother.productsByTypeDomain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindAllProductsUseCaseTest {

    private final ProductRepositoryPort productRepositoryPort = mock(ProductRepositoryPort.class);

    private final FindAllProductsUseCase findAllProductsUseCase = new FindAllProductsUseCase(productRepositoryPort);

    @Test
    public void find_all_products() {
        when(productRepositoryPort.findAll()).thenReturn(ProductMother.productsByTypeDomain);

        assertEquals(ProductMother.productsByTypeDomain.get(0), findAllProductsUseCase.find().get(0));
    }
}
