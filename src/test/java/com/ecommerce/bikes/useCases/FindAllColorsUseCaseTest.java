package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.ColorMother;
import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.repository.ColorRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindAllColorsUseCaseTest {

    private ColorRepository colorRepository = mock(ColorRepository.class);

    private FindAllColorsUseCase findAllColorsUseCase = new FindAllColorsUseCase(colorRepository);


    @Test
    public void getColorsDAOandReceiveColorsDomain() {
        when(colorRepository.findAll()).thenReturn(ColorMother.colorsDAO);

        List<Color> colorList = findAllColorsUseCase.get();

        assertEquals(ColorMother.colors, colorList);
    }
}