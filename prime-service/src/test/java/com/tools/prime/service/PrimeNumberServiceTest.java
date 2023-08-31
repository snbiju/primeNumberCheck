package com.tools.prime.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.tools.prime.exception.NotMatchingAlgorithmExceptiion;
import com.tools.prime.exception.InvalidPrimeNumberRangeException;
import com.tools.prime.model.PrimeNumberData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PrimeNumberServiceTest {

    @InjectMocks
    PrimeNumberService primeService;

    private PrimeNumberData data;
    @Mock
    Cache<String, List<Integer>> cache;


    @DisplayName("Unit test for getPrimeNumber method")
    @Test
    void whenWeArePassingValidPrimeNumberRangeThenReturnPrimeNumberListWithNoAlgorithms() throws InvalidPrimeNumberRangeException, NotMatchingAlgorithmExceptiion {

        List<Integer> response = primeService.getPrimeNumbers("10", null);
        assertNotNull(response);
        assertEquals(4, response.size());
    }

    @Test
    void whenWeArePassingValidPrimeNumberRangeThenReturnPrimeNumberListWithTraditionalAlgorithms() throws InvalidPrimeNumberRangeException, NotMatchingAlgorithmExceptiion {

        List<Integer> response = primeService.getPrimeNumbers("20", "TR");
        assertNotNull(response);
        assertEquals(8, response.size());
    }

    @Test
    void whenWeArePassingValidPrimeNumberRangeThenReturnPrimeNumberListWithSieveOfEratosthenesAlgorithms() throws InvalidPrimeNumberRangeException, NotMatchingAlgorithmExceptiion {

        List<Integer> response = primeService.getPrimeNumbers("20", "SE");
        assertNotNull(response);
        assertEquals(8, response.size());
    }
    @Test
    void whenWeArePassingValidPrimeNumberRangeThenReturnPrimeNumberListWithIterativeAlgorithms() throws InvalidPrimeNumberRangeException, NotMatchingAlgorithmExceptiion {

        List<Integer> response = primeService.getPrimeNumbers("20", "IR");
        assertNotNull(response);
        assertEquals(8, response.size());
    }
    @Test
    void givenRageOfPrimeNumber_whenGetAllPossiblePrimeNumbers_thenReturnPrimeNumberList_From_Cache() throws InvalidPrimeNumberRangeException, NotMatchingAlgorithmExceptiion {
        List<Integer> actual = primeService.getPrimeNumbers("10", "SE");
        assertNotNull(actual);
        assertEquals(4, actual.size());
        assertTimeout(Duration.ofMillis(2l), () -> primeService.getPrimeNumbers("10", "SE"));
    }

}