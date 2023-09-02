package com.ecommerce.bikes.useCases;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InsertLikeUseCaseTest {

    private final EntityManager entityManager = mock(EntityManager.class);
    private final InsertLikeUseCase insertLikeUseCase = new InsertLikeUseCase(entityManager);

    @Test
    public void add_like() {
        Query query = mock(Query.class);

        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(1, 1L)).thenReturn(query);
        when(query.setParameter(2, 2L)).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        insertLikeUseCase.add(1L, 2L);
    }
}
