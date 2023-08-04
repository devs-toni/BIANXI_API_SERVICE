package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import static com.ecommerce.bikes.ProductMother.productsByType;
import static com.ecommerce.bikes.ProductMother.productsByTypeDomain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindProductsByTypeUseCaseTest {

    private ProductRepository productRepository = mock(ProductRepository.class);
    private FindAllProductsByTypeUseCase findAllProductsByTypeUseCase = new FindAllProductsByTypeUseCase(productRepository);

    @Test
    public void findAllProductsBySpecificType() {
        String type = "road";

        when(productRepository.findAllByType(type)).thenReturn(productsByType);

        assertEquals(productsByTypeDomain.get(0), findAllProductsByTypeUseCase.find(type).get(0));
    }
}
