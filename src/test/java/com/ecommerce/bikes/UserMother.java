package com.ecommerce.bikes;

import com.ecommerce.bikes.domain.User;
import com.ecommerce.bikes.entity.UserDAO;

import java.util.Collections;

public class UserMother {

    public static User userToSave = new User(1L, "name@example.com", "pass");
    public static UserDAO savedUserDAO = new UserDAO(1L, "name@example.com", 'M', UserMother.encodedPassword, Collections.emptyList(), Collections.emptyList());

    public static String notEncodedPassword = "pass";

    public static String encodedPassword = "$2a$12$tQQVAKT8ELlfD7hKYa8zIOfa7CVvxkFwJT27/cpumVjRFAnHGiRy6";
}
