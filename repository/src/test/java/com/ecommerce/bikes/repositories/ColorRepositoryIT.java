package com.ecommerce.bikes.repositories;


import com.ecommerce.bikes.entities.ColorEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorRepositoryIT extends DockerConfiguration {

    @Autowired
    private ColorRepository colorRepository;

    @BeforeAll
    public void prepareTests() {
        colorRepository.save(new ColorEntity(1L, "Rojo"));
        colorRepository.save(new ColorEntity(2L, "Azul"));
    }

    @Test
    public void should_return_all_colors_size() {
            Integer expectedSize = 2;

        assertEquals(expectedSize, colorRepository.findAll().size());
    }
}
