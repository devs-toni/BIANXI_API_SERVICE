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

	public UserDAO findUserById(long userId) throws NoSuchElementException {
		return userRepository.findById(userId).get();
	}
}
