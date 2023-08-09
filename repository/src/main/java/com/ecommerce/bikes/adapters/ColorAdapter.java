package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.entities.ColorEntity;
import com.ecommerce.bikes.ports.ColorRepositoryPort;
import com.ecommerce.bikes.repositories.ColorRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ColorAdapter implements ColorRepositoryPort {

    private final ColorRepository colorRepository;

    public ColorAdapter (ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public List<Color> findAll() {
        List<ColorEntity> colors = colorRepository.findAll();
        return colors.stream().map(ColorEntity::toDomain).toList();
    }
}
