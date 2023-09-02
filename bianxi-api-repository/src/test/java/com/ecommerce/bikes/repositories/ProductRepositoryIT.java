package com.ecommerce.bikes.repositories;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductRepositoryIT extends DockerConfiguration {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void should_return_product_by_id() {
        ProductEntity product = productRepository.findById(1L).get();

        assertNotNull(product);
        assertEquals(productsEntities.get(0).getId(), product.getId());
        assertEquals(productsEntities.get(0).getName(), product.getName());
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

        List<ProductEntity> products = productRepository.findAllByType("mtb");

        assertEquals(expectedSize, products.size());
    }

    @Test
    public void should_return_all_products_by_name() {
        int expectedSize = 3;

        List<ProductEntity> products = productRepository.findByNameContainingIgnoreCase("Meth");

        assertEquals(expectedSize, products.size());
    }

    private static List<ProductEntity> productsEntities = List.of(
            new ProductEntity(
                    1L,
                    "Methanol CV FS 9.3 XT",
                    "road",
                    4707,
                    0,
                    "ULTIMATE CROSS-COUNTRY RACE BIKE",
                    "Bianchi Methanol FS es la joya de doble suspensión de Bianchi. Una btt que te permitirá subir como un cohete y bajar como un rayo, gracias a su geometría renovada y su carbono CV que absorve el 80% de las vibraciones.",
                    emptySet(),
                    emptyList(),
                    emptyList()
            ),
            new ProductEntity(
                    2L,
                    "Methanol CV FS 9.2 XTR",
                    "mtb",
                    6195,
                    0,
                    "ULTIMATE CROSS-COUNTRY RACE BIKE",
                    "Bianchi Methanol FS es la joya de doble suspensión de Bianchi. Una btt que te permitirá subir como un cohete y bajar como un rayo, gracias a su geometría renovada y su carbono CV que absorve el 80% de las vibraciones.",
                    emptySet(),
                    emptyList(),
                    emptyList()
            ),
            new ProductEntity(
                    3L,
                    "Methanol CV FS 9.1 XX1",
                    "mtb",
                    9932,
                    0,
                    "ULTIMATE CROS-COUNTRY RACE BIKE",
                    "Bianchi Methanol FS es la joya de doble suspensión de Bianchi. Una btt que te permitirá subir como un cohete y bajar como un rayo, gracias a su geometría renovada y su carbono CV que absorve el 80% de las vibraciones.",
                    emptySet(),
                    emptyList(),
                    emptyList()
            )
    );
}
