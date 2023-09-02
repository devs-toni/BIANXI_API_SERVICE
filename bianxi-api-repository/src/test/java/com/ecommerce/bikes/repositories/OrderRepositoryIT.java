package com.ecommerce.bikes.repositories;

import com.ecommerce.bikes.DockerConfiguration;
import com.ecommerce.bikes.entities.OrderEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderRepositoryIT extends DockerConfiguration {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("GIVEN a specific id WHEN order repository call find order THEN existent order is returned")
    public void should_return_order_by_id() {
        OrderEntity order = orderRepository.findById(1L).get();

        assertNotNull(order);
        assertEquals(1L, order.getId());
        assertEquals(order.getAddress(), order.getAddress());
        assertEquals(order.getPrice(), order.getPrice());
    }

    @Test
    @DisplayName("GIVEN a specific user id WHEN order repository call find all orders THEN these are returned")
    public void should_return_all_orders_by_user_id() {
        List<OrderEntity> orders = orderRepository.findAllByUserId(2L);

        assertEquals(1, orders.size());
    }

    private static OrderEntity order = new OrderEntity(
            1L,
            "C/Muro n3",
            563.25f,
            emptyList()
    );
}
