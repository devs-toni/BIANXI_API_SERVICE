package com.ecommerce.bikes.ports;

import com.ecommerce.bikes.domain.Like;

public interface LikeRepositoryPort {

    Like save(Like like);
}
