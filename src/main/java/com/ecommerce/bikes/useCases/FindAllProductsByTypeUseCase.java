package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.ProductEntity;
import com.ecommerce.bikes.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllProductsByTypeUseCase {

    private final ProductRepository productRepository;

    public FindAllProductsByTypeUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> find(String type) {
        List<ProductEntity> products = productRepository.findAllByType(type);
        return products.stream().map(ProductEntity::toDomain).toList();
    }
}
