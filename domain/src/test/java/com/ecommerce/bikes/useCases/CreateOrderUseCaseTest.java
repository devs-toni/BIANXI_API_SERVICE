package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.entity.OrderEntity;
import com.ecommerce.bikes.entity.ProductEntity;
import com.ecommerce.bikes.entity.UserEntity;
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
        UserEntity userEntity = createUserDAO();
        OrderEntity orderEntity = new OrderEntity(null, "address", 5f, userEntity, products);
        OrderEntity savedOrderEntity = new OrderEntity(1L, "address", 5f, userEntity, products);

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(products.get(0)));
        when(productRepository.findById(2L)).thenReturn(Optional.ofNullable(products.get(1)));
        when(userRepository.findById(userEntity.getId())).thenReturn(Optional.of(userEntity));
        when(orderRepository.save(any())).thenReturn(savedOrderEntity);

        Long orderId = createOrderUseCase.create(productsIds, userEntity.getId(), "address", 5f);

        assertEquals(expectedOrderId, orderId);
    }

    @Test
    public void throw_UserNotFoundException_when_user_does_not_exist() throws UserNotFoundException {
        Long expectedId = 1L;
        OrderEntity orderEntity = new OrderEntity(1L, "address", 5f, products);

        when(productRepository.findById(1L)).thenReturn(Optional.ofNullable(products.get(0)));
        when(productRepository.findById(2L)).thenReturn(Optional.ofNullable(products.get(1)));

        assertThrows(UserNotFoundException.class, () -> {
            createOrderUseCase.create(productsIds, 1L, "address", 5f);
        });
    }

    public static List<Long> productsIds = List.of(1L, 2L);
    public static List<ProductEntity> products = List.of(
            new ProductEntity(
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
            new ProductEntity(
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
