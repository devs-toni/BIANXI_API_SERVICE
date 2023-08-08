package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.entity.OrderDAO;
import com.ecommerce.bikes.entity.ProductDAO;
import com.ecommerce.bikes.repository.ProductRepository;
import com.ecommerce.bikes.repository.UserRepository;
import com.ecommerce.bikes.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrdersService orderService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/new")
    @ResponseBody
    public ResponseEntity<Long> createOrder(@RequestBody ArrayList<Object> body) {

        try {
            List<Integer> products = (List<Integer>) body.get(0);
            List<ProductDAO> arrayList = new ArrayList<>();
            for (Integer product : products) {
                arrayList.add(productRepository.findById(Long.valueOf(String.valueOf(product))).get());
            }
            Long idOrder = orderService.createOrder(
                    arrayList,
                    userRepository.findById(Integer.valueOf(String.valueOf(body.get(1)))).get(),
                    (String) body.get(2),
                    Float.valueOf(String.valueOf(body.get(3))));
            return new ResponseEntity<>(idOrder, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            System.out.println("Create and add order - " + nsee.getLocalizedMessage());
            return new ResponseEntity<>(-1l, HttpStatus.OK);
        }
    }

    @GetMapping("/get/all/{id}")
    public ResponseEntity<List<OrderDAO>> getAllOrdersEntity(@PathVariable("id") Long id) {

        try {
            List<OrderDAO> orderDAOS = orderService.findAllByUser(id);
            return new ResponseEntity<>(orderDAOS, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            System.out.println("Get user orders - " + nsee.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/get/products/{id}")
    public ResponseEntity<List<ProductDAO>> getAllProducts(@PathVariable("id") Long id) {

        try {
            OrderDAO orderDAO = orderService.findById(id);
            return new ResponseEntity<>(orderDAO.getProducts(), HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            System.out.println("Get order products - " + nsee.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
