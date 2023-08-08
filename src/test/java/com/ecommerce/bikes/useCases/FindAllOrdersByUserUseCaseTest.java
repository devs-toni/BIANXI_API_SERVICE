package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.entity.OrderDAO;
import com.ecommerce.bikes.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static com.ecommerce.bikes.OrderMother.order;
import static com.ecommerce.bikes.OrderMother.orderDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindAllOrdersByUserUseCaseTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final FindAllOrdersByUserUseCase findAllOrdersByUserUseCase = new FindAllOrdersByUserUseCase(orderRepository);
    @Test
    public void find_all_by_user() {
        List<OrderDAO> expectedOrders = List.of(orderDAO);
        Long userId = 1L;

        when(orderRepository.findAllByUserId(userId)).thenReturn(expectedOrders);

        List<Order> orders = findAllOrdersByUserUseCase.find(userId);

        assertEquals(order, orders.get(0));
    }
}
