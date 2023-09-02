package com.ecommerce.bikes.ports;

import com.ecommerce.bikes.domain.Color;

import java.util.List;

public interface ColorRepositoryPort {

    List<Color> findAll();
}
