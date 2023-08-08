package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.entity.OrderDAO;
import com.ecommerce.bikes.entity.ProductDAO;
import com.ecommerce.bikes.entity.UserDAO;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.repository.OrderRepository;
import com.ecommerce.bikes.repository.ProductRepository;
import com.ecommerce.bikes.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.ecommerce.bikes.TestDataHelpers.createUserDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateOrderUseCaseTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(
            orderRepository,
            userRepository,
            productRepository
    );

    @Test
    public void create_order() throws UserNotFoundException {
        Long expectedOrderId = 1L;
        UserDAO userDAO = createUserDAO();
        OrderDAO orderDAO = new OrderDAO(null, "address", 5f, userDAO, products);
        OrderDAO savedOrderDAO = new OrderDAO(1L, "address", 5f, userDAO, products);

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(products.get(0)));
        when(productRepository.findById(2L)).thenReturn(Optional.ofNullable(products.get(1)));
        when(userRepository.findById(userDAO.getId())).thenReturn(Optional.of(userDAO));
        when(orderRepository.save(any())).thenReturn(savedOrderDAO);

        Long orderId = createOrderUseCase.create(productsIds, userDAO.getId(), "address", 5f);

        assertEquals(expectedOrderId, orderId);
    }

    @Test
    public void throw_UserNotFoundException_when_user_does_not_exist() throws UserNotFoundException {
        Long expectedId = 1L;
        OrderDAO orderDAO = new OrderDAO(1L, "address", 5f, products);

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(products.get(0)));
        when(productRepository.findById(2L)).thenReturn(Optional.ofNullable(products.get(1)));

        assertThrows(UserNotFoundException.class, () -> {
            createOrderUseCase.create(productsIds, 1L, "address", 5f);
        });
    }

    public static List<Long> productsIds = List.of(1L, 2L);
    public static List<ProductDAO> products = List.of(
            new ProductDAO(
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
            new ProductDAO(
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
