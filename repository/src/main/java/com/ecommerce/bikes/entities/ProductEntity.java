package com.ecommerce.bikes.entities;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.ecommerce.bikes.domain.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "price", nullable = false)
	private float price;

	@Column(name = "offer", nullable = false)
	private int offer;

	@Column(name = "sentence", nullable = false)
	private String sentence;

	@Column(name = "description", nullable = false)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	private Set<DatasheetEntity> datasheet;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
	private List<BikeConfigurationEntity> configuration;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
	private List<LikeEntity> likes;

	public ProductEntity(Long id, String name, String type, float price, int offer, String sentence, String description, Set<DatasheetEntity> datasheet, List<BikeConfigurationEntity> configuration, List<LikeEntity> likes) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.offer = offer;
		this.sentence = sentence;
		this.description = description;
		this.datasheet = datasheet;
		this.configuration = configuration;
		this.likes = likes;
	}

	public ProductEntity() {}

	public List<LikeEntity> getLikes() {
		return likes;
	}

	public void setLikes(List<LikeEntity> likes) {
		this.likes = likes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<DatasheetEntity> getDatasheet() {
		return datasheet;
	}

	public void setDatasheet(Set<DatasheetEntity> datasheet) {
		this.datasheet = datasheet;
	}

	public List<BikeConfigurationEntity> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(List<BikeConfigurationEntity> configuration) {
		this.configuration = configuration;
	}

	public static Product toDomain(ProductEntity product) {
		return new Product(
				product.id,
				product.name,
				product.type,
				product.price,
				product.offer,
				product.sentence,
				product.description,
				product.datasheet.stream().map(DatasheetEntity::toDomain).collect(Collectors.toSet()),
				product.configuration.stream().map(BikeConfigurationEntity::toDomain).toList(),
				product.likes.stream().map(LikeEntity::toDomain).toList()
		);
	}

	public static ProductEntity toEntity(Product product) {
		return new ProductEntity(
				product.getId(),
				product.getName(),
				product.getType(),
				product.getPrice(),
				product.getOffer(),
				product.getSentence(),
				product.getDescription(),
				product.getDatasheet().stream().map(DatasheetEntity::toEntity).collect(Collectors.toSet()),
				product.getConfiguration().stream().map(BikeConfigurationEntity::toEntity).toList(),
				product.getLikes().stream().map(LikeEntity::toEntity).toList()
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
		ProductEntity other = (ProductEntity) obj;
		return Objects.equals(configuration, other.configuration) && Objects.equals(datasheet, other.datasheet)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && offer == other.offer
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(sentence, other.sentence) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", offer=" + offer
				+ ", sentence=" + sentence + ", description=" + description + ", datasheet=" + datasheet
				+ ", configuration=" + configuration + "]";
	}
}
