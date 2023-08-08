package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entity.LikeEntity;
import com.ecommerce.bikes.entity.ProductEntity;
import com.ecommerce.bikes.entity.UserEntity;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import com.ecommerce.bikes.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindFavouritesUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindFavouritesUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public List<Product> find(Long userId) throws UserNotFoundException {
        UserEntity userEntity = userRepositoryPort.findById(userId).orElseThrow(() -> new UserNotFoundException("The user does not exist"));
        User user = UserEntity.toDomain(userEntity);
        List<ProductEntity> products = userEntity.getLikes().stream().map(LikeEntity::getProduct).toList();
        return products.stream().map(ProductEntity::toDomain).toList();
    }
}
