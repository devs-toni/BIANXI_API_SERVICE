package com.ecommerce.bikes.domain;

public class Datasheet {

    private Long id;
    private String featureName;
    private String feature;

    public Datasheet(Long id, String featureName, String feature) {
        this.id = id;
        this.featureName = featureName;
        this.feature = feature;
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

}
