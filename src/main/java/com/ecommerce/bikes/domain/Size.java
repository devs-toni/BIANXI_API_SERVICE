package com.ecommerce.bikes.domain;

import com.ecommerce.bikes.entity.BikeConfiguration;

import java.util.List;
import java.util.Objects;

public class Size {

    private Long id;

    private String size;

    private List<BikeConfiguration> configuration = null;

    public Size (Long id, String size, List<BikeConfiguration> configuration) {
        this.id = id;
        this.size = size;
        this.configuration = configuration;
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
