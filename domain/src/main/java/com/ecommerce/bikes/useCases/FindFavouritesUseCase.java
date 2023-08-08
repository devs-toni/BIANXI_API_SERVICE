package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Like;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindFavouritesUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindFavouritesUseCase(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public List<Product> find(Long userId) throws UserNotFoundException {
        User user = userRepositoryPort.findById(userId);
        return user.getLikes().stream().map(Like::getProduct).toList();
    }
}
