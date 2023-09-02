package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.Size;
import com.ecommerce.bikes.entities.SizeEntity;
import com.ecommerce.bikes.repositories.SizeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SizeAdapterTest {

    private final SizeRepository sizeRepository = mock(SizeRepository.class);

    private final SizeAdapter sizeAdapter = new SizeAdapter(sizeRepository);

    @Test
    @DisplayName("WHEN size adapter call find all sizes THEN all sizes are returned")
    public void should_return_all_sizes() {
        when(sizeRepository.findAll()).thenReturn(sizesEntity);

        List<Size> sizesObtained = sizeAdapter.findAll();

        assertEquals(sizes, sizesObtained);
    }

    private static final List<SizeEntity> sizesEntity = List.of(
            new SizeEntity(1L, "L")
    );

    public static final List<Size> sizes = List.of(
            new Size(1L, "L")
    );
}
