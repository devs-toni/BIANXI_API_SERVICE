package com.ecommerce.bikes.domain;

import com.ecommerce.bikes.entity.UserEntity;
import com.ecommerce.bikes.http.UserRegisterResponse;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class User {

    private Long id;
    private String email;
    private char role;
    private String password;
    private List<Order> orders;
    private List<Like> likes;

    public User(String email, String password) {
        this.email = email;
        this.role = 'U';
        this.password = password;
        this.orders = Collections.emptyList();
        this.likes = Collections.emptyList();
    }

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.role = 'U';
        this.password = password;
        this.orders = Collections.emptyList();
        this.likes = Collections.emptyList();
    }

    public User(Long id, String email, char role, String password, List<Order> orders, List<Like> likes) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.password = password;
        this.orders = orders;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.id,
                user.email,
                user.role,
                user.password,
                user.orders.stream().map(Order::toEntity).toList(),
                user.likes.stream().map(Like::toEntity).toList()
        );
    }

    public static UserRegisterResponse toUserRegisterResponse(User user) {
        return new UserRegisterResponse(
                user.id,
                user.email,
                user.role
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return role == user.role && Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, role, password);
    }
}
