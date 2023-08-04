package com.ecommerce.bikes.http;

import com.ecommerce.bikes.domain.Product;

import java.util.List;

public class OrderResponse {

    private Long id;
    private String address;
    private float price;
    private List<ProductResponse> products;

    public OrderResponse(Long id, String address, float price, List<ProductResponse> products) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}
