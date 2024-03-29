package com.ecommerce.bikes.useCases;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InsertLikeUseCaseTest {

    private final EntityManager entityManager = mock(EntityManager.class);
    private final InsertLikeUseCase insertLikeUseCase = new InsertLikeUseCase(entityManager);

    @Test
    @DisplayName("WHEN user tries to add a new like THEN this is applied successfully")
    public void add_like() {
        Query query = mock(Query.class);

        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(1, 1L)).thenReturn(query);
        when(query.setParameter(2, 2L)).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);

        insertLikeUseCase.add(1L, 2L);
    }
}
