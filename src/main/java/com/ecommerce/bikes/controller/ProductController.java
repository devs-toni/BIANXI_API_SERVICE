package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.entity.ProductDAO;
import com.ecommerce.bikes.entity.UserDAO;
import com.ecommerce.bikes.http.ProductResponse;
import com.ecommerce.bikes.repository.UserRepository;
import com.ecommerce.bikes.service.ProductService;
import com.ecommerce.bikes.useCases.FindAllProductsByTypeUseCase;
import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    UserRepository userRepository;

    private FindAllProductsByTypeUseCase findAllProductsByTypeUseCase;

    public ProductController(FindAllProductsByTypeUseCase findAllProductsByTypeUseCase) {
        this.findAllProductsByTypeUseCase = findAllProductsByTypeUseCase;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(HttpServletResponse response, @PathVariable Long id) {
        try {
            ProductDAO productDAO = productService.findById(id);
            return new ResponseEntity<>(productDAO, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            System.out.println("Get product by id - " + nsee.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        try {
            List<ProductDAO> productDAOS = productService.findAll();
            return new ResponseEntity<>(productDAOS, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            System.out.println("Get all products - " + nsee.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ProductResponse>> getAllProductsByType(HttpServletResponse response, @PathVariable String type) {
        try {
            List<ProductResponse> products = findAllProductsByTypeUseCase.find(type).stream().map(Product::toResponse).toList();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/favourites/{userId}")
    public ResponseEntity<Object> getFavourites(HttpServletResponse response, @PathVariable long userId) {
        try {
            UserDAO userDAO = userRepository.findById(userId).get();
            List<ProductDAO> productDAOS = userDAO.getLikes().stream().map(like -> like.getProduct()).toList();
            return new ResponseEntity<>(productDAOS, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            System.out.println("Get like products - " + nsee.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Object> searchProductByName(HttpServletResponse response, @PathVariable String name) {
        try {
            List<ProductDAO> productDAOS = productService.findAllProductsByName(name);
            return new ResponseEntity<>(productDAOS, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            System.out.println("Get search products - " + nsee.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    /*
     * LIKES CONTROLLERS
     */

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
