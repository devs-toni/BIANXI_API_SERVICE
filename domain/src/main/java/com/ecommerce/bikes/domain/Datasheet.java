package com.ecommerce.bikes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

public class Datasheet {

    private Long id;
    private String featureName;
    private String feature;
    private Product product;

    public Datasheet(Long id, String featureName, String feature, Product product) {
        this.id = id;
        this.featureName = featureName;
        this.feature = feature;
        this.product = product;
    }

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
