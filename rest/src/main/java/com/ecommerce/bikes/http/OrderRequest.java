package com.ecommerce.bikes.http;

import java.util.List;
import java.util.Objects;

public class OrderRequest {

    private List<Long> productsIds;
    private Long userId;
    private String orderAddress;
    private Float orderAmount;

    public OrderRequest(List<Long> productsIds, Long userId, String orderAddress, Float orderAmount) {
        this.productsIds = productsIds;
        this.userId = userId;
        this.orderAddress = orderAddress;
        this.orderAmount = orderAmount;
    }

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Float orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderRequest that)) return false;
        return Objects.equals(productsIds, that.productsIds) && Objects.equals(userId, that.userId) && Objects.equals(orderAddress, that.orderAddress) && Objects.equals(orderAmount, that.orderAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productsIds, userId, orderAddress, orderAmount);
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "productsIds=" + productsIds +
                ", userId=" + userId +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderAmount=" + orderAmount +
                '}';
    }
}
