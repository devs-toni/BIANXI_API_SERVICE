package com.ecommerce.bikes.http;

import com.ecommerce.bikes.domain.User;

import java.util.Objects;

public class UserRegisterResponse {

    private Long id;
    private String email;
    private char role;

    public UserRegisterResponse(Long id, String email, char role) {
        this.id = id;
        this.email = email;
        this.role = role;
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

    public static UserRegisterResponse toUserRegisterResponse(User user) {
        return new UserRegisterResponse(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRegisterResponse that)) return false;
        return role == that.role && Objects.equals(id, that.id) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, role);
    }
}
