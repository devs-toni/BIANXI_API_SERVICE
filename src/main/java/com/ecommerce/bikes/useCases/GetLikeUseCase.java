package com.ecommerce.bikes.useCases;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class GetLikeUseCase {

    private EntityManager entityManager;

    public GetLikeUseCase(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional
    public Object get(Long productId, Long userId) throws NoResultException {
        return entityManager.createNativeQuery("SELECT * FROM likes WHERE product_id=? AND user_id=?")
                .setParameter(1, productId).setParameter(2, userId).getSingleResult();
    }
}
