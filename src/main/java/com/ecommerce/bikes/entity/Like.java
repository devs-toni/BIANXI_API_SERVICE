package com.ecommerce.bikes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private UserDAO user;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", nullable = false)
	private ProductDAO product;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDAO getUser() {
		return user;
	}

	public void setUser(UserDAO userDAO) {
		this.user = userDAO;
	}

	public ProductDAO getProduct() {
		return product;
	}

	public void setProduct(ProductDAO productDAO) {
		this.product = productDAO;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", user=" + user.getId() + ", product=" + product.getId() + "]";
	}
	
	
}
