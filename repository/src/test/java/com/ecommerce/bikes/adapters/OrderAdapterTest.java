package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.entities.OrderEntity;
import com.ecommerce.bikes.exception.OrderNotFoundException;
import com.ecommerce.bikes.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.ecommerce.bikes.adapters.UserAdapterTest.user;
import static com.ecommerce.bikes.adapters.UserAdapterTest.userEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderAdapterTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final OrderAdapter orderAdapter = new OrderAdapter(orderRepository);

    @Test
    public void find_by_id() throws OrderNotFoundException {
        when(orderRepository.findById(1L)).thenReturn(Optional.ofNullable(orderEntity));

        assertEquals(order, orderAdapter.findById(1L));
    }

    @Test
    public void find_by_user_id() {
        when(orderRepository.findAllByUserId(1L)).thenReturn(List.of(orderEntity));

        assertEquals(order, orderAdapter.findAllByUserId(1L).get(0));
    }

    @Test
    public void save() {
        when(orderRepository.save(any())).thenReturn(orderEntity);

        assertEquals(order, orderAdapter.save(orderWithoutId));
    }

    public static OrderEntity orderEntity = new OrderEntity(
            1L,
            "address",
            5f,
            userEntity,
            Collections.emptyList()
    );

    public static Order order = new Order(
            1L,
            "address",
            5f,
            user,
            Collections.emptyList()
    );

    public static OrderEntity orderEntityWithoutId = new OrderEntity(
            null,
            "address",
            5f,
            userEntity,
            Collections.emptyList()
    );

    public static Order orderWithoutId = new Order(
            "address",
            5f,
            user,
            Collections.emptyList()
    );
}
