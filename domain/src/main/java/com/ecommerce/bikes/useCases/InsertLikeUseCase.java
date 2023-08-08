package com.ecommerce.bikes.useCases;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InsertLikeUseCase {

    private EntityManager entityManager;

    public InsertLikeUseCase(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional
    public Integer add(Long productId, Long userId) {
        return entityManager.createNativeQuery("INSERT INTO likes (product_id, user_id) VALUES (?, ?)").setParameter(1 ,productId).setParameter(2, userId).executeUpdate();
    }
}
