package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllProductsByTypeUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public FindAllProductsByTypeUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public List<Product> find(String type) {
        return productRepositoryPort.findAllByType(type);
    }
}
