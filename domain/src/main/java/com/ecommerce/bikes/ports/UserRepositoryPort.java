package com.ecommerce.bikes.ports;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.UserNotFoundException;

public interface UserRepositoryPort {

    User findByEmail(String email) throws UserNotFoundException;

    User findById(Long userId) throws UserNotFoundException;

    User save(User user);
}
