package com.banca.demo.controller;

import com.banca.demo.DTO.FibonacciStatDTO;
import com.banca.demo.service.FibonacciStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FibonacciStatController {

    private final FibonacciStatService fibonacciStatService;

    @Autowired
    public FibonacciStatController(FibonacciStatService fibonacciStatService){
        this.fibonacciStatService = fibonacciStatService;
    }

    @GetMapping("/top")
    public List<FibonacciStatDTO> getTop(@RequestParam Integer number){
        return fibonacciStatService.getTop(number);
    }
}
