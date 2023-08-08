package com.ecommerce.bikes.entities;

import com.ecommerce.bikes.domain.Like;
import com.ecommerce.bikes.domain.Product;
import com.ecommerce.bikes.domain.User;
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
public class LikeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", nullable = false)
	private ProductEntity product;

	public LikeEntity(Long id, UserEntity user, ProductEntity product) {
		this.id = id;
		this.user = user;
		this.product = product;

	}

	public LikeEntity() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity userEntity) {
		this.user = userEntity;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity productEntity) {
		this.product = productEntity;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", user=" + user.getId() + ", product=" + product.getId() + "]";
	}

	public static Like toDomain(LikeEntity like) {
		return new Like(
				like.id,
				UserEntity.toDomain(like.user),
				ProductEntity.toDomain(like.product)
		);
	}

	public static LikeEntity toEntity(Like like) {
		return new LikeEntity(
				like.getId(),
				UserEntity.toEntity(like.getUser()),
				ProductEntity.toEntity(like.getProduct())
		);
	}
}
