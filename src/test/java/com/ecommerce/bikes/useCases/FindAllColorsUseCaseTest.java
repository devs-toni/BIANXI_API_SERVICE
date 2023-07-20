package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.ColorMother;
import com.ecommerce.bikes.repository.ColorRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindAllColorsUseCaseTest {

    private ColorRepository colorRepository = mock(ColorRepository.class);

    private FindAllColorsUseCase findAllColorsUseCase = new FindAllColorsUseCase(colorRepository);


    @Test
    public void getColorsDAOandReceiveColorsDomain() {
        when(colorRepository.findAll()).thenReturn(ColorMother.colorsDAO);

        assertEquals(findAllColorsUseCase.get().get(0), ColorMother.colors.get(0));
    }


}
