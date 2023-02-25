package com.ecommerce.bikes.controller;

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
	public ResponseEntity<Object> getProductById (HttpServletResponse r, @PathVariable Long id) {
		try {
			Product product = productService.findById(id);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println(nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
