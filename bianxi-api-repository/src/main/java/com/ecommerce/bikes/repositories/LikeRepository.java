package com.ecommerce.bikes.repositories;

import com.ecommerce.bikes.entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
}
