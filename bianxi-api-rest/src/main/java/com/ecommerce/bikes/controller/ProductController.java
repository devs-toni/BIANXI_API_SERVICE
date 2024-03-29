package com.ecommerce.bikes.controller;

import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.exception.ErrorResponse;
import com.ecommerce.bikes.exception.LikeDoesNotExistResultException;
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
        Product product = findProductByIdUseCase.find(id);
        return new ResponseEntity<>(ProductResponse.toProductResponse(product), HttpStatus.OK);
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
        List<Product> favourites = findFavouritesUseCase.find(userId);
        return new ResponseEntity<>(favourites.stream().map(ProductResponse::toProductResponse).toList(), HttpStatus.OK);
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
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @PostMapping("/likes/{productId}/{userId}")
    @ResponseBody
    public ResponseEntity<Integer> addLike(@PathVariable Long productId, @PathVariable Long userId) {

        insertLikeUseCase.add(productId, userId);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @DeleteMapping("/likes/{productId}/{userId}")
    @ResponseBody
    public ResponseEntity<Integer> deleteLike(@PathVariable Long productId, @PathVariable Long userId) {

        deleteLikeUseCase.delete(productId, userId);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(
            ProductNotFoundException pnfe
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), pnfe.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException unfe
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), unfe.getMessage()));
    }

    @ExceptionHandler(LikeDoesNotExistResultException.class)
    public ResponseEntity<ErrorResponse> handleLikeDoesNotExistResultException(
            LikeDoesNotExistResultException ldnere
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ErrorResponse(HttpStatus.OK.value(), ldnere.getMessage(), "LIKE_DOES_NOT_EXIST"));
    }
}
