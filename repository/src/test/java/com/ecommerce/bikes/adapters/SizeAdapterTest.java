package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.domain.Size;
import com.ecommerce.bikes.entities.ColorEntity;
import com.ecommerce.bikes.entities.SizeEntity;
import com.ecommerce.bikes.repositories.ColorRepository;
import com.ecommerce.bikes.repositories.SizeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SizeAdapterTest {

    private final SizeRepository sizeRepository = mock(SizeRepository.class);

    private SizeAdapter sizeAdapter = new SizeAdapter(sizeRepository);

    @Test
    public void find_all() {
        when(sizeRepository.findAll()).thenReturn(sizesEntity);

        List<Size> sizesObtained = sizeAdapter.findAll();

        assertEquals(sizes, sizesObtained);
    }

    public static List<SizeEntity> sizesEntity = List.of(
            new SizeEntity(1L, "L", emptyList())
    );

    public static List<Size> sizes = List.of(
            new Size(1L, "L", emptyList())
    );
}
