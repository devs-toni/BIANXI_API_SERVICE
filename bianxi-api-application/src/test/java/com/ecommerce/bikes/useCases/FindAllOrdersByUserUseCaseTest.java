package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.OrderMother;
import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.ports.OrderRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static com.ecommerce.bikes.OrderMother.order;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindAllOrdersByUserUseCaseTest {

    private final OrderRepositoryPort orderRepositoryPort = mock(OrderRepositoryPort.class);
    private final FindAllOrdersByUserUseCase findAllOrdersByUserUseCase = new FindAllOrdersByUserUseCase(orderRepositoryPort);

    @Test
    public void find_all_by_user() {
        List<Order> expectedOrders = List.of(OrderMother.order);
        Long userId = 1L;

        when(orderRepositoryPort.findAllByUserId(userId)).thenReturn(expectedOrders);

        List<Order> orders = findAllOrdersByUserUseCase.find(userId);

        Assertions.assertEquals(OrderMother.order, orders.get(0));
    }
}
