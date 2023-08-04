package com.ecommerce.bikes;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.ProductDAO;

import java.util.Collections;
import java.util.List;

public class ProductMother {

    public static List<ProductDAO> productsByType = List.of(
            new ProductDAO(
                    1L,
                    "dummy",
                    "road",
                    32.0f,
                    0,
                    "sentence",
                    "description",
                    Collections.emptySet(),
                    Collections.emptyList(),
                    Collections.emptyList()
            )
    );

    public static List<Product> productsByTypeDomain = List.of(
            new Product(
                    1L,
                    "dummy",
                    "road",
                    32.0f,
                    0,
                    "sentence",
                    "description",
                    Collections.emptySet(),
                    Collections.emptyList(),
                    Collections.emptyList()
            )
    );
}
