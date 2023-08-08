package com.ecommerce.bikes.ports;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.exception.ProductNotFoundException;

import java.util.List;

public interface ProductRepositoryPort {


    Product findById(Long id) throws ProductNotFoundException;

    List<Product> findAllByType(String c);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findAll();
}
