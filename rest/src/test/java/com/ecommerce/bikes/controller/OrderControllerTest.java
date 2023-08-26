package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.Like;
import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.http.OrderResponse;
import com.ecommerce.bikes.http.ProductResponse;
import com.ecommerce.bikes.useCases.CreateOrderUseCase;
import com.ecommerce.bikes.useCases.FindAllOrdersByUserUseCase;
import com.ecommerce.bikes.useCases.FindOrderProductsByOrderIdUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;
    private final FindOrderProductsByOrderIdUseCase findOrderProductsByOrderIdUseCase = mock(FindOrderProductsByOrderIdUseCase.class);
    private final FindAllOrdersByUserUseCase findAllOrdersByUserUseCase = mock(FindAllOrdersByUserUseCase.class);
    private final CreateOrderUseCase createOrderUseCase = mock(CreateOrderUseCase.class);

    @Test
    public void should_return_all_order_products() {
        long orderId = 1L;

        when(findOrderProductsByOrderIdUseCase.find(orderId)).thenReturn(orderTest);

        List<ProductResponse> products = orderController.findAllOrderProducts(orderId).getBody();

        assertEquals(orderTest.getProducts().stream().map(ProductResponse::toProductResponse).toList(), products);
    }

    @Test
    public void should_return_all_user_orders() {
        long userId = 2L;

        when(findAllOrdersByUserUseCase.find(userId)).thenReturn(List.of(orderTest));

        List<OrderResponse> orders = orderController.findAllUserOrders(userId).getBody();

        assertEquals(List.of(OrderResponse.toOrderResponse(orderTest)), orders);
    }

    @Test
    public void should_create_a_new_order() {
        List<Long> productIds = List.of(1L, 2L, 3L);
        long userId = 1L;
        String address = "address";
        float amount = 24.5f;

        when(createOrderUseCase.create(productIds, userId, address, amount)).thenReturn(createdOrder.getId());

        Long orderId = orderController.create(productIds, userId, address, amount).getBody();

        assertEquals(2L, orderId);
    }

    public static User user = new User(1L, "name@example.com", 'U', "$2a$12$tQQVAKT8ELlfD7hKYa8zIOfa7CVvxkFwJT27/cpumVjRFAnHGiRy6", Collections.emptyList(), List.of(new Like(1L, 1L, 1L)));
    public static List<Product> products = List.of(
            new Product(
                    1L,
                    "dummy",
                    "road",
                    32.0f,
                    0,
                    "sentence",
                    "description",
                    Collections.emptySet(),
                    Collections.emptyList(),
                    Collections.emptyList()
            )
    );
    public static Order orderTest = new Order(
            "direction",
            5f,
            user,
            products
    );
    public static Order createdOrder = new Order(
            2L,
            "direction",
            5f,
            user,
            products
    );
}
