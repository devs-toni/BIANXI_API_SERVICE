package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.http.ColorResponse;
import com.ecommerce.bikes.useCases.FindAllColorsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

    private final FindAllColorsUseCase findAllColorsUseCase;

    public ColorController(FindAllColorsUseCase findAllColorsUseCase) {
        this.findAllColorsUseCase = findAllColorsUseCase;
    }

    @GetMapping
    public ResponseEntity<List<ColorResponse>> findAll() {

        List<ColorResponse> colors = findAllColorsUseCase.find().stream().map(ColorResponse::toColorResponse).toList();
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }
}
