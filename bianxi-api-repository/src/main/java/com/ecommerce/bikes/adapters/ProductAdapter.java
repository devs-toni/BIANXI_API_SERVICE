package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entities.ProductEntity;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import com.ecommerce.bikes.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductAdapter implements ProductRepositoryPort {

    private final ProductRepository productRepository;

    public ProductAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) throws ProductNotFoundException {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("The product does not exist"));
        return ProductEntity.toDomain(productEntity);
    }

    @Override
    public List<Product> findAllByType(String type) {
        List<ProductEntity> products = productRepository.findAllByType(type);
        return products.stream().map(ProductEntity::toDomain).toList();
    }

    @Override
    public List<Product> findByNameContainingIgnoreCase(String name) {
        List<ProductEntity> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream().map(ProductEntity::toDomain).toList();
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().map(ProductEntity::toDomain).toList();    }
}
