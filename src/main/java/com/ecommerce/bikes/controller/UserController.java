package com.ecommerce.bikes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bikes.entity.User;
import com.ecommerce.bikes.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/verify", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Object>> verifyUser(@RequestBody User user) {
		List<Object> userData = new ArrayList<>();

		try {
			User userVerified = userService.verifyUser(user.getEmail(), user.getPassword());
			userData.add(userVerified.getId());
			userData.add(userVerified.getEmail());
			userData.add(userVerified.getRole());
			System.err.println("@@@User verified succesfully");
			return new ResponseEntity<>(userData, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.err.println("Error when verifyng user - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(userData, HttpStatus.OK);
		}
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<User> saveUser(@RequestBody User user) {

		try {
			User userExistUser = userService.findUserByEmail(user.getEmail());
			System.err.println("@@@User encountered succesfully");
			return new ResponseEntity<>(userExistUser, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			try {
				User userSavedUser = userService.save(user);
				System.err.println("@@@User saved succesfully");
				return new ResponseEntity<>(userSavedUser, HttpStatus.OK);
			} catch (NoSuchElementException e) {
				System.err.println("Error when saving user - " + nsee.getLocalizedMessage());
				return new ResponseEntity<>(null, HttpStatus.OK);
			}
		}

	}
}
