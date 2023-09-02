package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.ProductMother;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import org.junit.jupiter.api.Test;

import static com.ecommerce.bikes.ProductMother.productsByTypeDomain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindProductsByTypeUseCaseTest {

    private final ProductRepositoryPort productRepositoryPort = mock(ProductRepositoryPort.class);
    private final FindAllProductsByTypeUseCase findAllProductsByTypeUseCase = new FindAllProductsByTypeUseCase(productRepositoryPort);

    @Test
    public void find_all_products_by_type() {
        String type = "road";

        when(productRepositoryPort.findAllByType(type)).thenReturn(ProductMother.productsByTypeDomain);

        assertEquals(ProductMother.productsByTypeDomain.get(0), findAllProductsByTypeUseCase.find(type).get(0));
    }
}
