package com.ecommerce.bikes.entity;

import com.ecommerce.bikes.domain.User;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role", nullable = false)
    private char role;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "id")
    private List<OrderDAO> orderDAOS;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Like> likes;

    public Long getId() {
        return id;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<OrderDAO> getOrders() {
        return orderDAOS;
    }

    public void setOrders(List<OrderDAO> orderDAOS) {
        this.orderDAOS = orderDAOS;
    }


    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }

    public UserDAO(Long id, String email, char role, String password, List<OrderDAO> orderDAOS, List<Like> likes) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.password = password;
        this.orderDAOS = orderDAOS;
        this.likes = likes;
    }

    public UserDAO() {
    }

    public static User toDomain(UserDAO userDAO) {
        return new User(
                userDAO.id,
                userDAO.email,
                userDAO.role,
                userDAO.password,
                userDAO.orderDAOS.stream().map(OrderDAO::toDomain).toList(),
                userDAO.likes
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDAO other = (UserDAO) obj;
        return Objects.equals(email, other.email) && id == other.id
                && Objects.equals(password, other.password);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
    }
}
