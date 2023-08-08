package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.OrderMother;
import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.exception.OrderNotFoundException;
import com.ecommerce.bikes.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Optional;

import static com.ecommerce.bikes.OrderMother.orderDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindOrderByIdUseCaseTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final FindOrderByIdUseCase findOrderByIdUseCase = new FindOrderByIdUseCase(orderRepository);

    @Test
    public void find_order() throws OrderNotFoundException {

        Long orderId = 1L;

        when(orderRepository.findById(1L)).thenReturn(Optional.of(orderDAO));

        Order order = findOrderByIdUseCase.find(orderId);

        assertEquals(OrderMother.order, order);
    }

    @Test
    public void throw_OrderNotFoundException_when_order_does_not_exist() {
        assertThrows(OrderNotFoundException.class, () -> {
            findOrderByIdUseCase.find(1L);
        });
    }
}
