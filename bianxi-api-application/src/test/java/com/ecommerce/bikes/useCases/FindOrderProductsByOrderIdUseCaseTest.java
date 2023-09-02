package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.OrderMother;
import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.exception.OrderNotFoundException;
import com.ecommerce.bikes.ports.OrderRepositoryPort;
import org.junit.jupiter.api.*;

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
    @DisplayName("WHEN user wants specific order THEN this is returned successfully")
    public void find_order() {

        Long orderId = 1L;

        when(orderRepositoryPort.findById(1L)).thenReturn(OrderMother.order);

        Order order = findOrderProductsByOrderIdUseCase.find(orderId);

        Assertions.assertEquals(OrderMother.order, order);
    }

    @Test
    @DisplayName("WHEN user wants specific order THEN throw exception because order doesn't exist")
    public void throw_OrderNotFoundException_when_order_does_not_exist() {
        when(orderRepositoryPort.findById(1L)).thenThrow(OrderNotFoundException.class);

        assertThrows(OrderNotFoundException.class, () -> {
            findOrderProductsByOrderIdUseCase.find(1L);
        });
    }
}
