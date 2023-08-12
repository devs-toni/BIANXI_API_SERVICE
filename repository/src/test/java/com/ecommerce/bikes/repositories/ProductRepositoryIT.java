package com.ecommerce.bikes.repositories;

import com.ecommerce.bikes.entities.ProductEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductRepositoryIT extends DockerConfiguration {

    @Autowired
    private ProductRepository productRepository;

    @BeforeAll
    public void prepareTests() {
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }

    @Test
    @Disabled
    public void should_return_product_by_id() {
        ProductEntity product = productRepository.findById(1L).get();

        assertEquals(product1, product);
    }

    @Test
    public void should_return_all_products_when_find_all() {
        int expectedSize = 3;

        List<ProductEntity> products = productRepository.findAll();

        assertEquals(expectedSize, products.size());
    }

    @Test
    public void should_return_all_products_by_type() {
        int expectedSize = 2;

        List<ProductEntity> products = productRepository.findAllByType("road");

        assertEquals(expectedSize, products.size());
    }

    @Test
    public void should_return_all_products_by_name() {
        int expectedSize = 3;

        List<ProductEntity> products = productRepository.findByNameContainingIgnoreCase("dum");

        assertEquals(expectedSize, products.size());
    }

    public static ProductEntity product1 = new ProductEntity(1L, "dummy1", "road", 234f, 10, "sentence1", "desc1", emptySet(), emptyList(), emptyList());
    public static ProductEntity product2 = new ProductEntity(2L, "dummy2", "road", 456f, 20, "sentence2", "desc2", emptySet(), emptyList(), emptyList());
    public static ProductEntity product3 = new ProductEntity(3L, "dummy3", "mtb", 678f, 30, "sentence3", "desc3", emptySet(), emptyList(), emptyList());
}
