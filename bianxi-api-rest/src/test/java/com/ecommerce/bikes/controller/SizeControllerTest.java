package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.Size;
import com.ecommerce.bikes.entities.SizeEntity;
import com.ecommerce.bikes.http.SizeResponse;
import com.ecommerce.bikes.useCases.FindAllSizesUseCase;
import org.junit.jupiter.api.DisplayName;
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class SizeControllerTest {

    @InjectMocks
    private SizeController sizeController;

    private FindAllSizesUseCase findAllSizesUseCase = mock(FindAllSizesUseCase.class);

    @Test
    @DisplayName("WHEN user tries to get all sizes THEN these are returned")
    public void should_return_all_sizes_when_find_all() {
        when(findAllSizesUseCase.find()).thenReturn(sizes);

        ResponseEntity<List<SizeResponse>> response = sizeController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sizesResponses, response.getBody());
    }

    protected static SizeEntity sizeEntity = new SizeEntity(1L, "XL");
    protected static Size size = SizeEntity.toDomain(sizeEntity);
    protected static List<Size> sizes = List.of(size);
    protected static SizeResponse sizeResponse = SizeResponse.toSizeResponse(size);
    protected static List<SizeResponse> sizesResponses = List.of(sizeResponse);
}
