package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.ProductDAO;
import com.ecommerce.bikes.repository.ProductRepository;

import java.util.List;

public class FindAllProductsUseCase {

    private ProductRepository productRepository;

    public FindAllProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> find() {
        return productRepository.findAll().stream().map(ProductDAO::toDomain).toList();
    }
}
