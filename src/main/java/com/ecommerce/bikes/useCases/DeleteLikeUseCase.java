package com.ecommerce.bikes.useCases;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

public class DeleteLikeUseCase {

    private final EntityManager entityManager;

    public DeleteLikeUseCase(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public int delete(Long productId, Long userId) {
        return entityManager.createNativeQuery("DELETE FROM likes WHERE product_id=? AND user_id=?")
                .setParameter(1, productId).setParameter(2, userId).executeUpdate();
    }

}
