package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.Size;
import com.ecommerce.bikes.http.SizeResponse;
import com.ecommerce.bikes.useCases.FindAllSizesUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/sizes")
public class SizesController {

    private final FindAllSizesUseCase findAllSizesUseCase;

    public SizesController(FindAllSizesUseCase findAllSizesUseCase) {
        this.findAllSizesUseCase = findAllSizesUseCase;
    }

    @GetMapping
    public ResponseEntity<List<SizeResponse>> findAll() {

        List<SizeResponse> sizes = findAllSizesUseCase.find().stream().map(SizeResponse::toSizeResponse).toList();
        return new ResponseEntity<>(sizes, HttpStatus.OK);
    }
}
