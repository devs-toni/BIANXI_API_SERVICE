package com.ecommerce.bikes.ports;

import com.ecommerce.bikes.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {


    Optional<Product> findById(Long id);

    List<Product> findAllByType(String c);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findAll();
}
