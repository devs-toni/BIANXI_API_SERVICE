package com.ecommerce.bikes.domain;

import java.util.Objects;

public class Like {

    private Long id;
    private Long user;
    private Long product;

    public Like(Long id, Long user, Long product) {
        this.id = id;
        this.user = user;
        this.product = product;
    }

    public Like(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
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
