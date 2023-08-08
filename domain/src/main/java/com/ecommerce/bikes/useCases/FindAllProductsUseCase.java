package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllProductsUseCase {

    private ProductRepositoryPort productRepositoryPort;

    public FindAllProductsUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public List<Product> find() {
        return productRepositoryPort.findAll();
    }
}
