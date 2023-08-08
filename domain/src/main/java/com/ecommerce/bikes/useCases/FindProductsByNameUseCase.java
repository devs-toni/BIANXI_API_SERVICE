package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.ProductEntity;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import com.ecommerce.bikes.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindProductsByNameUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public FindProductsByNameUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public List<Product> find(String name) {
        List<ProductEntity> products = productRepositoryPort.findByNameContainingIgnoreCase(name);
        return products.stream().map(ProductEntity::toDomain).toList();
    }
}
