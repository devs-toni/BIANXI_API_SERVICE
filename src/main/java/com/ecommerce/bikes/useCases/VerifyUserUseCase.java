package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entity.UserDAO;
import com.ecommerce.bikes.exception.UserIsNotValidException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class VerifyUserUseCase {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public VerifyUserUseCase (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User verify(String email, String password) throws UserIsNotValidException, UserNotFoundException {
        User user = UserDAO.toDomain(userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("The user does not exist")));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        throw new UserIsNotValidException("The credentials are not valid");
    }
}
