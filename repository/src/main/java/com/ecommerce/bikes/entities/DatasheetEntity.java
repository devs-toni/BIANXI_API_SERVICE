package com.ecommerce.bikes.entities;

import com.ecommerce.bikes.domain.Datasheet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

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

    public DatasheetEntity(Long id, String featureName, String feature, ProductEntity product) {
        this.id = id;
        this.featureName = featureName;
        this.feature = feature;
        this.product = product;
    }

    public DatasheetEntity() {}

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

    public static Datasheet toDomain(DatasheetEntity datasheet) {
        return new Datasheet(
                datasheet.id,
                datasheet.featureName,
                datasheet.feature,
                ProductEntity.toDomain(datasheet.product)
        );
    }

    public static DatasheetEntity toEntity(Datasheet datasheet) {
        return new DatasheetEntity(
                datasheet.getId(),
                datasheet.getFeatureName(),
                datasheet.getFeature(),
                ProductEntity.toEntity(datasheet.getProduct())
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
        DatasheetEntity other = (DatasheetEntity) obj;
        return Objects.equals(feature, other.feature) && Objects.equals(featureName, other.featureName)
                && Objects.equals(id, other.id) && Objects.equals(product, other.product);
    }

    @Override
    public String toString() {
        return "Datasheets [idLong=" + id + ", featureName=" + featureName + ", feature=" + feature + "]";
    }
}
