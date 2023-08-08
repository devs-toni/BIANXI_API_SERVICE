package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entity.UserEntity;
import com.ecommerce.bikes.exception.UserIsNotValidException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import com.ecommerce.bikes.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class VerifyUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    private final PasswordEncoder passwordEncoder;

    public VerifyUserUseCase (UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    public User verify(String email, String password) throws UserIsNotValidException, UserNotFoundException {
        User user = UserEntity.toDomain(userRepositoryPort.findByEmail(email).orElseThrow(() -> new UserNotFoundException("The user does not exist")));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        throw new UserIsNotValidException("The credentials are not valid");
    }
}
