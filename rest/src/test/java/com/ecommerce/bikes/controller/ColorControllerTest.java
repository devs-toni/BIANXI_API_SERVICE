package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.entities.ColorEntity;
import com.ecommerce.bikes.http.ColorResponse;
import com.ecommerce.bikes.useCases.FindAllColorsUseCase;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ColorControllerTest {

    @InjectMocks
    private ColorController colorController;

    private final FindAllColorsUseCase findAllColorsUseCase = mock(FindAllColorsUseCase.class);

    @Test
    public void should_return_all_colors_when_find_all() {
        when(findAllColorsUseCase.find()).thenReturn(colors);

        ResponseEntity<List<ColorResponse>> response = colorController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(colorsResponses, response.getBody());
    }

    protected static final ColorEntity colorEntity = new ColorEntity(1L, "Azul");
    protected static final Color color = ColorEntity.toDomain(colorEntity);
    protected static final List<Color> colors = List.of(color);
    protected static final ColorResponse colorResponse = ColorResponse.toColorResponse(color);
    protected static final List<ColorResponse> colorsResponses = List.of(colorResponse);
}