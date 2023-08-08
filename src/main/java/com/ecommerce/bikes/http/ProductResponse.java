package com.ecommerce.bikes.http;

import com.ecommerce.bikes.entity.BikeConfiguration;
import com.ecommerce.bikes.entity.Datasheet;
import com.ecommerce.bikes.entity.LikeDAO;

import java.util.List;
import java.util.Set;

public class ProductResponse {

    private Long id;

    private String name;

    private String type;

    private float price;

    private int offer;

    private String sentence;

    private String description;

    private Set<Datasheet> datasheet;

    private List<BikeConfiguration> configuration;

    private List<LikeDAO> likeDAOS;

    public ProductResponse(Long id, String name, String type, float price, int offer, String sentence, String description, Set<Datasheet> datasheet, List<BikeConfiguration> configuration, List<LikeDAO> likeDAOS) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.offer = offer;
        this.sentence = sentence;
        this.description = description;
        this.datasheet = datasheet;
        this.configuration = configuration;
        this.likeDAOS = likeDAOS;
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

    public List<LikeDAO> getLikes() {
        return likeDAOS;
    }

    public void setLikes(List<LikeDAO> likeDAOS) {
        this.likeDAOS = likeDAOS;
    }
}
