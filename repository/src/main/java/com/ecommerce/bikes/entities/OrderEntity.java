package com.ecommerce.bikes.entities;

import com.ecommerce.bikes.domain.Order;
import com.ecommerce.bikes.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "price", nullable = false)
    private float price;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "order_products", joinColumns = {@JoinColumn(name = "order_id", insertable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "product_id", insertable = false, updatable = false)})
    private List<ProductEntity> products;

    protected OrderEntity() {
    }

    public OrderEntity(String address, float price, UserEntity user, List<ProductEntity> products) {
        this.address = address;
        this.price = price;
        this.user = user;
        this.products = products;
    }

    public OrderEntity(Long id, String address, float price, List<ProductEntity> products) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.products = products;
    }

    public OrderEntity(Long id, String address, float price, UserEntity user, List<ProductEntity> products) {
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

    public UserEntity getUser() {
        return user;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public static Order toDomain(OrderEntity orderEntity) {
        return new Order(
                orderEntity.id,
                orderEntity.address,
                orderEntity.price,
                orderEntity.user.getId(),
                orderEntity.products.stream().map(ProductEntity::toDomain).toList()
        );
    }

    public static OrderEntity toEntity(Order order, User user) {
        return new OrderEntity(
                order.getId(),
                order.getAddress(),
                order.getPrice(),
                UserEntity.toEntity(user),
                order.getProducts().stream().map(p -> ProductEntity.toEntity(p, user)).toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity that)) return false;
        return Float.compare(that.price, price) == 0 && Objects.equals(id, that.id) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, price);
    }
}
