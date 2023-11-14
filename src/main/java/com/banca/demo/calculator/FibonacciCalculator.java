package com.banca.demo.calculator;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FibonacciCalculator implements Calculator {

    @Override
    public Integer calculate(Integer number) {
        return fib(number);
    }

    public Map<Integer, Integer> getSeries(Integer number) {
        Map<Integer, Integer> series = new HashMap<>();
        int a = 0, b = 1, c;
        series.put(a, a);
        series.put(b, b);

        for (int i = 2; i <= number; i++) {
            c = a + b;
            a = b;
            b = c;
            series.put(i, b);
        }
        return series;
    }

    private static int fib(int n) {

        int[][] F = new int[][]{{1, 1}, {1, 0}};
        if (n == 0)
            return 0;
        power(F, n - 1);

        return F[0][0];
    }

    private static void power(int[][] F, int n) {

        if (n == 0 || n == 1)
            return;
        int[][] M = new int[][]{{1, 1}, {1, 0}};

        power(F, n / 2);
        multiply(F, F);

        if (n % 2 != 0)
            multiply(F, M);
    }

    private static void multiply(int[][] F, int[][] M) {

        int x = F[0][0] * M[0][0] + F[0][1] * M[1][0];
        int y = F[0][0] * M[0][1] + F[0][1] * M[1][1];
        int z = F[1][0] * M[0][0] + F[1][1] * M[1][0];
        int w = F[1][0] * M[0][1] + F[1][1] * M[1][1];

        F[0][0] = x;
        F[0][1] = y;
        F[1][0] = z;
        F[1][1] = w;
    }
}
