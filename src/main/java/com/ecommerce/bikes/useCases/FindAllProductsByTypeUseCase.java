package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.ProductDAO;
import com.ecommerce.bikes.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FindAllProductsByTypeUseCase {

    private ProductRepository productRepository;

    public FindAllProductsByTypeUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> find(String type) throws NoSuchElementException {
        List<ProductDAO> products = productRepository.findAllByType(type);
        return products.stream().map(ProductDAO::toDomain).toList();
    }
}
