package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entity.LikeDAO;
import com.ecommerce.bikes.entity.ProductDAO;
import com.ecommerce.bikes.entity.UserDAO;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.repository.UserRepository;

import java.util.List;

public class FindFavouritesUseCase {

    private final UserRepository userRepository;

    public FindFavouritesUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Product> find(Long userId) throws UserNotFoundException {
        UserDAO userDAO = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("The user does not exist"));
        User user = UserDAO.toDomain(userDAO);
        List<ProductDAO> products = userDAO.getLikes().stream().map(LikeDAO::getProduct).toList();
        return products.stream().map(ProductDAO::toDomain).toList();
    }
}
