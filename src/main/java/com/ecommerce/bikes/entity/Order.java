package com.ecommerce.bikes.entity;

import java.util.List;
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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ref", nullable = false)
	private Long ref;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "company", nullable = false)
	private String company;
	
	@Column(name = "price", nullable = false)
	private float price;	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "order_products", joinColumns = {@JoinColumn(name = "order_id", insertable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "product_id", insertable = false, updatable = false)})
	private List<Product> products;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRef() {
		return ref;
	}

	public void setRef(Long ref) {
		this.ref = ref;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public List<Product> getProducts() {
		return products;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(address, other.address) && Objects.equals(company, other.company)
				&& Objects.equals(id, other.id) && Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(products, other.products) && Objects.equals(ref, other.ref)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", ref=" + ref + ", address=" + address + ", company=" + company + ", price=" + price
				+ ", user=" + user + ", products=" + products + "]";
	}

}
