package com.banca.demo.service;

import com.banca.demo.calculator.FibonacciCalculatorCache;
import com.banca.demo.model.FibonacciNumberStat;
import com.banca.demo.repository.FibonacciNumberStatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class FibonacciServiceTest {

    @Mock
    private FibonacciCalculatorCache fibonacciCalculator;
    @Mock
    private FibonacciNumberStatRepository fibonacciNumberStatRepository;
    @InjectMocks
    private FibonacciService fibonacciService;

    @Test
    void testGetFiboNumberEmptyCache() {


        Mockito.when(fibonacciCalculator.getFibonacci(3)).thenReturn(2);
        Mockito.when(fibonacciNumberStatRepository.save(any(FibonacciNumberStat.class))).thenReturn(null);

        var ret = fibonacciService.getFiboNumber(3);

        Assertions.assertEquals(3, ret.getPos(), "Expected position must be 3");
        Assertions.assertEquals(2, ret.getFibonacci(), "Expected fibonacci must be 2");
    }

}
