package com.ecommerce.bikes.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "sizes")
public class Size {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "size", nullable = false)
	private String size;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "size")
	private List<BikeConfiguration> configuration;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<BikeConfiguration> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(List<BikeConfiguration> configuration) {
		this.configuration = configuration;
	}

	@Override
	public String toString() {
		return "Sizes [id=" + id + ", size=" + size + "]";
	}
}
