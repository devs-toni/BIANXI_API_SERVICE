package com.ecommerce.bikes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bikes.entity.Product;
import com.ecommerce.bikes.entity.User;
import com.ecommerce.bikes.service.ProductService;
import com.ecommerce.bikes.service.UserService;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;

	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getProductById(HttpServletResponse response, @PathVariable Long id) {
		try {
			Product product = productService.findById(id);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println("Get product by id - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/get/all")
	public ResponseEntity<Object> getAllProducts() {
		try {
			List<Product> products = productService.findAll();
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println("Get all products - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/get/type/{type}")
	public ResponseEntity<Object> getAllProductsByType(HttpServletResponse response, @PathVariable String type) {
		try {
			List<Product> products = productService.findAllProductsByType(type);
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println("Get product by type - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/get/favourites/{userId}")
	public ResponseEntity<Object> getFavourites(HttpServletResponse response, @PathVariable long userId) {
		try {
			User user = userService.findUserById(userId);
			List<Product> products =  user.getLikes().stream().map(like -> like.getProduct()).toList();
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println("Get like products - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/search/{name}")
	public ResponseEntity<Object> searchProductByName(HttpServletResponse response, @PathVariable String name) {
		try {
			List<Product> products = productService.findAllProductsByName(name);
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println("Get search products - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/like/add")
	@ResponseBody
	public ResponseEntity<Object> addLike(@RequestBody ArrayList<Integer> data, HttpServletResponse response) {
		try {
			int result = productService.insertLike(data.get(0), data.get(1));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println("Add like - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(-1, HttpStatus.OK);
		}
	}
	
	@PostMapping("/like/get")
	@ResponseBody
	public ResponseEntity<Object> getLike(@RequestBody ArrayList<Integer> data, HttpServletResponse response) {
		try {
			productService.getLike(data.get(0), data.get(1));
			return new ResponseEntity<>(1, HttpStatus.OK);
		} catch (NoSuchElementException | NoResultException exc) {
			System.out.println("Get like - " + exc.getLocalizedMessage());
			return new ResponseEntity<>(0, HttpStatus.OK);
		}
	}

	@DeleteMapping("/like/delete")
	@ResponseBody
	public ResponseEntity<Object> deleteLik(@RequestBody ArrayList<Integer> data, HttpServletResponse response) {
		try {
			int result = productService.deleteLike(data.get(0), data.get(1));
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println("Delete like - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(-1, HttpStatus.OK);
		}
	}
}
