package com.ecommerce.bikes.http;

import com.ecommerce.bikes.domain.Size;

public class SizeResponse {

    private Long id;
    private String size;

    public SizeResponse(Long id, String size) {
        this.id = id;
        this.size = size;
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

    public static SizeResponse toSizeResponse(Size size) {
        return new SizeResponse(
                size.getId(),
                size.getSize()
        );
    }
}
