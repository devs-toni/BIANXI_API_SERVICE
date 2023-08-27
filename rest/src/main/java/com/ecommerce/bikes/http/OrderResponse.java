package com.ecommerce.bikes.http;

import com.ecommerce.bikes.domain.Order;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class OrderResponse implements Serializable {

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

    public static OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getAddress(),
                order.getPrice(),
                order.getProducts().stream().map(ProductResponse::toProductResponse).toList()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderResponse that)) return false;
        return Float.compare(that.price, price) == 0 && Objects.equals(id, that.id) && Objects.equals(address, that.address) && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, price, products);
    }
}
