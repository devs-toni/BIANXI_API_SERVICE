package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.ProductEntity;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import com.ecommerce.bikes.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class FindProductByIdUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public FindProductByIdUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public Product find(Long id) throws ProductNotFoundException {
        ProductEntity productEntity = productRepositoryPort.findById(id).orElseThrow(() -> new ProductNotFoundException("The product does not exist"));
        return ProductEntity.toDomain(productEntity);
    }
}
