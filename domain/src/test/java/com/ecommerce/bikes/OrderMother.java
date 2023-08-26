package com.ecommerce.bikes;

import com.ecommerce.bikes.domain.Order;

import static com.ecommerce.bikes.ProductMother.productsByTypeDomain;
import static com.ecommerce.bikes.TestDataHelpers.createUser;

public class OrderMother {

    public static Order order = new Order(
            "direction",
            5f,
            createUser(),
            productsByTypeDomain
    );

    public static Order createdOrder = new Order(
            1L,
            "direction",
            5f,
            createUser(),
            productsByTypeDomain
    );
}
