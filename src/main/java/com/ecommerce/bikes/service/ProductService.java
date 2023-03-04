package com.ecommerce.bikes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.bikes.entity.Color;
import com.ecommerce.bikes.entity.Product;
import com.ecommerce.bikes.entity.Size;
import com.ecommerce.bikes.entity.User;
import com.ecommerce.bikes.repository.ColorRepository;
import com.ecommerce.bikes.repository.ProductRepository;
import com.ecommerce.bikes.repository.SizesRepository;
import com.ecommerce.bikes.repository.UserRepository;

@Service("ProductService")
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	SizesRepository sizesRepository;
	@Autowired
	ColorRepository colorRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	
	public Product findById(Long id) throws NoSuchElementException{
		return productRepository.findById(id).get();
	}
	
	public List<Product> findAllProductsByType(String type) throws NoSuchElementException{
		return productRepository.findAllByType(type);
	}

	public List<Product> findAllProductsByName(String name) throws NoSuchElementException{
		return productRepository.findByNameContainingIgnoreCase(name);
	}
	
	public List<Size> findAllSizes() {
		return sizesRepository.findAll();
	}

	public List<Color> findAllColors() {
		return colorRepository.findAll();
	}
	
	public boolean verifyUser(String email, String password) throws NoSuchElementException {
		User user = userRepository.findByEmail(email).get();
		System.out.println(user);
		if (encoder.matches(password, user.getPassword())) {
			return true;
		} 
		return false;
	}
	
}
