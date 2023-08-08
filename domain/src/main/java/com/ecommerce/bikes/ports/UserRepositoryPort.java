package com.ecommerce.bikes.ports;

import com.ecommerce.bikes.domain.User;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findByEmail(String email) throws NoSuchElementException;

    Optional<User> findById(long userId) throws NoSuchElementException;
}
