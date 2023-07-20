package com.ecommerce.bikes.domain;

import java.util.Objects;

public class Color {

    private Long id;
    private String color;

    public Color (Long id, String color) {
        this.id = id;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Color color1)) return false;
        return Objects.equals(id, color1.id) && Objects.equals(color, color1.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color);
    }
}
