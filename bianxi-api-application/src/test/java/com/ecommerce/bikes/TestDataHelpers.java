package com.ecommerce.bikes;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.domain.User;

import java.util.Collections;

public class TestDataHelpers {

    public static Product createProduct() {
        return new Product(
                1L,
                "Dummy",
                "road",
                0.5f,
                0,
                "sentence",
                "description",
                Collections.emptySet(),
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    public static User createUser() {
        return new User(
                1L,
                "johndoe@doe.com",
                'U',
                "pepe",
                Collections.emptyList(),
                Collections.emptyList()
        );
    }
}
