package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.ProductDAO;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.repository.ProductRepository;

public class FindProductByIdUseCase {

    private final ProductRepository productRepository;

    public FindProductByIdUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product find(Long id) throws ProductNotFoundException {
        ProductDAO productDAO = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("The product does not exist"));
        return ProductDAO.toDomain(productDAO);
    }
}
