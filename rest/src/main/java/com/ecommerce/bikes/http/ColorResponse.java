package com.ecommerce.bikes.http;

import com.ecommerce.bikes.domain.Color;

import java.util.Objects;

public class ColorResponse {

    private Long id;
    private String color;

    public ColorResponse(Long id, String color) {
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

    public static ColorResponse toColorResponse(Color color) {
        return new ColorResponse(
                color.getId(),
                color.getColor()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColorResponse that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color);
    }
}
