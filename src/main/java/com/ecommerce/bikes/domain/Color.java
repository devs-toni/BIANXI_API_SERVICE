package com.ecommerce.bikes.domain;

import com.ecommerce.bikes.http.ColorResponse;

import java.util.Objects;


public class Color {

    private Long id;
    private String color;

    public Color(Long id, String color) {
        this.id = id;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static ColorResponse toResponse(Color color) {
        return new ColorResponse(
                color.id,
                color.color
        );
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
