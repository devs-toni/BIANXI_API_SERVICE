package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.ColorMother;
import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.ports.ColorRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindAllColorsUseCaseTest {

    private final ColorRepositoryPort colorRepositoryPort = mock(ColorRepositoryPort.class);

    private final FindAllColorsUseCase findAllColorsUseCase = new FindAllColorsUseCase(colorRepositoryPort);


    @Test
    @DisplayName("WHEN user wants all the colors THEN all colors are returned successfully")
    public void find_all_colors() {
        when(colorRepositoryPort.findAll()).thenReturn(ColorMother.colors);

        List<Color> colorList = findAllColorsUseCase.find();

        Assertions.assertEquals(ColorMother.colors, colorList);
    }
}
