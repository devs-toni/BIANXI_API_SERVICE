package com.ecommerce.bikes;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.entity.OrderDAO;

import static com.ecommerce.bikes.ProductMother.productsByType;
import static com.ecommerce.bikes.ProductMother.productsByTypeDomain;
import static com.ecommerce.bikes.TestDataHelpers.createUserDAO;

public class OrderMother {

    public static OrderDAO orderDAO = new OrderDAO(
            "direction",
            5f,
            createUserDAO(),
            productsByType
    );

    public static Order order = new Order(
            "direction",
            5f,
            productsByTypeDomain
    );
}
