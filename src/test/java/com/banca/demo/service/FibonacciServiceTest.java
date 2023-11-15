package com.banca.demo.service;

import com.banca.demo.calculator.FibonacciCalculator;
import com.banca.demo.model.FibonacciNumber;
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

import static org.mockito.ArgumentMatchers.any;

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
        Map<Integer, Integer> newCache = new HashMap<>();
        newCache.put(0, 0);
        newCache.put(1, 1);
        newCache.put(2, 1);
        newCache.put(3, 2);

        var cache = List.of(new FibonacciNumber(0, 0),
                new FibonacciNumber(1, 1),
                new FibonacciNumber(2, 1));

        Mockito.when((fibonacciRepository.findAll())).thenReturn(cache);
        Mockito.when(fibonacciCalculator.getSeries(3)).thenReturn(newCache);
        Mockito.when(fibonacciRepository.saveAll(any())).thenReturn(null);
        Mockito.when(fibonacciNumberStatRepository.save(any(FibonacciNumberStat.class))).thenReturn(null);

        var ret = fibonacciService.getFiboNumber(3);

        Assertions.assertEquals(3, ret.getPos(), "Expected position must be 3");
        Assertions.assertEquals(2, ret.getFibonacci(), "Expected fibonacci must be 2");
    }

    @Test
    void testGetFiboNumberInCache() {
        var cache = List.of(new FibonacciNumber(0, 0),
                new FibonacciNumber(1, 1),
                new FibonacciNumber(2, 1));

        Mockito.when((fibonacciRepository.findAll())).thenReturn(cache);
        Mockito.when(fibonacciNumberStatRepository.save(any(FibonacciNumberStat.class))).thenReturn(null);

        var ret = fibonacciService.getFiboNumber(2);

        Assertions.assertEquals(2, ret.getPos(), "Expected position must be 2");
        Assertions.assertEquals(1, ret.getFibonacci(), "Expected fibonacci must be 1");
    }

}
