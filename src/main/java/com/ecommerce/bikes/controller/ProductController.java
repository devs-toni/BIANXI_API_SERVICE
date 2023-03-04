package com.ecommerce.bikes.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bikes.entity.Product;
import com.ecommerce.bikes.service.ProductService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getProductById (HttpServletResponse response, @PathVariable Long id) {
		try {
			Product product = productService.findById(id);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println(nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/get/type/{type}")
	public ResponseEntity<Object> getAllProductsByType (HttpServletResponse response, @PathVariable String type) {
		try {
			List<Product> products = productService.findAllProductsByType(type);
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println(nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<Object> searchProductByName (HttpServletResponse response, @PathVariable String name) {
		try {
			List<Product> products = productService.findAllProductsByName(name);
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println(nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
