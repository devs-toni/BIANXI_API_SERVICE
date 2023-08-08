package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Size;
import com.ecommerce.bikes.entity.SizeEntity;
import com.ecommerce.bikes.repository.SizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllSizesUseCase {

    private SizeRepository sizeRepository;

    public FindAllSizesUseCase(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public List<Size> find() {
        return sizeRepository.findAll().stream().map(SizeEntity::toDomain).toList();
    }
}
