package com.banca.demo.service;

import com.banca.demo.DTO.FibonacciNumberDTO;
import com.banca.demo.calculator.FibonacciCalculator;
import com.banca.demo.model.FibonacciNumber;
import com.banca.demo.model.FibonacciNumberStat;
import com.banca.demo.repository.FibonacciNumberStatRepository;
import com.banca.demo.repository.FibonacciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FibonacciService {


    private final FibonacciCalculator calculator;

    private final FibonacciRepository fibonacciRepository;

    private final FibonacciNumberStatRepository fibonacciNumberStatRepository;

    private Map<Integer, Integer> cache = new HashMap<>();

    @Autowired
    public FibonacciService(FibonacciCalculator calculator, FibonacciRepository fibonacciRepository, FibonacciNumberStatRepository fibonacciNumberStatRepository) {
        this.calculator = calculator;
        this.fibonacciRepository = fibonacciRepository;
        this.fibonacciNumberStatRepository = fibonacciNumberStatRepository;
    }

    public FibonacciNumberDTO getFiboNumber(Integer number) {
        if (cache.isEmpty()) {
            loadCache();
        }

        FibonacciNumberDTO fiboNumber = new FibonacciNumberDTO();
        fiboNumber.setPos(number);

        if (cache != null && cache.get(number) != null) {
            fiboNumber.setFibonacci(cache.get(number));
        } else {
            loadNewSeries(number);
            fiboNumber.setFibonacci(cache.get(number));
        }
        saveNewOccurrence(number);
        return fiboNumber;
    }

    private void loadNewSeries(Integer number) {
        Map<Integer, Integer> oldCache = new HashMap<>(cache);
        cache = calculator.getSeries(number);
        Map<Integer, Integer> newCache = new HashMap<>(cache);
        oldCache.keySet().forEach(newCache::remove);
        saveSeries(newCache);

    }

    private void loadCache() {
        cache = fibonacciRepository.findAll()
                .stream()
                .collect(Collectors.toMap(FibonacciNumber::getPos, FibonacciNumber::getFibonacci));
    }

    private void saveSeries(Map<Integer, Integer> values) {
        fibonacciRepository.saveAll(
                values.keySet()
                        .stream()
                        .map(pos -> new FibonacciNumber(pos, values.get(pos)))
                        .collect(Collectors.toList())
        );
    }

    private void saveNewOccurrence(Integer number) {
        FibonacciNumberStat stat = new FibonacciNumberStat();
        stat.setNumber(number);
        fibonacciNumberStatRepository.save(stat);
    }
}
