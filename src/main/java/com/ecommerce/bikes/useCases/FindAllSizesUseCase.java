package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Size;
import com.ecommerce.bikes.entity.SizeDAO;
import com.ecommerce.bikes.repository.SizeRepository;

import java.util.List;

public class FindAllSizesUseCase {

    private SizeRepository sizeRepository;

    public FindAllSizesUseCase (SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    public List<Size> get() {
        return sizeRepository.findAll().stream().map(SizeDAO::toDomain).toList();
    }
}
