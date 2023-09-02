package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.entities.ColorEntity;
import com.ecommerce.bikes.repositories.ColorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ColorAdapterTest {

    private final ColorRepository colorRepository = mock(ColorRepository.class);

    private final ColorAdapter colorAdapter = new ColorAdapter(colorRepository);

    @Test
    public void should_return_all_colors() {
        when(colorRepository.findAll()).thenReturn(colorsEntity);

        List<Color> colorsObtained = colorAdapter.findAll();

        assertEquals(colors, colorsObtained);
    }

    private static final List<ColorEntity> colorsEntity = List.of(
            new ColorEntity(1L, "rojo")
    );

    private static final List<Color> colors = List.of(
            new Color(1L, "rojo")
    );
}
