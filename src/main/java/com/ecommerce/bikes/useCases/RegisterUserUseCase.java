package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entity.UserDAO;
import com.ecommerce.bikes.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;

@Service
public class RegisterUserUseCase {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) throws NoSuchElementException {

        try {
            UserDAO userAlreadyExist = userRepository.findByEmail(user.getEmail()).get();
            return UserDAO.toDomain(userAlreadyExist);

        } catch (NoSuchElementException nsee) {

            UserDAO userToSave = User.toEntity(user);
            userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
            UserDAO savedUser = userRepository.save(userToSave);

            return UserDAO.toDomain(savedUser);
        }
    }
}
