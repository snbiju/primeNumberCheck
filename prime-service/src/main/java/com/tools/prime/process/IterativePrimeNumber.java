package com.tools.prime.process;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * The outer for loop iterates from 2 to range, which takes O(n) time. The inner for loop iterates from 2 to the square root of i, which takes O(sqrt(n)) time. The total time complexity is O(n * sqrt(n)).
 * The space complexity of the computePrimeNumbers() method is O(n).
 *
 * The primeList array stores all the prime numbers found, which can have a maximum size of n. Therefore, the space complexity is O(n).
 * The outer for loop: O(n)
 * The inner for loop: O(sqrt(n))
 * The isPrime check: O(1)
 * The overall time complexity is the product of the time complexities of each step, which is O(n * sqrt(n)).
 */
public class IterativePrimeNumber implements AlgorithmHandler {

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

    @Override
    public List<Integer> apply(int range) {
        return computePrimeNumbers(range);
    }
}