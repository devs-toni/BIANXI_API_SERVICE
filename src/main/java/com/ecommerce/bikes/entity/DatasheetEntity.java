package com.ecommerce.bikes.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "datasheets")
public class DatasheetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "featureName", nullable = false)
	private String featureName;
	
	@Column(name = "feature", nullable = false)
	private String feature;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", nullable = false)
	private ProductEntity product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity productEntity) {
		this.product = productEntity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatasheetEntity other = (DatasheetEntity) obj;
		return Objects.equals(feature, other.feature) && Objects.equals(featureName, other.featureName)
				&& Objects.equals(id, other.id) && Objects.equals(product, other.product);
	}

	@Override
	public String toString() {
		return "Datasheets [idLong=" + id + ", featureName=" + featureName + ", feature=" + feature + "]";
	}
}
