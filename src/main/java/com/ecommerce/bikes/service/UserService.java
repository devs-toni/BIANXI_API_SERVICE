package com.ecommerce.bikes.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.bikes.entity.User;
import com.ecommerce.bikes.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	
	public User verifyUser(String email, String password) throws NoSuchElementException {
		User user = userRepository.findByEmail(email).get();
		if (encoder.matches(password, user.getPassword())) {
			return user;
		}
		throw new NoSuchElementException();
	}

	public User findUserById(long userId) throws NoSuchElementException {
		return userRepository.findById(userId).get();
	}

	public User findUserByEmail(String email) throws NoSuchElementException {
		return userRepository.findByEmail(email).get();
	}

	public User save(User user) {
		return userRepository.save(user);
	}
}
