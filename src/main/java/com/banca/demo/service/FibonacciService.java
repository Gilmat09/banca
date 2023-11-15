package com.banca.demo.service;

import com.banca.demo.DTO.FibonacciNumberDTO;
import com.banca.demo.calculator.FibonacciCalculatorCache;
import com.banca.demo.model.FibonacciNumberStat;
import com.banca.demo.repository.FibonacciNumberStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FibonacciService {
    private final FibonacciCalculatorCache calculatorCache;
    private final FibonacciNumberStatRepository fibonacciNumberStatRepository;
    @Autowired
    public FibonacciService(FibonacciCalculatorCache calculatorCache, FibonacciNumberStatRepository fibonacciNumberStatRepository) {
        this.calculatorCache = calculatorCache;
        this.fibonacciNumberStatRepository = fibonacciNumberStatRepository;
    }

    public FibonacciNumberDTO getFiboNumber(Integer number) {
        FibonacciNumberDTO fiboNumber = new FibonacciNumberDTO();
        fiboNumber.setPos(number);
        fiboNumber.setFibonacci(calculatorCache.getFibonacci(number));
        saveNewOccurrence(number);
        return fiboNumber;
    }

    private void saveNewOccurrence(Integer number) {
        FibonacciNumberStat stat = new FibonacciNumberStat();
        stat.setNumber(number);
        fibonacciNumberStatRepository.save(stat);
    }
}
