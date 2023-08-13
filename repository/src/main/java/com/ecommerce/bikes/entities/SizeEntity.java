package com.ecommerce.bikes.entities;


import com.ecommerce.bikes.domain.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "sizes")
public class SizeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size", nullable = false)
    private String size;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "size")
    private List<BikeConfigurationEntity> configuration;

    public SizeEntity(Long id, String size) {
        this.id = id;
        this.size = size;
        this.configuration = Collections.emptyList();
    }

    public SizeEntity() {
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

    public static Size toDomain(SizeEntity size) {
        return new Size(size.id, size.size);
    }

    public static SizeEntity toEntity(Size size) {
        return new SizeEntity(size.getId(), size.getSize());
    }

    @Override
    public String toString() {
        return "Sizes [id=" + id + ", size=" + size + "]";
    }
}
