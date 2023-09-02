package com.ecommerce.bikes.repositories;


import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.entities.ColorEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ColorRepositoryIT extends DockerConfiguration {

    @Autowired
    private ColorRepository colorRepository;

    @Test
    @DisplayName("WHEN color repository call find all colors THEN these are returned")
    public void should_return_all_colors_size() {
        int expectedSize = 3;

        List<ColorEntity> colors = colorRepository.findAll();

        assertEquals(expectedSize, colors.size());
    }
}
