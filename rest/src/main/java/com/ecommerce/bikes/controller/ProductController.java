package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.exception.ProductNotFoundException;
import com.ecommerce.bikes.exception.UserNotFoundException;
import com.ecommerce.bikes.http.ProductResponse;
import com.ecommerce.bikes.useCases.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final FindAllProductsByTypeUseCase findAllProductsByTypeUseCase;
    private final FindProductByIdUseCase findProductByIdUseCase;
    private final FindAllProductsUseCase findAllProductsUseCase;
    private final FindProductsByNameUseCase findProductsByNameUseCase;
    private final FindFavouritesUseCase findFavouritesUseCase;
    private final InsertLikeUseCase insertLikeUseCase;
    private final GetLikeUseCase getLikeUseCase;
    private final DeleteLikeUseCase deleteLikeUseCase;

    public ProductController(
            FindAllProductsByTypeUseCase findAllProductsByTypeUseCase,
            FindProductByIdUseCase findProductByIdUseCase,
            FindAllProductsUseCase findAllProductsUseCase,
            FindProductsByNameUseCase findProductsByNameUseCase,
            FindFavouritesUseCase findFavouritesUseCase,
            InsertLikeUseCase insertLikeUseCase,
            GetLikeUseCase getLikeUseCase,
            DeleteLikeUseCase deleteLikeUseCase
    ) {
        this.findAllProductsByTypeUseCase = findAllProductsByTypeUseCase;
        this.findProductByIdUseCase = findProductByIdUseCase;
        this.findAllProductsUseCase = findAllProductsUseCase;
        this.findProductsByNameUseCase = findProductsByNameUseCase;
        this.findFavouritesUseCase = findFavouritesUseCase;
        this.insertLikeUseCase = insertLikeUseCase;
        this.getLikeUseCase = getLikeUseCase;
        this.deleteLikeUseCase = deleteLikeUseCase;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id) {
        try {
            Product product = findProductByIdUseCase.find(id);
            return new ResponseEntity<>(ProductResponse.toProductResponse(product), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {

        List<Product> products = findAllProductsUseCase.find();
        return new ResponseEntity<>(products.stream().map(ProductResponse::toProductResponse).toList(), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ProductResponse>> findAllByType(@PathVariable String type) {

        List<Product> products = findAllProductsByTypeUseCase.find(type);
        return new ResponseEntity<>(products.stream().map(ProductResponse::toProductResponse).toList(), HttpStatus.OK);
    }

    @GetMapping("/favourites/{userId}")
    public ResponseEntity<List<ProductResponse>> findFavourites(@PathVariable Long userId) {
        try {
            List<Product> favourites = findFavouritesUseCase.find(userId);
            return new ResponseEntity<>(favourites.stream().map(ProductResponse::toProductResponse).toList(), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductResponse>> findByName(@PathVariable String name) {

        List<Product> products = findProductsByNameUseCase.find(name);
        return new ResponseEntity<>(products.stream().map(ProductResponse::toProductResponse).toList(), HttpStatus.OK);
    }

    @GetMapping("/likes/{productId}/{userId}")
    @ResponseBody
    public ResponseEntity<Integer> getLike(@PathVariable Long productId, @PathVariable Long userId) {

        getLikeUseCase.get(productId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/likes/{productId}/{userId}")
    @ResponseBody
    public ResponseEntity<Integer> addLike(@PathVariable Long productId, @PathVariable Long userId) {

        insertLikeUseCase.add(productId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/likes/{productId}/{userId}")
    @ResponseBody
    public ResponseEntity<Integer> deleteLike(@PathVariable Long productId, @PathVariable Long userId) {

        deleteLikeUseCase.delete(productId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
