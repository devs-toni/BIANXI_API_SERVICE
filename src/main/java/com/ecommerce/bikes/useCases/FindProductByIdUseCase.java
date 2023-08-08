package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.ProductEntity;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class FindProductByIdUseCase {

    private final ProductRepository productRepository;

    public FindProductByIdUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product find(Long id) throws ProductNotFoundException {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("The product does not exist"));
        return ProductEntity.toDomain(productEntity);
    }
}
