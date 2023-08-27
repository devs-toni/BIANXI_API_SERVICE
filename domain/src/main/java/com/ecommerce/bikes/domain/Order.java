package com.ecommerce.bikes.domain;

import java.util.List;
import java.util.Objects;

public class Order {

    private Long id;
    private String address;
    private float price;
    private List<Product> products;
    private Long user;

    public Order(String address, float price, Long user, List<Product> products) {
        this.id = null;
        this.address = address;
        this.price = price;
        this.user = user;
        this.products = products;
    }

    public Order(Long id, String address, float price, Long user, List<Product> products) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.user = user;
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Float.compare(order.price, price) == 0 && Objects.equals(id, order.id) && Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, price);
    }

}
