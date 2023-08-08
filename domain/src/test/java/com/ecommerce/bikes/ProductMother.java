package com.ecommerce.bikes;

import com.ecommerce.bikes.domain.Product;

import java.util.Collections;
import java.util.List;

public class ProductMother {

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
