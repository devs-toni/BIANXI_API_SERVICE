package com.ecommerce.bikes.entity;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.ecommerce.bikes.domain.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class ProductDAO {

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
	private Set<Datasheet> datasheet;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
	private List<BikeConfiguration> configuration;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "products")
	private List<OrderDAO> orderDAOS;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
	private List<Like> likes;

	public ProductDAO(Long id, String name, String type, float price, int offer, String sentence, String description, Set<Datasheet> datasheet, List<BikeConfiguration> configuration, List<OrderDAO> orderDAOS, List<Like> likes) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.offer = offer;
		this.sentence = sentence;
		this.description = description;
		this.datasheet = datasheet;
		this.configuration = configuration;
		this.orderDAOS = orderDAOS;
		this.likes = likes;
	}

	public ProductDAO() {}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
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

	public Set<Datasheet> getDatasheet() {
		return datasheet;
	}

	public void setDatasheet(Set<Datasheet> datasheet) {
		this.datasheet = datasheet;
	}

	public List<BikeConfiguration> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(List<BikeConfiguration> configuration) {
		this.configuration = configuration;
	}

	public List<OrderDAO> getOrders() {
		return orderDAOS;
	}

	public void setOrders(List<OrderDAO> orderDAOS) {
		this.orderDAOS = orderDAOS;
	}

	public static Product toDomain(ProductDAO product) {
		return new Product(
				product.id,
				product.name,
				product.type,
				product.price,
				product.offer,
				product.sentence,
				product.description,
				product.datasheet,
				product.configuration,
				product.orderDAOS.stream().map(OrderDAO::toDomain).toList(),
				product.likes
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
		ProductDAO other = (ProductDAO) obj;
		return Objects.equals(configuration, other.configuration) && Objects.equals(datasheet, other.datasheet)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && offer == other.offer && Objects.equals(orderDAOS, other.orderDAOS)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(sentence, other.sentence) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", offer=" + offer
				+ ", sentence=" + sentence + ", description=" + description + ", datasheet=" + datasheet
				+ ", configuration=" + configuration + ", orders=" + orderDAOS + "]";
	}
}
