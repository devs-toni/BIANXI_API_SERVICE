package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.entity.ColorDAO;
import com.ecommerce.bikes.repository.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllColorsUseCase {

    private final ColorRepository colorRepository;

    public FindAllColorsUseCase (ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> find() {
        return colorRepository.findAll().stream().map(ColorDAO::toDomain).toList();
    }
}
