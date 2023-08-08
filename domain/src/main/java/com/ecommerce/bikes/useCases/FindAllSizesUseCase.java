package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Size;
import com.ecommerce.bikes.entity.SizeEntity;
import com.ecommerce.bikes.ports.SizeRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllSizesUseCase {

    private SizeRepositoryPort sizeRepositoryPort;

    public FindAllSizesUseCase(SizeRepositoryPort sizeRepositoryPort) {
        this.sizeRepositoryPort = sizeRepositoryPort;
    }

    public List<Size> find() {
        return sizeRepositoryPort.findAll().stream().map(SizeEntity::toDomain).toList();
    }
}
