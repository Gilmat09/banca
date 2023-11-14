package com.banca.demo.service;


import com.banca.demo.model.FibonacciNumberStat;
import com.banca.demo.repository.FibonacciNumberStatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FibonacciStatServiceTest {

    @Mock
    private FibonacciNumberStatRepository fibonacciNumberStatRepository;

    @InjectMocks
    private FibonacciStatService fibonacciStatService;

    @Test
    void testGetTopOk() {
        var stats = List.of(new FibonacciNumberStat(1, 1, 10),
                new FibonacciNumberStat(2, 3, 7),
                new FibonacciNumberStat(3, 4, 6),
                new FibonacciNumberStat(4, 5, 4));

        Mockito.when(fibonacciNumberStatRepository.getTopNumbers(4)).thenReturn(stats);

        var dtos = fibonacciStatService.getTop(4);

        Assertions.assertEquals(stats.size(), dtos.size(), "Expected same number of elements");
        Assertions.assertEquals(stats.get(0).getOccurrence(),dtos.get(0).getOccurrence(), "Expected same number of occurrences for first element of lists");

    }
}
