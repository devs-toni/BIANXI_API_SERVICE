package com.ecommerce.bikes.controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.ecommerce.bikes.domain.Size;
import com.ecommerce.bikes.useCases.FindAllSizesUseCase;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bikes.entity.SizeDAO;
import com.ecommerce.bikes.service.ProductService;


@RestController
@RequestMapping("/sizes")
public class SizesController {

	@Autowired
	ProductService productService;

	private FindAllSizesUseCase findAllSizesUseCase;

	public SizesController(FindAllSizesUseCase findAllSizesUseCase) {
		this.findAllSizesUseCase = findAllSizesUseCase;
	}

	@GetMapping("/get/all")
	public ResponseEntity<Object> getAllSizes() {
		try {
			List<Size> sizes = findAllSizesUseCase.get();
			System.err.println("@@@ Get all sizes succesfully");
			return new ResponseEntity<>(sizes, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.err.println("Get all sizes - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
}
