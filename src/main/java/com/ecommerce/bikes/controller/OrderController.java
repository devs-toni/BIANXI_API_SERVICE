package com.ecommerce.bikes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.bikes.entity.Order;
import com.ecommerce.bikes.entity.Product;
import com.ecommerce.bikes.service.OrdersService;
import com.ecommerce.bikes.service.ProductService;
import com.ecommerce.bikes.service.UserService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrdersService orderService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;

	@PostMapping("/new")
	@ResponseBody
	public ResponseEntity<Long> createOrder(@RequestBody ArrayList<Object> body) {

		try {
			List<Integer> products = (List<Integer>) body.get(0);
			List<Product> arrayList = new ArrayList<>();
			for (Integer product : products) {
				arrayList.add(productService.findById(Long.valueOf(String.valueOf(product))));
			}
			System.out.println(1);
			Long idOrder = orderService.createOrder(arrayList,
					userService.findUserById(Integer.valueOf(String.valueOf(body.get(1)))), (String) body.get(2),
					Float.valueOf(String.valueOf(body.get(3))));
			return new ResponseEntity<>(idOrder, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println("Create and add order - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(-1l, HttpStatus.OK);
		}
	}

	@GetMapping("/get/all/{id}")
	public ResponseEntity<List<Order>> getAllOrdersEntity(@PathVariable("id") Long id) {

		try {
			List<Order> orders = orderService.findAllByUser(id);
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println("Get user orders - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@GetMapping("/get/products/{id}")
	public ResponseEntity<List<Product>> getAllProducts(@PathVariable("id") Long id) {

		try {
			Order order = orderService.findById(id);
			return new ResponseEntity<>(order.getProducts(), HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			System.out.println("Get order products - " + nsee.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

}
