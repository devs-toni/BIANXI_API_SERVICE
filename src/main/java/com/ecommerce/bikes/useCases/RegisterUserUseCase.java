package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entity.UserEntity;
import com.ecommerce.bikes.exception.UserAlreadyExistException;
import com.ecommerce.bikes.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegisterUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) throws UserAlreadyExistException {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("This user already exists");

        } else {
            UserEntity userToSave = User.toEntity(user);
            userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
            UserEntity savedUser = userRepository.save(userToSave);

            return UserEntity.toDomain(savedUser);
        }
    }
}
