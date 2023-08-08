package com.ecommerce.bikes;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entity.ProductEntity;
import com.ecommerce.bikes.entity.UserEntity;

import java.util.Collections;

public class TestDataHelpers {

    public static ProductEntity createProductDAO() {
        return new ProductEntity(
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

    public static UserEntity createUserDAO() {
        return new UserEntity(
                1L,
                "johndoe@doe.com",
                'U',
                "pepe",
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
