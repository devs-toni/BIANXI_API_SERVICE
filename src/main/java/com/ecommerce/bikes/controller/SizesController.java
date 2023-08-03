package com.ecommerce.bikes.controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.ecommerce.bikes.domain.Size;
import com.ecommerce.bikes.http.SizeDTO;
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
@RequestMapping("/api/sizes")
public class SizesController {

	private FindAllSizesUseCase findAllSizesUseCase;

	public SizesController(FindAllSizesUseCase findAllSizesUseCase) {
		this.findAllSizesUseCase = findAllSizesUseCase;
	}

	@GetMapping
	public ResponseEntity<List<SizeDTO>> getAllSizes() {
		try {
			List<SizeDTO> sizes = findAllSizesUseCase.get().stream().map(Size::toResponse).toList();
			return new ResponseEntity<>(sizes, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
