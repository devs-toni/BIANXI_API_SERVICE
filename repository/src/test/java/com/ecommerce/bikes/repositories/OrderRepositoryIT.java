package com.ecommerce.bikes.repositories;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.entities.OrderEntity;
import com.ecommerce.bikes.entities.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

// We already have the End-to-End tests in the rest layer

@Disabled
public class OrderRepositoryIT extends DockerConfiguration {

    @Autowired
    private OrderRepository orderRepository;

    @BeforeAll
    public void prepareTests() {
        //orderRepository.save(orderToSave);
        //orderRepository.save(orderToSave2);
    }

    @Test
    public void should_return_order_by_id() {
        OrderEntity order = orderRepository.findById(1L).get();

        assertEquals(orderToSave, order);
    }

    @Test
    public void should_return_all_orders_by_user_id() {
        List<OrderEntity> orders = orderRepository.findAllByUserId(2L);

        assertEquals(2, orders.size());
    }

    private final static UserEntity newUser = new UserEntity("order", 'U', "dummy", Collections.emptyList(), Collections.emptyList());
    private final static OrderEntity orderToSave = new OrderEntity("Dummy", 234, newUser, emptyList());
    private final static OrderEntity orderToSave2 = new OrderEntity("Dummy", 234, newUser, emptyList());


}
