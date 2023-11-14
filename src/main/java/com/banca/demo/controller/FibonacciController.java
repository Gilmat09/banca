package com.banca.demo.controller;

import com.banca.demo.DTO.FibonacciNumberDTO;
import com.banca.demo.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibonacciController {
    private final FibonacciService fiboService;

    @Autowired
    public FibonacciController(FibonacciService fiboService) {
        this.fiboService = fiboService;
    }

    @GetMapping("/fibonacci")
    public FibonacciNumberDTO getNumber(@RequestParam Integer number) {
        return fiboService.getFiboNumber(number);
    }
}
