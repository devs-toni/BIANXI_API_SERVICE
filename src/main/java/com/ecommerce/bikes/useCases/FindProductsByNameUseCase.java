package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.ProductDAO;
import com.ecommerce.bikes.repository.ProductRepository;

import java.util.List;

public class FindProductsByNameUseCase {

    private final ProductRepository productRepository;

    public FindProductsByNameUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> find(String name) {
        List<ProductDAO> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream().map(ProductDAO::toDomain).toList();
    }
}
