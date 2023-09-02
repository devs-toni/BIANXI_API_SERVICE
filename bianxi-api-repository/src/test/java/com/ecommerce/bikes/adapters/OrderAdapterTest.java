package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.entities.OrderEntity;
import com.ecommerce.bikes.exception.OrderNotFoundException;
import com.ecommerce.bikes.repositories.OrderRepository;
import com.ecommerce.bikes.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.ecommerce.bikes.adapters.UserAdapterTest.user;
import static com.ecommerce.bikes.adapters.UserAdapterTest.userEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderAdapterTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final OrderAdapter orderAdapter = new OrderAdapter(orderRepository, userRepository);

    @AfterEach
    public void resetMocks() {
        reset(orderRepository);
    }

    @Test
    @DisplayName("GIVEN a specific id WHEN order adapter call find order THEN existent order is returned")
    public void should_return_order_when_find_order_that_exists() {
        when(orderRepository.findById(1L)).thenReturn(Optional.ofNullable(orderEntity));

        assertEquals(order, orderAdapter.findById(1L));
    }

    @Test
    @DisplayName("GIVEN a specific id WHEN order adapter call find order THEN throw exception because order doesn't exist")
    public void should_throw_exception_when_find_order_that_does_not_exist() throws OrderNotFoundException {
        when(orderRepository.findById(1L)).thenThrow(OrderNotFoundException.class);

        assertThrows(OrderNotFoundException.class, () -> {
            orderAdapter.findById(1L);
        });
    }

    @Test
    @DisplayName("GIVEN a specific user id WHEN order adapter call find all orders THEN these are returned")
    public void should_return_orders_list_when_find_by_user_id() {
        when(orderRepository.findAllByUserId(1L)).thenReturn(List.of(orderEntity));

        assertEquals(order, orderAdapter.findAllByUserId(1L).get(0));
    }

    @Test
    @DisplayName("GIVEN a new order WHEN order adapter call save THEN new order is created")
    public void should_store_order_when_save() {
        when(orderRepository.save(any())).thenReturn(orderEntity);
        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));

        assertEquals(order, orderAdapter.save(orderWithoutId));
    }

    private static final OrderEntity orderEntity = new OrderEntity(
            1L,
            "address",
            5f,
            userEntity,
            Collections.emptyList()
    );

    private static final Order order = new Order(
            1L,
            "address",
            5f,
            user.getId(),
            Collections.emptyList()
    );

    private static final OrderEntity orderEntityWithoutId = new OrderEntity(
            null,
            "address",
            5f,
            userEntity,
            Collections.emptyList()
    );

    public static final Order orderWithoutId = new Order(
            "address",
            5f,
            user.getId(),
            Collections.emptyList()
    );
}
