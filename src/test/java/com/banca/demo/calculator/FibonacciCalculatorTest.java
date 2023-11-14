package com.banca.demo.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FibonacciCalculatorTest {
    private FibonacciCalculator fibonacciCalculator;

    @BeforeEach
    public void setup() {
        fibonacciCalculator = new FibonacciCalculator();
    }

    @Test
    void testCalculateNumber() {
        Integer ret = fibonacciCalculator.calculate(10);
        Assertions.assertEquals(55, ret, "Expected result with fast algorithm should be 55");
    }

    @Test
    void testGetSeries() {
        var ret = fibonacciCalculator.getSeries(3);
        Assertions.assertEquals(4, ret.keySet().stream().count(), "Expected 4 results");
        Assertions.assertEquals(2, ret.get(3), "Expected result should be 2");
    }
}
