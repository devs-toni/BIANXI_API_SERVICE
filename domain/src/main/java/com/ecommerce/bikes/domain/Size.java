package com.ecommerce.bikes.domain;

import com.ecommerce.bikes.entity.BikeConfigurationEntity;
import com.ecommerce.bikes.http.SizeResponse;

import java.util.List;
import java.util.Objects;

public class Size {

    private Long id;
    private String size;
    private List<BikeConfigurationEntity> configuration = null;
    public Size (Long id, String size, List<BikeConfigurationEntity> configuration) {
        this.id = id;
        this.size = size;
        this.configuration = configuration;
    }

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

    public List<BikeConfigurationEntity> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(List<BikeConfigurationEntity> configuration) {
        this.configuration = configuration;
    }

    public static SizeResponse toResponse(Size size) {
        return new SizeResponse(
                size.id,
                size.size
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Size size1)) return false;
        return Objects.equals(id, size1.id) && Objects.equals(size, size1.size) && Objects.equals(configuration, size1.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, configuration);
    }
}
