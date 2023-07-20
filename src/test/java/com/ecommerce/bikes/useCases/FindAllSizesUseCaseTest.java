package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.SizeMother;
import com.ecommerce.bikes.repository.SizeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindAllSizesUseCaseTest {


    private SizeRepository sizeRepository = mock(SizeRepository.class);

    private FindAllSizesUseCase findAllSizesUseCase = new FindAllSizesUseCase(sizeRepository);


    @Test
    public void get_SizesDAO_and_Receive_Sizes_Domain() {
        when(sizeRepository.findAll()).thenReturn(SizeMother.sizesDAO);

        assertEquals(findAllSizesUseCase.get().get(0), SizeMother.sizes.get(0));
    }

}
