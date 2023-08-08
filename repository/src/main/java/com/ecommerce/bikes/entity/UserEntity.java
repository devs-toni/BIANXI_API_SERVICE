package com.ecommerce.bikes.entity;

import com.ecommerce.bikes.domain.User;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {

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
    private List<OrderEntity> orders;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<LikeEntity> likes;

    public Long getId() {
        return id;
    }

    public List<LikeEntity> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeEntity> likeEntities) {
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

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }


    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }

    public UserEntity(Long id, String email, char role, String password, List<OrderEntity> orders, List<LikeEntity> likes) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.password = password;
        this.orders = orders;
        this.likes = likes;
    }

    public UserEntity() {
    }

    public static User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.id,
                userEntity.email,
                userEntity.role,
                userEntity.password,
                userEntity.orders.stream().map(OrderEntity::toDomain).toList(),
                userEntity.likes.stream().map(LikeEntity::toDomain).toList()
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
        UserEntity other = (UserEntity) obj;
        return Objects.equals(email, other.email) && id == other.id
                && Objects.equals(password, other.password);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
    }
}
