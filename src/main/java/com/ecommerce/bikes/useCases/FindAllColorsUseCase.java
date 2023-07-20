package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.entity.ColorDAO;
import com.ecommerce.bikes.repository.ColorRepository;

import java.util.List;

public class FindAllColorsUseCase {

    private ColorRepository colorRepository;

    public FindAllColorsUseCase (ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> get() {
        return colorRepository.findAll().stream().map(ColorDAO::toDomain).toList();
    }
}
