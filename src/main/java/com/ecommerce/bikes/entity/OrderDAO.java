package com.ecommerce.bikes.entity;

import java.util.List;

import com.ecommerce.bikes.domain.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderDAO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "price", nullable = false)
	private float price;	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private UserDAO user;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "order_products", joinColumns = {@JoinColumn(name = "order_id", insertable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "product_id", insertable = false, updatable = false)})
	private List<ProductDAO> products;

	protected OrderDAO() {}

	public OrderDAO(String address, float price, UserDAO user, List<ProductDAO> products) {
		this.address = address;
		this.price = price;
		this.user = user;
		this.products = products;
	}

	public OrderDAO(Long id, String address, float price, List<ProductDAO> products) {
		this.id = id;
		this.address = address;
		this.price = price;
		this.products = products;
	}

	public OrderDAO(Long id, String address, float price, UserDAO user, List<ProductDAO> products) {
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

	public UserDAO getUser() {
		return user;
	}

	public List<ProductDAO> getProducts() {
		return products;
	}

	public static Order toDomain(OrderDAO orderDAO) {
		return new Order(
				orderDAO.id,
				orderDAO.address,
				orderDAO.price,
				orderDAO.products.stream().map(ProductDAO::toDomain).toList()
		);
	}

}
