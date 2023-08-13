package com.ecommerce.bikes.domain;

import java.util.List;
import java.util.Objects;

public class Size {

    private Long id;
    private String size;
    private List<BikeConfiguration> configuration = null;

    public Size(Long id, String size) {
        this.id = id;
        this.size = size;
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

    public List<BikeConfiguration> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(List<BikeConfiguration> configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Size size1)) return false;
        return Objects.equals(id, size1.id) && Objects.equals(size, size1.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size);
    }
}
