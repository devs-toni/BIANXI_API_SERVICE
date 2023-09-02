package com.ecommerce.bikes.entities;


import com.ecommerce.bikes.domain.Color;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "colors")
public class ColorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color", nullable = false)
    private String color;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "color")
    private List<BikeConfigurationEntity> configuration;

    public ColorEntity(Long id, String color) {
        this.id = id;
        this.color = color;
    }

    public ColorEntity() {
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

    public List<BikeConfigurationEntity> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(List<BikeConfigurationEntity> configuration) {
        this.configuration = configuration;
    }

    public static Color toDomain(ColorEntity colorDAO) {
        return new Color(colorDAO.id, colorDAO.color);
    }

    public static ColorEntity toEntity(Color color) {
        return new ColorEntity(color.getId(), color.getColor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColorEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(color, that.color) && Objects.equals(configuration, that.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, configuration);
    }

    @Override
    public String toString() {
        return "Colors [id=" + id + ", color=" + color + "]";
    }
}
