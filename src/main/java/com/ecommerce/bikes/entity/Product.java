package com.ecommerce.bikes.entity;

import java.util.List;
import java.util.Objects;
import java.util.Set;

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
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", 
			nullable = false)
	private String name;
	
	@Column(name = "type", 
			nullable = false)
	private char type;
	
	@Column(name = "price", 
			nullable = false)
	private float price;
	
	@Column(name = "offer", 
			nullable = false)
	private int offer;
	
	@Column(name = "stock", 
			nullable = false)
	private int stock;
	
	@Column(name = "sentence", 
			nullable = false)
	private String sentence;
	
	@Column(name = "description", 
			nullable = false)
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, 
			   fetch = FetchType.LAZY, 
			   mappedBy = "product")
	private Set<Colors> colors;
	
	@OneToMany(cascade = CascadeType.ALL, 
			   fetch = FetchType.LAZY, 
			   mappedBy = "product")	
	private Set<Sizes> sizes;
	
	@OneToMany(cascade = CascadeType.ALL, 
			   fetch = FetchType.LAZY, 
			   mappedBy = "product")
	private Set<Datasheets> datasheet;
	
	@ManyToMany(fetch = FetchType.LAZY,
				cascade = CascadeType.ALL,
				mappedBy = "products")	
	private List<Order> orders;

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

	public char getType() {
		return type;
	}

	public void setType(char type) {
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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

	public Set<Colors> getColors() {
		return colors;
	}

	public void setColors(Set<Colors> colors) {
		this.colors = colors;
	}

	public Set<Sizes> getSizes() {
		return sizes;
	}

	public void setSizes(Set<Sizes> sizes) {
		this.sizes = sizes;
	}

	public Set<Datasheets> getDatasheet() {
		return datasheet;
	}

	public void setDatasheet(Set<Datasheets> datasheet) {
		this.datasheet = datasheet;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(colors, other.colors) && Objects.equals(datasheet, other.datasheet)
				&& Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name)
				&& offer == other.offer && Objects.equals(orders, other.orders)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(sentence, other.sentence) && Objects.equals(sizes, other.sizes)
				&& stock == other.stock && type == other.type;
	}

	@Override
	public String toString() {
		
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + ", offer=" + offer
				+ ", stock=" + stock + ", sentence=" + sentence + ", description=" + description + "]";
	}
}
