package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.exception.LikeDoesNotExistResultException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetLikeUseCaseTest {

    private final EntityManager entityManager = mock(EntityManager.class);
    private final GetLikeUseCase getLikeUseCase = new GetLikeUseCase(entityManager);

    @Test
    public void get_like() throws LikeDoesNotExistResultException {
        Object dataExpected = new Object();

        Query query = mock(Query.class);

        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(1, 1L)).thenReturn(query);
        when(query.setParameter(2, 2L)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(dataExpected);

        Object dataObtained = getLikeUseCase.get(1L, 2L);

        assertEquals(dataExpected, dataObtained);
    }
}
