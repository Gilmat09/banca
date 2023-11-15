package com.banca.demo.calculator;

import com.banca.demo.repository.FibonacciRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class FibonacciCalculatorTest {

    @Mock
    private FibonacciRepository fibonacciRepository;
    @InjectMocks
    private FibonacciCalculatorCache fibonacciCalculator;


    @Test
    void testGetFibonacciNoSavedCache() {
        Mockito.when(fibonacciRepository.findAll()).thenReturn(new ArrayList<>());
        Mockito.when(fibonacciRepository.saveAll(any())).thenReturn(null);

        Integer ret = fibonacciCalculator.getFibonacci(3);

        Assertions.assertEquals(2, ret, "Fibonacci of 3 should be 2");
    }

    @Test
    void testGetFibonacciCache() {
        Integer ret = fibonacciCalculator.getFibonacci(1);

        Assertions.assertEquals(1, ret, "Fibonacci of 1 should be 1");
    }
}
