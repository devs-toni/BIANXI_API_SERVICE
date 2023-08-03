package com.ecommerce.bikes.service;

import java.util.NoSuchElementException;

import com.ecommerce.bikes.entity.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.bikes.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	
	public UserDAO verifyUser(String email, String password) throws NoSuchElementException {
		UserDAO userDAO = userRepository.findByEmail(email).get();
		if (encoder.matches(password, userDAO.getPassword())) {
			return userDAO;
		}
		throw new NoSuchElementException();
	}

	public UserDAO findUserById(long userId) throws NoSuchElementException {
		return userRepository.findById(userId).get();
	}

	public UserDAO findUserByEmail(String email) throws NoSuchElementException {
		return userRepository.findByEmail(email).get();
	}

	public UserDAO save(UserDAO userDAO) {
		return userRepository.save(userDAO);
	}
}
