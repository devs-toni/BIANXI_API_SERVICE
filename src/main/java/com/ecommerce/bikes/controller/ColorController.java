package com.ecommerce.bikes.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import com.ecommerce.bikes.domain.Color;
import com.ecommerce.bikes.useCases.FindAllColorsUseCase;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bikes.entity.ColorDAO;
import com.ecommerce.bikes.service.ProductService;

@RestController
@RequestMapping("/colors")
public class ColorController {

	private ProductService productService;

	private FindAllColorsUseCase findAllColorsUseCase;

	public ColorController (ProductService productService, FindAllColorsUseCase findAllColorsUseCase) {
		this.productService = productService;
		this.findAllColorsUseCase = findAllColorsUseCase;
	}

	@GetMapping("/all")
	public ResponseEntity<Object> getAllColors() {
		try {
			List<Color> colorDAOS = findAllColorsUseCase.get();
			System.err.println("@@@ Get all colors succesfully");
			return new ResponseEntity<>(colorDAOS, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.err.println("Get all colors - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
