package com.ecommerce.bikes.entities;

import com.ecommerce.bikes.domain.BikeConfiguration;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "stock_colors_sizes")
public class BikeConfigurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public BikeConfigurationEntity(Long id, ColorEntity color, SizeEntity size, Integer stock) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.stock = stock;
    }

    public BikeConfigurationEntity() {
    }

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

    public static BikeConfiguration toDomain(BikeConfigurationEntity bikeConfigurationEntity) {
        return new BikeConfiguration(
                bikeConfigurationEntity.id,
                ColorEntity.toDomain(bikeConfigurationEntity.color),
                SizeEntity.toDomain(bikeConfigurationEntity.size),
                bikeConfigurationEntity.stock
        );
    }

    public static BikeConfigurationEntity toEntity(BikeConfiguration bikeConfiguration) {
        return new BikeConfigurationEntity(
                bikeConfiguration.getId(),
                ColorEntity.toEntity(bikeConfiguration.getColor()),
                SizeEntity.toEntity(bikeConfiguration.getSize()),
                bikeConfiguration.getStock()
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
