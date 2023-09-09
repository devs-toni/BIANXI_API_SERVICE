package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.exception.UserAlreadyExistException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.ports.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        try {
            return userRepositoryPort.findByEmail(user.getEmail());
        } catch (UserNotFoundException e) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepositoryPort.save(user);
        }
    }
}
