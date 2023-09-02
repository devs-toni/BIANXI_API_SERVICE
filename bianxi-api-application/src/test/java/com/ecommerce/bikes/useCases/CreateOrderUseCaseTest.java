package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.OrderMother;
import com.ecommerce.bikes.TestDataHelpers;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.OrderRepositoryPort;
import com.ecommerce.bikes.ports.ProductRepositoryPort;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collections;
import java.util.List;

import static com.ecommerce.bikes.OrderMother.createdOrder;
import static com.ecommerce.bikes.TestDataHelpers.createUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateOrderUseCaseTest {

    private final OrderRepositoryPort orderRepositoryPort = mock(OrderRepositoryPort.class);
    private final UserRepositoryPort userRepositoryPort = mock(UserRepositoryPort.class);
    private final ProductRepositoryPort productRepositoryPort = mock(ProductRepositoryPort.class);
    private final CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(
            orderRepositoryPort,
            userRepositoryPort,
            productRepositoryPort
    );

    @AfterEach
    public void resetMocks() {
        reset(orderRepositoryPort);
        reset(userRepositoryPort);
        reset(productRepositoryPort);
    }

    @Test
    public void create_order() throws UserNotFoundException, ProductNotFoundException {
        Long expectedOrderId = 1L;
        User user = TestDataHelpers.createUser();

        when(productRepositoryPort.findById(1L)).thenReturn(products.get(0));
        when(productRepositoryPort.findById(2L)).thenReturn(products.get(1));
        when(userRepositoryPort.findById(1L)).thenReturn(user);
        when(orderRepositoryPort.save(any())).thenReturn(OrderMother.createdOrder);

        Long orderId = createOrderUseCase.create(productsIds, user.getId(), "address", 5f);

        assertEquals(expectedOrderId, orderId);
    }

    @Test
    public void throw_UserNotFoundException_when_user_does_not_exist() throws UserNotFoundException, ProductNotFoundException {

        when(productRepositoryPort.findById(1L)).thenReturn(products.get(0));
        when(productRepositoryPort.findById(2L)).thenReturn(products.get(1));
        when(userRepositoryPort.findById(1L)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> {
            createOrderUseCase.create(productsIds, 1L, "address", 5f);
        });
    }

    public static List<Long> productsIds = List.of(1L, 2L);
    public static List<Product> products = List.of(
            new Product(
                    1L,
                    "Dummy",
                    "road",
                    0.5f,
                    0,
                    "sentence",
                    "description",
                    Collections.emptySet(),
                    Collections.emptyList(),
                    Collections.emptyList()
            ),
            new Product(
                    2L,
                    "Dummy2",
                    "mtb",
                    2.5f,
                    10,
                    "sentence2",
                    "description2",
                    Collections.emptySet(),
                    Collections.emptyList(),
                    Collections.emptyList()
            )
    );
}
