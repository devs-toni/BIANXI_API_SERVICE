package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.SizeMother;
import com.ecommerce.bikes.domain.Size;
import com.ecommerce.bikes.ports.SizeRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindAllSizesUseCaseTest {


    private final SizeRepositoryPort sizeRepositoryPort = mock(SizeRepositoryPort.class);

    private final FindAllSizesUseCase findAllSizesUseCase = new FindAllSizesUseCase(sizeRepositoryPort);


    @Test
    public void find_all_sizes() {
        when(sizeRepositoryPort.findAll()).thenReturn(SizeMother.sizes);

        List<Size> sizeList = findAllSizesUseCase.find();

        Assertions.assertEquals(SizeMother.sizes, sizeList);
    }

}
