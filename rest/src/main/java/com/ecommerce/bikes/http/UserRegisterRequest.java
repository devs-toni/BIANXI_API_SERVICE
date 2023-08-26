package com.ecommerce.bikes.http;

import com.ecommerce.bikes.domain.User;

public class UserRegisterRequest {

    private String email;
    private String password;

    public UserRegisterRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User toDomain(UserRegisterRequest userRegisterRequest) {
        return new User(
                userRegisterRequest.email,
                userRegisterRequest.password
        );
    }
}
