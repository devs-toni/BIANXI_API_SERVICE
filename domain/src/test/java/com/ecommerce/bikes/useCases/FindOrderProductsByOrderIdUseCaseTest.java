package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.OrderMother;
import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.exception.OrderNotFoundException;
import com.ecommerce.bikes.ports.OrderRepositoryPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.ecommerce.bikes.OrderMother.order;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindOrderProductsByOrderIdUseCaseTest {

    private final OrderRepositoryPort orderRepositoryPort = mock(OrderRepositoryPort.class);
    private final FindOrderProductsByOrderIdUseCase findOrderProductsByOrderIdUseCase = new FindOrderProductsByOrderIdUseCase(orderRepositoryPort);

    @AfterEach
    public void resetMocks() {
        reset(orderRepositoryPort);
    }

    @Test
    public void find_order() throws OrderNotFoundException {

        Long orderId = 1L;

        when(orderRepositoryPort.findById(1L)).thenReturn(order);

        Order order = findOrderProductsByOrderIdUseCase.find(orderId);

        assertEquals(OrderMother.order, order);
    }

    @Test
    public void throw_OrderNotFoundException_when_order_does_not_exist() throws OrderNotFoundException {
        when(orderRepositoryPort.findById(1L)).thenThrow(OrderNotFoundException.class);

        assertThrows(OrderNotFoundException.class, () -> {
            findOrderProductsByOrderIdUseCase.find(1L);
        });
    }
}
