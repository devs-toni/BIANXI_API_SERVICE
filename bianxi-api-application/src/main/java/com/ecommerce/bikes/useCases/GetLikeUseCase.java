package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.exception.LikeDoesNotExistResultException;
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
    public Object get(Long productId, Long userId) {
        try {
            return entityManager.createNativeQuery("SELECT * FROM likes WHERE product_id=? AND user_id=?")
                    .setParameter(1, productId)
                    .setParameter(2, userId)
                    .getSingleResult();
        } catch (NoResultException nre) {
            throw new LikeDoesNotExistResultException("Does not exist a result with this specifications");
        }
    }
}
