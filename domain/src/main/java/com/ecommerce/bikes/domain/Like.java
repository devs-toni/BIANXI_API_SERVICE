package com.ecommerce.bikes.domain;

import java.util.Objects;

public class Like {

    private Long id;
    private User user;
    private Product product;

    public Like(Long id, User user, Product product) {
        this.id = id;
        this.user = user;
        this.product = product;
    }

    public Like(Long id, Product product) {
        this.id = id;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Like like)) return false;
        return Objects.equals(id, like.id) && Objects.equals(user, like.user) && Objects.equals(product, like.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, product);
    }
}
