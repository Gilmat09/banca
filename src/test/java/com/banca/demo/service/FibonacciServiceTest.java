package com.banca.demo.service;

import com.banca.demo.calculator.FibonacciCalculator;
import com.banca.demo.model.FibonacciNumberStat;
import com.banca.demo.repository.FibonacciNumberStatRepository;
import com.banca.demo.repository.FibonacciRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class FibonacciServiceTest {

    @Mock
    private FibonacciCalculator fibonacciCalculator;
    @Mock
    private FibonacciRepository fibonacciRepository;
    @Mock
    private FibonacciNumberStatRepository fibonacciNumberStatRepository;
    @InjectMocks
    private FibonacciService fibonacciService;

    @Test
    void testGetFiboNumberEmptyCache() {
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        cache.put(1, 1);
        cache.put(2, 2);

        Mockito.when(fibonacciCalculator.getSeries(2)).thenReturn(cache);
        Mockito.when(fibonacciRepository.saveAll(Mockito.any(List.class)))
                .thenReturn(List.of());
        Mockito.when(fibonacciNumberStatRepository.save(Mockito.any(FibonacciNumberStat.class))).thenReturn(null);

        var ret = fibonacciService.getFiboNumber(2);

        Assertions.assertEquals(2, ret.getPos(), "Expected position must be 2");
        Assertions.assertEquals(2, ret.getFibonacci(), "Expected fibonacci must be 2");
    }


}
