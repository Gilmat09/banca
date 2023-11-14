package com.banca.demo.service;

import com.banca.demo.DTO.FibonacciStatDTO;
import com.banca.demo.repository.FibonacciNumberStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FibonacciStatService {

    private final FibonacciNumberStatRepository fibonacciNumberStatRepository;

    @Autowired
    public FibonacciStatService(FibonacciNumberStatRepository fibonacciNumberStatRepository){
        this.fibonacciNumberStatRepository = fibonacciNumberStatRepository;
    }
    public List<FibonacciStatDTO> getTop(Integer number) {
        return fibonacciNumberStatRepository.getTopNumbers(number)
                .stream()
                .map(fibo -> new FibonacciStatDTO(fibo.getNumber(), fibo.getOccurrence()))
                .collect(Collectors.toList());
    }
}
