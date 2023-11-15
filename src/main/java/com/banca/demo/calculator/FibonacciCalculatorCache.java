package com.banca.demo.calculator;

import com.banca.demo.model.FibonacciNumber;
import com.banca.demo.repository.FibonacciRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FibonacciCalculatorCache {

    @Getter
    private Map<Integer, Integer> cache = new HashMap<>();
    private Map<Integer, Pair<Integer, Integer>> lastNumbers = new HashMap<>();
    private static final Integer MAX_VALUE_POS = -1;
    private static final Integer LESS_MAX_VALUE_POS = -2;

    private final FibonacciRepository fibonacciRepository;

    @Autowired
    public FibonacciCalculatorCache(FibonacciRepository fibonacciRepository) {
        this.fibonacciRepository = fibonacciRepository;
    }

    public Integer getFibonacci(Integer number) {
        if(cache.isEmpty()){
            loadCache();
        }
        if (cache.containsKey(number)) {
            return cache.get(number);
        } else {
            return calculateFibonacci(number);
        }
    }

    private Integer calculateFibonacci(Integer number) {
        Map<Integer, Integer> series = new HashMap<>();
        int fibonacciNumber = 0;
        int maxPosInCache = lastNumbers.get(MAX_VALUE_POS).getFirst() + 1;

        int a = lastNumbers.get(LESS_MAX_VALUE_POS).getSecond();
        int b = lastNumbers.get(MAX_VALUE_POS).getSecond();
        int c;

        if (a == 0) {
            series.put(0, a);
        }
        if (b == 1) {
            fibonacciNumber = b;
            series.put(1, b);
        }

        for (int i = maxPosInCache; i <= number; i++) {
            c = a + b;
            a = b;
            b = c;
            series.put(i, b);
            fibonacciNumber = b;
            lastNumbers.put(LESS_MAX_VALUE_POS, Pair.of(i - 1, a));
            lastNumbers.put(MAX_VALUE_POS, Pair.of(i, b));
        }
        saveSeries(series);
        return fibonacciNumber;
    }

    private void saveSeries(Map<Integer, Integer> values) {
        fibonacciRepository.saveAll(
                values.keySet()
                        .stream()
                        .map(pos -> new FibonacciNumber(pos, values.get(pos)))
                        .collect(Collectors.toList())
        );
        cache.putAll(values);

    }

    private void loadCache() {
        List<FibonacciNumber> lst = fibonacciRepository.findAll();

        if (lst.isEmpty()) {
            lst.add(new FibonacciNumber(0, 0));
            lst.add(new FibonacciNumber(1, 1));
        }
        cache = lst
                .stream()
                .collect(Collectors.toMap(FibonacciNumber::getPos, FibonacciNumber::getFibonacci));

        lastNumbers.put(MAX_VALUE_POS, Pair.of(lst.get(lst.size() - 1).getPos(), lst.get(lst.size() - 1).getFibonacci()));
        lastNumbers.put(LESS_MAX_VALUE_POS, Pair.of(lst.get(lst.size() - 2).getPos(), lst.get(lst.size() - 2).getFibonacci()));
    }

}
