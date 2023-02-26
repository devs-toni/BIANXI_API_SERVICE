package com.ecommerce.bikes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.bikes.entity.Product;
import com.ecommerce.bikes.repository.ProductRepository;

@Service("ProductService")
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public Product findById(Long id) throws NoSuchElementException{
		return productRepository.findById(id).get();
	}
	
	public List<Product> findAllByType(String type) throws NoSuchElementException{
		return productRepository.findAllByType(type).get();
	}
}
