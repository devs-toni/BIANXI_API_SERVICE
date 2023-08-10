package com.ecommerce.bikes.domain;

public class BikeConfiguration {

    private Long id;
    private Product product;
    private Color color;
    private Size size;
    private Integer stock;

    public BikeConfiguration(Long id, Product product, Color color, Size size, Integer stock) {
        this.id = id;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
