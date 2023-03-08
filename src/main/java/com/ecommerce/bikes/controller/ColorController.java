package com.ecommerce.bikes.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bikes.entity.Color;
import com.ecommerce.bikes.service.ProductService;

@RestController
@RequestMapping("/colors")
public class ColorController {
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping("/get/all")
	public ResponseEntity<Object> getAllColors() {
		try {
			List<Color> colors = productService.findAllColors();
			System.err.println("@@@ Get all colors succesfully");
			return new ResponseEntity<>(colors, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.err.println("Get all colors - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
