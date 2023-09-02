package com.ecommerce.bikes.ports;

import com.ecommerce.bikes.domain.Size;

import java.util.List;

public interface SizeRepositoryPort {
    List<Size> findAll();
}
