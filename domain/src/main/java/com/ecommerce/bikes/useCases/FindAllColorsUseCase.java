package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.ports.ColorRepositoryPort;
import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.entity.ColorEntity;
import com.ecommerce.bikes.repository.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllColorsUseCase {

    private final ColorRepositoryPort colorRepositoryPort;

    public FindAllColorsUseCase (ColorRepositoryPort colorRepositoryPort) {
        this.colorRepositoryPort = colorRepositoryPort;
    }

    public List<Color> find() {
        return colorRepositoryPort.findAll().stream().map(ColorEntity::toDomain).toList();
    }
}
