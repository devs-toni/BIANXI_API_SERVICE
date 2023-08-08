package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.ColorMother;
import com.ecommerce.bikes.ports.ColorRepositoryPort;
import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.repository.ColorRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindAllColorsUseCaseTest {

    private final ColorRepositoryPort colorRepositoryPort = mock(ColorRepositoryPort.class);

    private final FindAllColorsUseCase findAllColorsUseCase = new FindAllColorsUseCase(colorRepositoryPort);


    @Test
    public void find_all_colors() {
        when(colorRepositoryPort.findAll()).thenReturn(ColorMother.colorsDAO);

        List<Color> colorList = findAllColorsUseCase.find();

        assertEquals(ColorMother.colors, colorList);
    }
}
