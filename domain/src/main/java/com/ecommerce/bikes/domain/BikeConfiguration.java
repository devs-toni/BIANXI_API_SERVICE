package com.ecommerce.bikes.domain;

public class BikeConfiguration {

    private Long id;
    private Color color;
    private Size size;
    private Integer stock;

    public BikeConfiguration(Long id, Color color, Size size, Integer stock) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
