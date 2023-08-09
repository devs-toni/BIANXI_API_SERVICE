package com.ecommerce.bikes.domain;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Product {

    private Long id;

    private String name;

    private String type;

    private float price;

    private int offer;

    private String sentence;

    private String description;

    private Set<Datasheet> datasheet;

    private List<BikeConfiguration> configuration;

    private List<Like> likes;

    public Product(Long id, String name, String type, float price, int offer, String sentence, String description, Set<Datasheet> datasheet, List<BikeConfiguration> configuration, List<Like> likes) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.offer = offer;
        this.sentence = sentence;
        this.description = description;
        this.datasheet = datasheet;
        this.configuration = configuration;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Datasheet> getDatasheet() {
        return datasheet;
    }

    public void setDatasheet(Set<Datasheet> datasheet) {
        this.datasheet = datasheet;
    }

    public List<BikeConfiguration> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(List<BikeConfiguration> configuration) {
        this.configuration = configuration;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Float.compare(product.price, price) == 0 && offer == product.offer && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(type, product.type) && Objects.equals(sentence, product.sentence) && Objects.equals(description, product.description) && Objects.equals(datasheet, product.datasheet) && Objects.equals(configuration, product.configuration) && Objects.equals(likes, product.likes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, price, offer, sentence, description, datasheet, configuration, likes);
    }
}
