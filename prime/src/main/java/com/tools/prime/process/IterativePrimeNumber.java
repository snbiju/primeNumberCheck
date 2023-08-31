package com.tools.prime.process;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class IterativePrimeNumber {

    public List<Integer> computePrimeNumbers(Integer range) {

        List<Integer> primeNumberList = new ArrayList<>();
        int i = 2;
        while (i <= range) {
            if (isPrime(i))
                primeNumberList.add(i);
            i++;
        }
        return primeNumberList;

    }

    private boolean isPrime(int n) {
        return IntStream.iterate(2, i -> i <= Math.sqrt(n), i -> i + 1).noneMatch(i -> n % i == 0);
    }
}
