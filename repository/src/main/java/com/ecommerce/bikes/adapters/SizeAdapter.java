package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.Size;
import com.ecommerce.bikes.entities.SizeEntity;
import com.ecommerce.bikes.ports.SizeRepositoryPort;
import com.ecommerce.bikes.repositories.SizeRepository;

import java.util.List;

public class SizeAdapter implements SizeRepositoryPort {

    private final SizeRepository sizeRepository;

    public SizeAdapter(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public List<Size> findAll() {
        List<SizeEntity> sizes = sizeRepository.findAll();
        return sizes.stream().map(SizeEntity::toDomain).toList();
    }
}