package com.ecommerce.bikes.repositories;

import com.ecommerce.bikes.DockerConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SizeRepositoryIT extends DockerConfiguration {

    @Autowired
    private SizeRepository sizeRepository;

    @Test
    public void should_return_all_sizes_size() {
        int expectedSize = 3;

        assertEquals(expectedSize, sizeRepository.findAll().size());
    }
}
