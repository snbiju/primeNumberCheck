package com.tools.prime.process;

import java.util.ArrayList;
import java.util.List;

public class TraditionalPrimeNumber implements AlgorithmHandler {

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
    private List<Integer> computePrimeNumbers(int range) {
        List<Integer> primeList = new ArrayList<>();
        for (int i = 2; i <= range; i++) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primeList.add(i);
            }
        }
        return primeList;
    }

    @Override
    public List<Integer>  apply(int range) {
        return computePrimeNumbers(range);
    }
}
