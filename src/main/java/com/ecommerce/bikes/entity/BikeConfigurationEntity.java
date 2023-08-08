package com.ecommerce.bikes.entity;

import java.util.Objects;

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
@Table(name = "stock_colors_sizes")
public class BikeConfigurationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private ProductEntity product;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "color_id", nullable = false)
	private ColorEntity color;


	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "size_id", nullable = false)
	private SizeEntity size;

	@Column(name = "stock")
	private Integer stock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity productEntity) {
		this.product = productEntity;
	}

	public SizeEntity getSizes() {
		return size;
	}

	public void setSizes(SizeEntity sizes) {
		this.size = sizes;
	}

	public ColorEntity getColor() {
		return color;
	}

	public void setColor(ColorEntity colorDAO) {
		this.color = colorDAO;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BikeConfigurationEntity other = (BikeConfigurationEntity) obj;
		return Objects.equals(color, other.color) && Objects.equals(id, other.id)
				&& Objects.equals(product, other.product) && Objects.equals(size, other.size)
				&& Objects.equals(stock, other.stock);
	}

	@Override
	public String toString() {
		return "BikeConfig [id=" + id + ", product=" + product + ", sizes=" + size + ", color=" + color + ", stock="
				+ stock + "]";
	}
}
