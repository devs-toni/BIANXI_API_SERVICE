package com.ecommerce.bikes.useCases;

import com.ecommerce.bikes.exception.LikeDoesNotExistResultException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetLikeUseCaseTest {

    private final EntityManager entityManager = mock(EntityManager.class);
    private final GetLikeUseCase getLikeUseCase = new GetLikeUseCase(entityManager);

    @Test
    @DisplayName("WHEN user tries to get a new like THEN this is returned successfully")
    public void get_like() {
        Object dataExpected = new Object();

        Query query = mock(Query.class);

        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(1, 1L)).thenReturn(query);
        when(query.setParameter(2, 2L)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(dataExpected);

        Object dataObtained = getLikeUseCase.get(1L, 2L);

        assertEquals(dataExpected, dataObtained);
    }

    @Test
    @DisplayName("WHEN user tries to get a new like THEN throw exception because like diesn't exist")
    public void should_throw_LikeDoesNotExistException_when_get_like() {
        Object dataExpected = new Object();

        Query query = mock(Query.class);

        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(1, 1L)).thenReturn(query);
        when(query.setParameter(2, 2L)).thenReturn(query);
        when(query.getSingleResult()).thenThrow(LikeDoesNotExistResultException.class);

        assertThrows(LikeDoesNotExistResultException.class, () -> {
            getLikeUseCase.get(1L, 2L);
        });
    }
}
