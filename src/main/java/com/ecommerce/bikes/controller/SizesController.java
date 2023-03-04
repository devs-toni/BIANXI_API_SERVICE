package com.ecommerce.bikes.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bikes.entity.Size;
import com.ecommerce.bikes.service.ProductService;


@RestController
@RequestMapping("/sizes")
public class SizesController {

	@Autowired
	ProductService productService;

	@GetMapping("/get/all")
	public ResponseEntity<Object> getAllSizes() {
		try {
			List<Size> sizes = productService.findAllSizes();
			return new ResponseEntity<>(sizes, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println(nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
}
