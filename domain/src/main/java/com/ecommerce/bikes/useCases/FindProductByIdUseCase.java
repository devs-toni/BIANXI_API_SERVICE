package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class FindProductByIdUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public FindProductByIdUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public Product find(Long id) throws ProductNotFoundException {
        return productRepositoryPort.findById(id);
    }
}
