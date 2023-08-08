package com.ecommerce.bikes.adapters;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entities.UserEntity;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import com.ecommerce.bikes.repositories.UserRepository;

public class UserAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    public UserAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("The user does not exist"));
        return UserEntity.toDomain(userEntity);
    }

    @Override
    public User findById(Long userId) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("The user does not exist"));
        return UserEntity.toDomain(userEntity);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserEntity.toEntity(user);
        UserEntity userSavedEntitiy = userRepository.save(userEntity);
        return UserEntity.toDomain(userSavedEntitiy);
    }
}
