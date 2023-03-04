package com.ecommerce.bikes.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bikes.entity.User;
import com.ecommerce.bikes.service.ProductService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/verify", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Boolean> verifyUser(@RequestBody User user) {
		try {
			boolean response = productService.verifyUser(user.getEmail(), user.getPassword());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println(nsee.getLocalizedMessage());
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}
}
