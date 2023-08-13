package com.ecommerce.bikes.repositories;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.entities.SizeEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

// We already have the End-to-End tests in the rest layer

@Disabled
public class SizeRepositoryIT extends DockerConfiguration {

    @Autowired
    private SizeRepository sizeRepository;

    @BeforeAll
    public void prepareTests() {
        sizeRepository.save(new SizeEntity(1L, "XL"));
        sizeRepository.save(new SizeEntity(2L, "L"));
        sizeRepository.save(new SizeEntity(3L, "S"));
        sizeRepository.save(new SizeEntity(4L, "M"));
    }

    @Test
    public void should_return_all_sizes_size() {
        int expectedSize = 4;

        assertEquals(expectedSize, sizeRepository.findAll().size());
    }
}
